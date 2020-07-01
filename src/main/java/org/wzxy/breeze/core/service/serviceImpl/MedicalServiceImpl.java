package org.wzxy.breeze.core.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.core.mapper.medicalMapper;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.medical;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.service.Iservice.IMedicalService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class MedicalServiceImpl implements IMedicalService {

    @Resource
    private medicalMapper medicalDao;

    @Autowired
    private List<medical>  medicalList;

    @Autowired
    private Page<medical>   medicalPage;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;


    @Override
    @Cacheable(value = "medicalZone" , key = "'getAllMedical'")
    public List<medical> getAllMedical() {
        return medicalDao.getAll();
    }

    @Override
    @Cacheable(value = "medicalZone" , key = "'findMedicalByPage'+#nowPage+','+#pageSize")
    public Page<medical> findMedicalByPage(int nowPage, int pageSize) {
        medicalList=medicalDao.getByPage(nowPage*pageSize, pageSize);
        medicalPage.setDatas(medicalList);
        medicalPage.setNowPage(nowPage+1);
        medicalPage.setDataTotalCount(medicalDao.getTotalCount());
        medicalPage.setPageSize(pageSize);
        medicalPage.setPageTotalCount(medicalPage.getPageTotalCount());
        return medicalPage;
    }

    @Override
    @CacheEvict(cacheNames = "medicalZone",allEntries = true)
    public HandleResult addMedical(medical m) {
        exist=medicalDao.isExist(m.getMedicalId());
        if(exist==0){
            if(  medicalDao.add(m) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("新增医疗机构成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增医疗机构失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，医疗机构已存在!");
            return handle;
        }
    }

    @Override
    @CacheEvict(cacheNames = "medicalZone",allEntries = true)
    public HandleResult updateMedical(medical m) {
        exist=medicalDao.isExist(m.getMedicalId());
        if(exist==1){

            if(  medicalDao.update(m) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新医疗机构成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新医疗机构失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，医疗机构不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "medicalZone" , key = "'findMedicalById'+#medicalId")
    public medical findMedicalById(int medicalId) {
        return medicalDao.findById(medicalId);
    }

    @Override
    @CacheEvict(cacheNames = "medicalZone",allEntries = true)
    public HandleResult deleteMedicalById(int medicalId) {
        exist=medicalDao.isExist(medicalId);
        if(exist==1){
            if(   medicalDao.delete(medicalId)){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除医疗机构成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除医疗机构失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，医疗机构不存在!");
            return handle;
        }
    }
}
