package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
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


    @PostMapping("/medical")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增医疗机构")
    public ResponseResult addMedical(@Validated medical m) {
            handle=medicalService.addMedical(m);
            Result.renderResult(handle);
            return Result;
    }

    @PutMapping("/medical")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新医疗机构")
    public ResponseResult updateMedical(@Validated medical m) {
            handle=medicalService.updateMedical(m);
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/medical")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "查找医疗机构")
    public ResponseResult queryMedicalById(medical m) {
            Result.renderResult(ResponseCode.OK.getCode(),
                    "查找医疗机构成功！",
                    medicalService.findMedicalById(m.getMedicalId()));
            return Result;
    }


    @DeleteMapping("/medical")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除医疗机构")
    public ResponseResult deleteMedicalById(medical m) {
            handle=medicalService.deleteMedicalById(m.getMedicalId());
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/medical/all")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取全部医疗机构列表")
    public ResponseResult getAllMedical() {
            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取全部医疗机构列表成功！",
                    medicalService.getAllMedical());
            return Result;
    }


    @GetMapping("/medical/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取医疗机构分页列表")
    public ResponseResult queryMedicalByPage(medical m) {
            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取医疗机构分页列表成功！",
                    medicalService.findMedicalByPage(
                            m.getNowPage(), m.getPageSize()
                    )
            );
            return Result;
    }






}
