package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.chronicdisDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IChronicdisService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class chronicdisController {

    @Autowired
    private IChronicdisService chronicdisService;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;

    //////////新增
    @PostMapping("/chronicdis")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增慢性病信息")
    public ResponseResult addChronicdis(@Validated chronicdisDto chronDto) {
            handle=chronicdisService.addChronicdis(chronDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @PutMapping("/chronicdis")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新慢性病信息")
    public ResponseResult updateChronicdis(@Validated chronicdisDto chronDto) {
            handle=chronicdisService.updateChronicdis(chronDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @GetMapping("/chronicdis")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "查找慢性病信息")
    public ResponseResult queryChronicdisById(chronicdisDto chronDto) {
            Result.setData(chronicdisService.findChronicdisById(chronDto.getIllcode()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找慢性病信息成功！");
            return Result;
    }


    @DeleteMapping("/chronicdis")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除慢性病信息")
    public ResponseResult deleteChronicdisById(chronicdisDto chronDto) {
            handle=chronicdisService.deleteChronicdisById(chronDto.getIllcode());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @GetMapping("/chronicdis/all")
    @RequiresRoles(value={"乡镇农合经办人","超级管理员"},logical = Logical.OR)
    @MedicalLog(description = "获取全部慢性病列表")
    public ResponseResult getAllChronicdis() {
            Result.setData(chronicdisService.getAllChronicdis());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取全部慢性病列表成功！");
            return Result;
    }


    @GetMapping("/chronicdis/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取慢性病分页列表")
    public ResponseResult queryChronicdisByPage(chronicdisDto chronDto) {
            Result.setData(chronicdisService.findChronicdisByPage(chronDto.getNowPage(), chronDto.getPageSize()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取慢性病分页列表成功！");
            return Result;
    }



}
