package org.wzxy.breeze.service.serviceImpl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.menusMapper;
import org.wzxy.breeze.mapper.rolesMapper;
import org.wzxy.breeze.model.dto.RoleDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.role;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IRoleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-05
 */

@Service
public class RoleServiceImpl  implements IRoleService {
    @Resource
    private rolesMapper rolesDao;
    @Resource
    private menusMapper menuDao;
    @Autowired
    private List<role> allRole ;
    @Autowired
    private List<RoleDto> roleDtoList ;
    @Autowired
    private Page<RoleDto> rolepage;
    @Autowired
    private List<role> roleByUserId;
    private List<Map<String,String>> zTreeList = new ArrayList<>();
    @Autowired
    private HandleResult handle ;
    private int exist=-1;


    @Override
    @Cacheable(value = "roleZone" , key = "'findRoleByPage'+#nowPage+','+#pageSize")
    public Page<RoleDto> findRoleByPage(int nowPage, int pageSize){
        List<role> roles = rolesDao.getRolesByPage(nowPage * pageSize, pageSize);
        roleDtoList.clear();
        for (role r:
        roles) {
            roleDtoList.add(new RoleDto(r) );
        }
        rolepage.setDatas(roleDtoList);
        rolepage.setNowPage(nowPage+1);
        rolepage.setDataTotalCount(rolesDao.getTotalCount());
        rolepage.setPageSize(pageSize);
        rolepage.setPageTotalCount(rolepage.getPageTotalCount());
        return rolepage;
    }

    @Override
    @Cacheable(value = "roleZone" , key = "'findRoleById'+#roleId")
    public RoleDto findRoleById(int roleId) {

        return  new RoleDto(rolesDao.findByRoleId(roleId));
    }

    @Override
    @Caching(evict={@CacheEvict(value = "roleZone", allEntries = true),
            @CacheEvict(value = "menuZone", allEntries = true)})
    public HandleResult addRole(RoleDto roledto) {
        role role = new role(roledto);
        exist=rolesDao.isExist(role.getRoleId());
        if (exist==0){
            rolesDao.addRole(role);
            String[] menuIds = roledto.getMenuIds().split(",");
            for (String menuId:
                    menuIds) {
                if(!"".equals(menuId)){
                    menuDao.addMenuRelation(role.getRoleId(), menuId);
                }
                menuDao.addMenuRelation(role.getRoleId(), menuId);
            }
            handle.setStatus(ResponseCode.getOkcode());
            handle.setMessage("新增角色成功!");
            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("新增失败，角色已存在!");
            return handle;
        }

    }

    @Override
    @Caching(evict={@CacheEvict(value = "roleZone", allEntries = true),
            @CacheEvict(value = "menuZone", allEntries = true)})
    public HandleResult updateRole(RoleDto roledto) {

        exist=rolesDao.isExist(roledto.getRoleId());
        if (exist==1){
            rolesDao.updateRole( new role(roledto));
            String[] split = roledto.getMenuIds().split(",");
            //之前的关系全删
            //新增所有的关系
            if(split.length>1){
                menuDao.deleteMenuRelation(roledto.getRoleId());
                for (String s:
                        split ) {
                    if(!"".equals(s)){
                        menuDao.addMenuRelation(roledto.getRoleId(), s);
                    }

                }
            }
            handle.setStatus(ResponseCode.getOkcode());
            handle.setMessage("更新角色成功!");
            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("更新失败，角色不存在!");
            return handle;
        }



    }

    @Override
    @Caching(evict={@CacheEvict(value = "roleZone", allEntries = true),
            @CacheEvict(value = "menuZone", allEntries = true)})
    public HandleResult deleteRoleById(int roleId) {
        exist=rolesDao.isExist(roleId);
        if (exist==1){
            rolesDao.deleteRoleById(roleId);
            exist=rolesDao.RoleRelationIsExist(roleId);
            if(exist==1){
                menuDao.deleteMenuRelation(roleId);
            }
            handle.setStatus(ResponseCode.getOkcode());
            handle.setMessage("删除角色成功!");
            return handle;
        }else{
            handle.setStatus(ResponseCode.getFailcode());
            handle.setMessage("删除失败，角色不存在!");
            return handle;
        }

    }


    @Override
    @Cacheable(value = "roleZone" , key = "'getTreeOfAdd'")
    public List<Map<String, String>> getTreeOfAdd() {
        allRole = rolesDao.findAllRole();
        zTreeList.clear();
        for (role r:allRole){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", String.valueOf(r.getRoleId()));
            zTreeMap.put("pId", "0");
            zTreeMap.put("name", r.getRoleName());
            zTreeMap.put("open", "true");
            zTreeMap.put("checked", "false");
            zTreeList.add(zTreeMap);
        }
        return zTreeList;
    }

    @Override
    @Cacheable(value = "roleZone" , key = "'getAllRole'")
    public List<role> getAllRole() {
        return rolesDao.findAllRole();
    }

    @Override
    @Cacheable(value = "roleZone" , key = "'getTreeOfHave'+#uid")
    public List<Map<String, String>> getTreeOfHave(int uid) {
        allRole = rolesDao.findAllRole();
        roleByUserId= rolesDao.findRoleByUserId(uid);
        zTreeList.clear();
        for (role r:allRole){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", String.valueOf(r.getRoleId()));
            zTreeMap.put("pId", "0");
            zTreeMap.put("name", r.getRoleName());
            zTreeMap.put("open", "true");

            for (role have:
            roleByUserId) {
                if(r.getRoleId()==have.getRoleId()){
                    zTreeMap.put("checked", "true");
                    break;
                }else{
                     zTreeMap.put("checked", "false");
                }
            }


            zTreeList.add(zTreeMap);
        }

        return zTreeList;
    }

    @Override
    @Cacheable(value = "roleZone" , key = "'getRolesOfHave'+#uid")
    public List<Map<String, String>> getRolesOfHave(int uid) {
        allRole = rolesDao.findAllRole();
        roleByUserId= rolesDao.findRoleByUserId(uid);
        zTreeList.clear();
        for (role r:allRole){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", String.valueOf(r.getRoleId()));
            zTreeMap.put("pId", "0");
            zTreeMap.put("name", r.getRoleName());
            for (role have:
            roleByUserId) {
                if(r.getRoleId()==have.getRoleId()){
                    zTreeMap.put("checked", "true");
                    break;
                }else{
                     zTreeMap.put("checked", "");
                }
            }

            zTreeList.add(zTreeMap);
        }

        return zTreeList;
    }




}
