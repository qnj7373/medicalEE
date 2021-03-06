package org.wzxy.breeze.core.controller;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.RoleDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IMenuService;
import org.wzxy.breeze.core.service.Iservice.IOrganizationService;
import org.wzxy.breeze.core.service.Iservice.IRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-05
 */
@RestController
@RequestMapping("/medical")
public class roleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IOrganizationService organService;
    private  List<Map<String,String>> zTreeList = new ArrayList<>();
    private  Map<String,String> zTreeMap = new HashedMap();
    @Autowired
    private  ResponseResult Result ;
   @Autowired
    private HandleResult handle ;

    @PostMapping("/role")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增角色")
    public ResponseResult addRole(@Validated RoleDto roleDto) {
            handle= roleService.addRole(roleDto);
            Result.renderResult(handle);
            return Result;
    }


    @GetMapping("/role")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取角色信息")
    public ResponseResult queryRoleById(RoleDto roleDto) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取修改角色所需信息成功！",
                    roleService.findRoleById(roleDto.getRoleId()),
                    menuService.getTreeOfHave(roleDto.getRoleId())
            );
            return Result;
    }


    @PutMapping("/role")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新角色")
    public ResponseResult updateRole(@Validated RoleDto roleDto) {

            handle= roleService.updateRole(roleDto);
            Result.renderResult(handle);
            return Result;
    }


    @DeleteMapping("/role")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除角色")
    public ResponseResult deleteRoleById(RoleDto roleDto) {
            handle=roleService.deleteRoleById(roleDto.getRoleId());
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/role/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取角色分页列表")
    public ResponseResult getRolesPage(RoleDto roleDto) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取角色列表成功！",
                    roleService.findRoleByPage
                            (
                             roleDto.getNowPage(),roleDto.getPageSize()
                            )
            );
            return Result;
    }



    @GetMapping("/role/toadd")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取新增用户所需信息")
    public ResponseResult getRolesToAdd() {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取新增用户所需信息成功！",
                    roleService.getAllRole(),
                    organService.getAllOrgans()
            );
            return Result;
    }



}
