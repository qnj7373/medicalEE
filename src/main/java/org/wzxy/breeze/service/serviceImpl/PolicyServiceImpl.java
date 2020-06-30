package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.policyMapper;
import org.wzxy.breeze.model.dto.chronicdisDto;
import org.wzxy.breeze.model.dto.paymentDto;
import org.wzxy.breeze.model.dto.policyDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.chronicdis;
import org.wzxy.breeze.model.po.policy;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IPaymentService;
import org.wzxy.breeze.service.Iservice.IPolicyService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class PolicyServiceImpl implements IPolicyService {

    @Resource
    private policyMapper  policyDao;

    @Autowired
    private List<policyDto> policyDtoList;

    @Autowired
    private Page<policyDto> policyDtoPage;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;

    @Override
    @Cacheable(value = "policyZone" , key = "'getAllPolicy'")
    public List<policy> getAllPolicy() {
        return policyDao.getAllPolicy();
    }

    @Override
    @Cacheable(value = "policyZone" , key = "'findPolicyByPage'+#nowPage+','+#pageSize")
    public Page<policyDto> findPolicyByPage(int nowPage, int pageSize) {
        List<policy> policyByPage = policyDao.getPolicyByPage(nowPage * pageSize, pageSize);
        policyDtoList.clear();
        for (policy p:
                policyByPage) {
            policyDtoList.add(new policyDto(p));
        }
        policyDtoPage.setDatas(policyDtoList);
        policyDtoPage.setNowPage(nowPage+1);
        policyDtoPage.setDataTotalCount(policyDao.getTotalCount());
        policyDtoPage.setPageSize(pageSize);
        policyDtoPage.setPageTotalCount(policyDtoPage.getPageTotalCount());
        return policyDtoPage;
    }

    @Override
    @CacheEvict(cacheNames = "policyZone",allEntries = true)
    public HandleResult addPolicy(policyDto pDto) {
        exist=policyDao.isExist(pDto.getId());
        if(exist==0){
            if(policyDao.addPolicy(new policy(pDto))){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("新增政策成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增政策失败!");
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，政策已存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "policyZone",allEntries = true)
    public HandleResult updatePolicy(policyDto pDto) {
        exist=policyDao.isExist(pDto.getId());
        if(exist==1){
            if(policyDao.updatePolicy(new policy(pDto))){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新政策成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新政策失败!");
            }
            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，政策不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "policyZone" , key = "'findPolicyById'+#id")
    public policyDto findPolicyById(int id) {
        return  new policyDto(policyDao.findPolicyById(id));
    }

    @Override
    @CacheEvict(cacheNames = "policyZone",allEntries = true)
    public HandleResult deletePolicyById(int id) {
        exist=policyDao.isExist(id);
        if(exist==1){
            if(policyDao.deletePolicy(id)){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除政策成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除政策失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，政策不存在!");
            return handle;
        }
    }
}
