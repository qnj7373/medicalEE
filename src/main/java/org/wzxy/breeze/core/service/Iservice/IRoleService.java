package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.RoleDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.role;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-05
 */
public interface IRoleService {

    List<Map<String,String>> getTreeOfAdd();

    List<role> getAllRole();

    List<Map<String,String>> getTreeOfHave(int userId);

    List<Map<String, String>> getRolesOfHave(int uid);

    Page<RoleDto> findRoleByPage(int nowPage, int pageSize);

    RoleDto findRoleById(int roleId);

    HandleResult addRole(RoleDto roledto) ;

    HandleResult updateRole(RoleDto roledto) ;

    HandleResult deleteRoleById(int roleId);

}
