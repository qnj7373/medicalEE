package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.familyMapper;
import org.wzxy.breeze.mapper.personMapper;
import org.wzxy.breeze.model.dto.FamilyDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.family;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IFamilyService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class FamilyServiceImpl implements IFamilyService {
    @Resource
    private familyMapper familyDao;
    @Resource
    private personMapper personDao;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;
    @Autowired
    private List<FamilyDto> familyDtoList;
    @Autowired
    private Page<FamilyDto> familyPage;
    @Autowired
    private  FamilyDto familyDto;


    @Override
    @Cacheable(value = "familyZone" , key = "'findFamilyByPage'+#regionId+','+#nowPage+','+#pageSize")
    public Page<FamilyDto> findFamilyByPage(int regionId, int nowPage, int pageSize) {
        List<family> familysByPage = familyDao.getFamilysByPage(regionId,nowPage * pageSize, pageSize);
        familyDtoList.clear();
        for (family f:
                familysByPage) {
            familyDtoList.add(new FamilyDto(f));
        }
        familyPage.setDatas(familyDtoList);
        familyPage.setNowPage(nowPage+1);
        familyPage.setDataTotalCount(familyDao.getTotalCount(regionId));
        familyPage.setPageSize(pageSize);
        familyPage.setPageTotalCount(familyPage.getPageTotalCount());
        return familyPage;
    }

    @Override
    @CacheEvict(cacheNames = "familyZone",allEntries = true)
    public HandleResult addFamily(FamilyDto familyDto) {

        exist=familyDao.isExist(familyDto.getFamicode());
        if (exist==0){
            familyDto.setPopuNum(1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String nowTime = dateFormat.format(date);
            familyDto.setCreattime(nowTime);
            if( familyDao.addFamily(new family(familyDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("新增家庭档案成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增家庭档案失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，家庭档案已存在!");
            return handle;
        }
    }



    @Override
    @CacheEvict(cacheNames = "familyZone",allEntries = true)
    public HandleResult deleteFamilyById(int  famicode){
        exist=familyDao.isExist(famicode);
        if(exist==1){
            if( familyDao.deleteFamily(famicode) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除家庭档案成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除家庭档案失败!");
            }
                ///还需删除家庭成员信息
             exist= personDao.familyIsExist(famicode);
            if(exist==1){
                personDao.familyIsExist(famicode);
                personDao.deletePersonOfFamily(famicode);
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，家庭档案不存在!");
            return handle;
        }
    }



    @Override
    @Cacheable(value = "familyZone" , key = "'findFamilyById'+#famicode")
    public FamilyDto findFamilyById(int  famicode) {
        family familyByFamicode = familyDao.findFamilyByFamicode(famicode);
        familyDto = new FamilyDto(familyByFamicode) ;
        if(familyByFamicode.getVillage()!=null){
            familyDto.setRegionName( familyByFamicode.getVillage().getRegionName());
        }
        return familyDto;
    }



    @Override
    @CacheEvict(cacheNames = "familyZone",allEntries = true)
    public HandleResult updateFamily(FamilyDto familyDto) {
        exist=familyDao.isExist(familyDto.getFamicode());
        if(exist==1){
            //更新家庭后记得把个人户主也更新
            if(  familyDao.updateFamily(new family(familyDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新家庭档案成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新家庭档案失败!");
            }
            exist= personDao.familyIsExist(familyDto.getFamicode());
            if(exist==1){
                personDao.upDateholder(familyDto.getFamicode(),"户主",familyDto.getHolderName());
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，家庭档案不存在!");
            return handle;
        }

    }

}
