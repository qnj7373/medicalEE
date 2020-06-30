package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.certificateMapper;
import org.wzxy.breeze.mapper.expenseMapper;
import org.wzxy.breeze.mapper.policyMapper;
import org.wzxy.breeze.model.dto.expenseDto;
import org.wzxy.breeze.model.dto.paymentDto;
import org.wzxy.breeze.model.dto.policyDto;
import org.wzxy.breeze.model.po.*;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IExpenseService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class expenseServiceImpl implements IExpenseService {

    @Resource
    private expenseMapper expenseDao;
    @Resource
    private certificateMapper certDao;
    @Resource
    private policyMapper policyDao;
    @Autowired
    private List<expenseDto> expenseDtoList;

    @Autowired
    private Page<expenseDto> expenseDtoPage;
    @Autowired
    List<expense> expenseByPage;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;

    @Override
    @Cacheable(value = "expenseZone" , key = "'findExpenseByPage'+#administrationId+','+#state+','+#nowPage+','+#pageSize")
    public Page<expenseDto> findExpenseByPage(int administrationId,String state, int nowPage, int pageSize) {
        expenseByPage.clear();
           if(state!=null){
               if("已通过".equals(state)){
                   expenseByPage = expenseDao.getExpenseByStatePage(administrationId,state,"已汇款",nowPage * pageSize, pageSize);
               }else if ("未审核".equals(state)){
                   expenseByPage = expenseDao.checkExpenseByStatePage(administrationId,state,"已通过","未通过",nowPage * pageSize, pageSize);
               }
           }else{
               expenseByPage = expenseDao.getExpenseByPage(administrationId, nowPage * pageSize, pageSize);
           }

        expenseDtoList.clear();
        for (expense e:
                expenseByPage) {
            expenseDtoList.add(new expenseDto(e));
        }
        expenseDtoPage.setDatas(expenseDtoList);
        expenseDtoPage.setNowPage(nowPage+1);
        expenseDtoPage.setDataTotalCount(expenseDao.getTotalCount(administrationId));
        expenseDtoPage.setPageSize(pageSize);
        expenseDtoPage.setPageTotalCount(expenseDtoPage.getPageTotalCount());
        return expenseDtoPage;
    }

    @Override
    @CacheEvict(cacheNames = "expenseZone",allEntries = true)
    public HandleResult addExpense(expenseDto eDto, int administrationId) {

           exist=  expenseDao.isExist(eDto.getId());
         if (exist==0){
                    //先验证报销信息是否与慢性病证一致
                    exist= certDao.expenseIsConsistent(eDto);
                 if(exist==1){


                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
                    Date date = new Date();
                    String nowYear = dateFormat.format(date);

                    policy policyByRunyear = policyDao.findPolicyByRunyear(nowYear);
                    double maxline = policyByRunyear.getMaxline();
                    String rate = policyByRunyear.getRate();
                    double nowRate = Double.parseDouble(rate);

                    double InputAmount = Double.parseDouble(eDto.getInputAmount());
                    double expenseNum = InputAmount * nowRate;//本次报销金额

                    List<expense> expenseByCardID = expenseDao.findExpenseByCardID(eDto.getCardID());
                    double already=0;//已报销金额
                    for (expense e:
                            expenseByCardID) {
                        already+=e.getAmount();
                    }
                    //本年度报销金额已达封顶线,无法再报销
                    if(already>=maxline){
                        handle.setStatus(ResponseCode.FAIL.getCode());
                        handle.setMessage("报销失败，本年度报销金额已达上限!");
                        return handle;
                    }else {
                        //本次应报销金额到达封顶，但可报销金额还有
                        if(expenseNum >=maxline&&already<maxline){
                            expenseNum=maxline-already;
                            eDto.setAmount(expenseNum);
                            handle.setStatus(ResponseCode.OK.getCode());
                            handle.setMessage("报销成功!由于报销金额已超上限，" +
                                    "所以只给予了允许范围内的报销金额 "+expenseNum+" !");

                        }else{  //本次应报销金额未到达封顶，可报销金额还有
                            eDto.setAmount(expenseNum);
                            handle.setStatus(ResponseCode.OK.getCode());
                            handle.setMessage("报销成功!本次报销金额为 " +expenseNum+" !");
                        }

                    }

                eDto.setRunyear(nowYear);
                eDto.setAdministrationId(administrationId);
                eDto.setState("未审核");
                     if(   expenseDao.addExpense( new expense(eDto)) ){
                     }else{
                         handle.setStatus(ResponseCode.FAIL.getCode());
                         handle.setMessage("报销失败!");
                     }

                }else{      //不一致
                        handle.setStatus(ResponseCode.FAIL.getCode());
                        handle.setMessage("报销失败，报销信息与慢病证信息不一致或尚无慢性病证!");
                }

             return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("报销失败，报销信息已存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "expenseZone",allEntries = true)
    public HandleResult updateExpense(expenseDto eDto) {
        return null;
    }

    @Override
    @CacheEvict(cacheNames = "expenseZone",allEntries = true)
    public HandleResult updateExpenseState(expenseDto eDto) {
        exist=expenseDao.isExist(eDto.getId());
        if(exist==1){
            if( expenseDao.updateExpenseState(new expense(eDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("审核成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("审核失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("审核失败，报销信息不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "expenseZone" , key = "'findExpenseById'+#Id")
    public expenseDto findExpenseById(int Id) {
        return  new expenseDto(expenseDao.findExpenseById(Id));
    }

    @Override
    @CacheEvict(cacheNames = "expenseZone",allEntries = true)
    public HandleResult deleteExpenseById(int Id) {

        exist=  expenseDao.isExist(Id);
        if(exist==1){

            if(  expenseDao.deleteExpense(Id) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除报销信息成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除报销信息失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，报销信息不存在!");
            return handle;
        }


    }
}
