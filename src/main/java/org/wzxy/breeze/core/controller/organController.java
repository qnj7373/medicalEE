package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
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

    @GetMapping("/organization/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取农合经办点分页列表")
    public ResponseResult queryOrgansByPage(OrganizationDto organDto) {
        Result.renderResult(ResponseCode.OK.getCode(),
                "获取农合经办点分页列表成功！",
                organService.findOrganByPage(
                        organDto.getNowPage(),organDto.getPageSize()
                )
                );
            return Result;

    }


    @GetMapping("/organization/all")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取全部机构")
    public ResponseResult getAllOrgans() {
        Result.renderResult(ResponseCode.OK.getCode(),
                "获取全部机构成功！",
                organService.getAllOrgans(),
                regionService.getAllRegions()
        );
            return Result;
    }

    @GetMapping("/organization/tree/add")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取机构树")
    public ResponseResult getOrgans() {
        Result.renderResult(ResponseCode.OK.getCode(),
                "获取机构树成功！",
                organService.getTreeOfAdd()
        );
            return Result;
    }

    @PostMapping("/organization")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增机构")
    public ResponseResult AddOrgan(@Validated OrganizationDto organDto) {
            handle=organService.addOrgan(organDto);
            Result.renderResult(handle);
            return Result;
    }


    @GetMapping("/organization")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "查找机构")
    public ResponseResult queryOrganById(OrganizationDto organDto) {
            Map<String,Object> map = new HashMap<>();
            map.put("organs", organService.getAllOrgans());
            map.put("regions", regionService.getAllRegions());
            Result.renderResult(ResponseCode.OK.getCode(),
                    "查找农合经办点成功！",
                    organService.queryOrganById(organDto.getAdministrationId()),
                    map
            );
            return Result;
    }


    @PutMapping("/organization")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新机构")
    public ResponseResult updateOrgan(@Validated OrganizationDto organDto) {
            handle= organService.updateOrgan(organDto);
            Result.renderResult(handle);
            return Result;
    }


    @DeleteMapping("/organization")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除机构")
    public ResponseResult deleteOrgan(OrganizationDto organDto) {
            handle= organService.deleteOrganById(organDto.getAdministrationId());
            Result.renderResult(handle);
            return Result;
    }

}
