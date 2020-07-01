package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.medical;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IMedicalService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class medicalController {


    @Autowired
    private IMedicalService medicalService;
    @Autowired
    private ResponseResult Result ;
    @Autowired
    private HandleResult handle;


    //////////新增
    @PostMapping("/medical")
    @RequiresRoles("超级管理员")
    public ResponseResult addMedical(@Validated medical m) {
            handle=medicalService.addMedical(m);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //////////更新
    @PutMapping("/medical")
    @RequiresRoles("超级管理员")
    public ResponseResult updateMedical(@Validated medical m) {
            handle=medicalService.updateMedical(m);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //编辑前查
    @GetMapping("/medical")
    @RequiresRoles("超级管理员")
    public ResponseResult queryMedicalById(medical m) {
            Result.setData(medicalService.findMedicalById(m.getMedicalId()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找医疗机构成功！");
            return Result;
    }


    @DeleteMapping("/medical")
    @RequiresRoles("超级管理员")
    public ResponseResult deleteMedicalById(medical m) {
            handle=medicalService.deleteMedicalById(m.getMedicalId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @GetMapping("/medical/all")
    @RequiresRoles("超级管理员")
    public ResponseResult getAllMedical() {
            Result.setData( medicalService.getAllMedical());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取全部医疗机构列表成功！");
            return Result;
    }


    //////////分页查
    @GetMapping("/medical/page")
    @RequiresRoles("超级管理员")
    public ResponseResult queryMedicalByPage(medical m) {
            Result.setData(medicalService.findMedicalByPage(m.getNowPage(), m.getPageSize()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取医疗机构分页列表成功！");
            return Result;
    }






}
