package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    //////////分页查
    @GetMapping("/region/page")
    @RequiresRoles("超级管理员")
    public ResponseResult queryRegionsByPage(RegionDto regionDto) {
            Result.setData(
                    regionService.findRegionByPage(regionDto.getNowPage(),regionDto.getPageSize())
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取区域分页列表成功！");
            return Result;

    }


    @GetMapping("/region/all")
    @RequiresRoles("超级管理员")
    public ResponseResult getAllRegions() {
            Result.setData(regionService.getAllRegions());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取全部区域成功！");
            return Result;
    }


    @GetMapping("/region/own")
    @RequiresRoles(value={"乡镇农合经办人","超级管理员"},logical = Logical.OR)
    public ResponseResult getOwnRegions() {
            Result.setData(regionService.getOwnRegions(Status.getMyRegionId()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取乡镇相关区域成功！");
            return Result;
    }

    //////////新增
    @PostMapping("/region")
    @RequiresRoles("超级管理员")
    public ResponseResult AddRegion(@Validated RegionDto regionDto) {
            handle=regionService.addRegion(regionDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    //编辑前查
    @GetMapping("/region")
    @RequiresRoles("超级管理员")
    public ResponseResult queryRegionById(RegionDto regionDto) {

            Result.setData(regionService.queryRegionById(regionDto.getRegionId()));
            Result.setDataBackUp(regionService.getAllRegions());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找区域成功！");
            return Result;
    }


    //////////更新
    @PutMapping("/region")
    @RequiresRoles("超级管理员")
    public ResponseResult updateRegion(@Validated RegionDto regionDto) {
            handle= regionService.updateRegion(regionDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    @DeleteMapping("/region")
    @RequiresRoles("超级管理员")
    public ResponseResult deleteRegion(RegionDto regionDto) {
            handle= regionService.deleteRegionById(regionDto.getRegionId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

}
