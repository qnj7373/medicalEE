package org.wzxy.breeze.core.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.payment;
import org.wzxy.breeze.core.mapper.paymentMapper;
import org.wzxy.breeze.core.model.dto.paymentDto;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.service.Iservice.IPaymentService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Resource
    private paymentMapper payDao;
    @Autowired
    private List<paymentDto> payDtoList;
    @Autowired
    private Page<paymentDto> payPage;
    @Autowired
    private  paymentDto payDto;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;


    @Override
    @Cacheable(value = "paymentZone" , key = "'findPaymentByPage'+#regionId+','+#nowPage+','+#pageSize")
    public Page<paymentDto> findPaymentByPage(int regionId, int nowPage, int pageSize) {
        List<payment> paymentsByPage = payDao.getPaymentsByPage(regionId, nowPage * pageSize, pageSize);
        payDtoList.clear();
        for (payment p:
                paymentsByPage) {
            payDtoList.add(new paymentDto(p));
        }
        payPage.setDatas(payDtoList);
        payPage.setNowPage(nowPage+1);
        payPage.setDataTotalCount(payDao.getTotalCount(regionId));
        payPage.setPageSize(pageSize);
        payPage.setPageTotalCount(payPage.getPageTotalCount());
        return payPage;
    }

    @Override
    @CacheEvict(cacheNames = "paymentZone",allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public HandleResult addPayment(paymentDto payDto) {
        exist= payDao.isExist(payDto.getPaymentId());
        if (exist==0){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String nowTime = dateFormat.format(date);
            payDto.setPayDate(nowTime);

            if( payDao.addPayment(new payment(payDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("参合登记成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("参合登记失败!");
            }

            return handle;

        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("登记失败，登记列表已存在!");
            return handle;
        }


    }

    @Override
    @CacheEvict(cacheNames = "paymentZone",allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public HandleResult deletePaymentById(int paymentId) {

        exist= payDao.isExist(paymentId);
        if(exist==1){
            if(  payDao.deletePayment(paymentId) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除参合登记信息成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除参合登记信息失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，登记信息不存在!");
            return handle;
        }


    }

    @Override
    @CacheEvict(cacheNames = "paymentZone",allEntries = true)
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public HandleResult updatePayment(paymentDto payDto)
    {
        exist= payDao.isExist(payDto.getPaymentId());
        if(exist==1){

            if(  payDao.updatePayment( new payment(payDto))){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新登记信息成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新登记信息失败!");
            }


            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，登记信息不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "paymentZone" , key = "'findPaymentById'+#paymentId")
    public paymentDto findPaymentById(int paymentId)
    {

        return  new paymentDto(payDao.findPaymentById(paymentId));
    }
}
