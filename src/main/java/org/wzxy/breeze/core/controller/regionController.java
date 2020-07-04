package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.RegionDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IRegionService;
import org.wzxy.breeze.core.service.serviceImpl.getStatusService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class regionController {

    @Autowired
    private IRegionService regionService;
    @Autowired
    private ResponseResult Result;
    @Autowired
    private HandleResult handle ;
    @Autowired
    private getStatusService Status;

    @GetMapping("/region/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取区域分页列表")
    public ResponseResult queryRegionsByPage(RegionDto regionDto) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取区域分页列表成功！",
                    regionService.findRegionByPage(
                            regionDto.getNowPage(),regionDto.getPageSize()
                    )
            );
            return Result;

    }


    @GetMapping("/region/all")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取全部区域")
    public ResponseResult getAllRegions() {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取全部区域成功！",
                    regionService.getAllRegions()
            );
            return Result;
    }


    @GetMapping("/region/own")
    @RequiresRoles(value={"乡镇农合经办人","超级管理员"},logical = Logical.OR)
    @MedicalLog(description = "获取乡镇相关区域")
    public ResponseResult getOwnRegions() {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取乡镇相关区域成功！",
                    regionService.getOwnRegions(Status.getMyRegionId())
            );
            return Result;
    }

    @PostMapping("/region")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增区域信息")
    public ResponseResult AddRegion(@Validated RegionDto regionDto) {
            handle=regionService.addRegion(regionDto);
            Result.renderResult(handle);
            return Result;
    }


    @GetMapping("/region")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "查找区域信息")
    public ResponseResult queryRegionById(RegionDto regionDto) {

            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "查找区域成功！",
                    regionService.queryRegionById(regionDto.getRegionId()),
                    regionService.getAllRegions()
            );
            return Result;
    }


    @PutMapping("/region")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新区域信息")
    public ResponseResult updateRegion(@Validated RegionDto regionDto) {
            handle= regionService.updateRegion(regionDto);
            Result.renderResult(handle);
            return Result;
    }


    @DeleteMapping("/region")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除区域信息")
    public ResponseResult deleteRegion(RegionDto regionDto) {
            handle= regionService.deleteRegionById(regionDto.getRegionId());
            Result.renderResult(handle);
            return Result;
    }

}
