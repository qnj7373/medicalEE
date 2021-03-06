package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.s201_xx;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IS201_xxService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class s201Controller {

    @Autowired
    private IS201_xxService s201Service;
    @Autowired
    private ResponseResult Result ;
    @Autowired
    private HandleResult handle;


    @PostMapping("/s201")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "新增S201_xx模型信息")
    public ResponseResult addS201(@Validated s201_xx s) {
            handle=s201Service.addS201_xx(s);
            Result.renderResult(handle);
            return Result;
    }

    @PutMapping("/s201")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "更新S201_xx模型信息")
    public ResponseResult updateS201(@Validated s201_xx s) {
            handle=s201Service.updateS201_xx(s);
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/s201")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "查找S201_xx模型信息")
    public ResponseResult queryS201ById(s201_xx s) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "查找信息成功！",
                    s201Service.findS201_xxById(s)
            );
            return Result;
    }


    @DeleteMapping("/s201")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "删除新增S201_xx模型信息")
    public ResponseResult deleteS201ById(s201_xx s) {
            handle= s201Service.deleteS201_xxById(s.getId(), s.getTable());
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/s201/all")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取全部S201_xx模型信息")
    public ResponseResult getAllS201(s201_xx s) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取全部列表成功！",
                    s201Service.getAllS201_xx(s.getTable())
            );
            return Result;
    }


    @GetMapping("/s201/page")
    @RequiresRoles("超级管理员")
    @MedicalLog(description = "获取新增S201_xx模型分页列表")
    public ResponseResult queryS201ByPage(s201_xx s) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取分页列表成功！",
                    s201Service.findS201_xxByPage(
                            s.getTable(),
                            s.getNowPage(),
                            s.getPageSize()
                    )
            );
            return Result;
    }




}
