package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.core.model.dto.OrganizationDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IOrganizationService;
import org.wzxy.breeze.core.service.Iservice.IRegionService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class organController  {
    @Autowired
    private IOrganizationService organService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private  ResponseResult Result;
    @Autowired
    private HandleResult handle ;

    //////////分页查
    @GetMapping("/organization/page")
    @RequiresRoles("超级管理员")
    public ResponseResult queryOrgansByPage(OrganizationDto organDto) {
            Result.setData(
               organService.findOrganByPage(organDto.getNowPage(),organDto.getPageSize() )
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取农合经办点分页列表成功！");
            return Result;

    }


    @GetMapping("/organization/all")
    @RequiresRoles("超级管理员")
    public ResponseResult getAllOrgans() {
            Result.setData(organService.getAllOrgans());
            Result.setDataBackUp(regionService.getAllRegions());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取全部机构成功！");
            return Result;
    }

    @GetMapping("/organization/tree/add")
    @RequiresRoles("超级管理员")
    public ResponseResult getOrgans() {
            Result.setData(organService.getTreeOfAdd());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取机构树成功！");
            return Result;
    }

    //////////新增
    @PostMapping("/organization")
    @RequiresRoles("超级管理员")
    public ResponseResult AddOrgan(@Validated OrganizationDto organDto) {
            handle=organService.addOrgan(organDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    //编辑前查
    @GetMapping("/organization")
    @RequiresRoles("超级管理员")
    public ResponseResult queryOrganById(OrganizationDto organDto) {
            Result.setData(organService.queryOrganById(organDto.getAdministrationId()));
            Map<String,Object> map = new HashMap<>();
            map.put("organs", organService.getAllOrgans());
            map.put("regions", regionService.getAllRegions());
            Result.setDataBackUp(map);
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找农合经办点成功！");
            return Result;
    }


    //////////更新
    @PutMapping("/organization")
    @RequiresRoles("超级管理员")
    public ResponseResult updateOrgan(@Validated OrganizationDto organDto) {
            handle= organService.updateOrgan(organDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    @DeleteMapping("/organization")
    @RequiresRoles("超级管理员")
    public ResponseResult deleteOrgan(OrganizationDto organDto) {
            handle= organService.deleteOrganById(organDto.getAdministrationId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

}
