package org.wzxy.breeze.core.controller;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    //////////新增
    @PostMapping("/role")
    @RequiresRoles("超级管理员")
    public ResponseResult addRole(@Validated RoleDto roleDto) {
            handle= roleService.addRole(roleDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }
    //编辑前查
    @GetMapping("/role")
    @RequiresRoles("超级管理员")
    public ResponseResult queryRoleById(RoleDto roleDto) {
            Result.setData(roleService.findRoleById(roleDto.getRoleId()));
            Result.setDataBackUp(menuService.getTreeOfHave(roleDto.getRoleId()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取修改角色所需信息成功！");
            return Result;
    }

    @PutMapping("/role")
    @RequiresRoles("超级管理员")
    public ResponseResult updateRole(@Validated RoleDto roleDto) {

            handle= roleService.updateRole(roleDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    @DeleteMapping("/role")
    @RequiresRoles("超级管理员")
    public ResponseResult deleteRoleById(RoleDto roleDto) {
            handle=roleService.deleteRoleById(roleDto.getRoleId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @GetMapping("/role/page")
    @RequiresRoles("超级管理员")
    public ResponseResult getRolesPage(RoleDto roleDto) {
            Result.setData(
                    roleService.findRoleByPage
                            (roleDto.getNowPage(),roleDto.getPageSize())
                    );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取角色列表成功！");
            return Result;
    }



    @GetMapping("/role/toadd")
    @RequiresRoles("超级管理员")
    public ResponseResult getRolesToAdd() {
            Result.setData(roleService.getAllRole());
            Result.setDataBackUp(organService.getAllOrgans());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取新增用户所需信息成功！");
            return Result;
    }







}
