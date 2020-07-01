package org.wzxy.breeze.core.service.serviceImpl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.core.mapper.organizationMapper;
import org.wzxy.breeze.core.mapper.userMapper;
import org.wzxy.breeze.core.model.dto.OrganizationDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.po.organization;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.service.Iservice.IOrganizationService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private List<OrganizationDto> organDtos;

    @Autowired
    private Page<OrganizationDto> OrganPage;
    @Autowired
    private HandleResult handle ;
    private int exist=-1;
    @Resource
    private  organizationMapper organizationDao;
    @Resource
    private userMapper userDao;
    private List<Map<String,String>> zTreeList = new ArrayList<>();


    @Override
    @Cacheable(value = "organizationZone" , key = "'findOrganByPage'+#nowPage+','+#pageSize")
    public Page<OrganizationDto> findOrganByPage(int nowPage, int pageSize) {
        List<organization> organs = organizationDao.getOrgansByPage(nowPage * pageSize, pageSize);
        organDtos.clear();
        for (organization organ:
                organs) {
            organDtos.add(new OrganizationDto(organ));
        }
        OrganPage.setDatas(organDtos);
        OrganPage.setNowPage(nowPage+1);
        OrganPage.setDataTotalCount(organizationDao.getTotalCount());
        OrganPage.setPageSize(pageSize);
        OrganPage.setPageTotalCount(OrganPage.getPageTotalCount());
        return OrganPage;
    }

    @Override
    @CacheEvict(cacheNames = "organizationZone",allEntries = true)
    public HandleResult addOrgan(OrganizationDto organDto) {

        exist= organizationDao.isXZExist(organDto.getRegionId(), "2");
        if (exist==1){
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，该乡镇农合经办点已存在!");
            return handle;
        }else {
            exist= organizationDao.isExist(organDto.getAdministrationId());
            if (exist==0){
                if ( "0".equals(organDto.getPid()) ) {
                    organDto.setAdministrationPid(Integer.parseInt(organDto.getPid()));
                    organDto.setLevel("1");
                }else{
                    organDto.setAdministrationPid(Integer.parseInt(organDto.getPid()));
                    organization organById =
                            organizationDao.findOrganById(organDto.getAdministrationPid());
                    int i = Integer.parseInt(organById.getLevel());
                    i++;
                    organDto.setLevel(String.valueOf(i));
                    organDto.setOrganizationCode(organById.getOrganizationCode());
                }

                if(  organizationDao.addOrgan(new organization(organDto)) ){
                    handle.setStatus(ResponseCode.OK.getCode());
                    handle.setMessage("新增农合经办点成功!");
                }else{
                    handle.setStatus(ResponseCode.FAIL.getCode());
                    handle.setMessage("新增农合经办点失败!");
                }

                return handle;

            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增失败，农合经办点已存在!");
                return handle;
            }
        }



    }

    @Override
    @CacheEvict(cacheNames = "organizationZone",allEntries = true)
    public HandleResult updateOrgan(OrganizationDto organDto) {
        int tempReg = organizationDao.findOrganById(organDto.getAdministrationId()).getRegionId();
        if (organDto.getRegionId() == tempReg) {
            return  this.update(organDto);
        } else {
            exist = organizationDao.isXZExist(organDto.getRegionId(), "2");
            if (exist == 1) {
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新失败，该乡镇农合经办点已存在!");
                return handle;
            } else {
               return this.update(organDto);

            }
        }

    }

    @Override
    @CacheEvict(cacheNames = "organizationZone",allEntries = true)
    public HandleResult deleteOrganById(int Id)
    {
        exist= organizationDao.isExist(Id);
        if(exist==1){
            if(  organizationDao.deleteOrgan(Id) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("删除农合经办点成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("删除农合经办点失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，农合经办点不存在!");
            return handle;
        }
    }

    @Override
    @Cacheable(value = "organizationZone" , key = "'queryOrganById'+#administrationId")
    public OrganizationDto queryOrganById(int administrationId) {
        organization organById = organizationDao.findOrganById(administrationId);
        if(organById!=null) {
            return   new OrganizationDto(organById);
        }else {
            return null;
        }
    }


    @Override
    @Cacheable(value = "organizationZone" , key = "'getAllOrgans'")
    public List<organization> getAllOrgans() {

        return organizationDao.findAllOrgan();
    }

    @Override
    @Cacheable(value = "organizationZone" , key = "'getTreeOfAdd'")
    public List<Map<String, String>> getTreeOfAdd() {
        List<organization> organs = organizationDao.findAllOrgan();
        zTreeList.clear();
        for (organization o:organs){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", String.valueOf(o.getAdministrationId()));
            zTreeMap.put("pId", "0");
            zTreeMap.put("name", o.getOrganizationName());
            zTreeMap.put("open", "true");
            zTreeMap.put("checked", "false");
            zTreeList.add(zTreeMap);
        }
        return zTreeList;
    }


    @Override
    @Cacheable(value = "organizationZone" , key = "'getTreeOfHave'+#administrationId")
    public List<Map<String, String>> getTreeOfHave(int administrationId) {
        List<organization> organs = organizationDao.findAllOrgan();
        organization organById = organizationDao.findOrganById(administrationId);
        zTreeList.clear();
        for (organization r:organs){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", String.valueOf(r.getAdministrationId()));
            zTreeMap.put("pId", "0");
            zTreeMap.put("name", r.getOrganizationName());
            zTreeMap.put("open", "true");
              if(organById.getAdministrationPid()==r.getAdministrationId()){
                  zTreeMap.put("checked", "true");
              }else{
                  zTreeMap.put("checked", "false");
              }

            zTreeList.add(zTreeMap);
        }

        return zTreeList;
    }

    @Override
    @Cacheable(value = "organizationZone" , key = "'getOrganOfHave'+#uid")
    public List<Map<String, String>> getOrganOfHave(int uid) {
        List<organization> organs = organizationDao.findAllOrgan();
        User userByUid = userDao.findUserByUid(uid);
        zTreeList.clear();
        for (organization r:organs){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", String.valueOf(r.getAdministrationId()));
            zTreeMap.put("pId", "0");
            zTreeMap.put("name", r.getOrganizationName());

            if(userByUid.getAdministrationId()==r.getAdministrationId()){
                zTreeMap.put("checked", "true");
            }else{
                zTreeMap.put("checked", "");
            }

            zTreeList.add(zTreeMap);
        }

        return zTreeList;
    }


    private HandleResult update(OrganizationDto organDto){
        exist = organizationDao.isExist(organDto.getAdministrationId());
        if (exist == 1) {
            if ("0".equals(organDto.getPid())) {
                organDto.setAdministrationPid(Integer.parseInt(organDto.getPid()));
                organDto.setLevel("1");
            } else {
                organDto.setAdministrationPid(Integer.parseInt(organDto.getPid()));
                organization organById =
                        organizationDao.findOrganById(organDto.getAdministrationPid());
                int i = Integer.parseInt(organById.getLevel());
                i++;
                organDto.setLevel(String.valueOf(i));
                organDto.setOrganizationCode(organById.getOrganizationCode());
            }
            if( organizationDao.updateOrgan(new organization(organDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新农合经办点信息成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新农合经办点信息失败!");
            }
            return handle;
        } else {
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，农合经办点不存在!");
            return handle;
        }
    }


}
