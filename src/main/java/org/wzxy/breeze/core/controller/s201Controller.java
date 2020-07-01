package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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


    //////////新增
    @PostMapping("/s201")
    @RequiresRoles("超级管理员")
    public ResponseResult addS201(@Validated s201_xx s) {
            handle=s201Service.addS201_xx(s);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //////////更新
    @PutMapping("/s201")
    @RequiresRoles("超级管理员")
    public ResponseResult updateS201(@Validated s201_xx s) {
            handle=s201Service.updateS201_xx(s);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //编辑前查
    @GetMapping("/s201")
    @RequiresRoles("超级管理员")
    public ResponseResult queryS201ById(s201_xx s) {
            Result.setData(
                    s201Service.findS201_xxById(s)
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找信息成功！");
            return Result;
    }


    @DeleteMapping("/s201")
    @RequiresRoles("超级管理员")
    public ResponseResult deleteChronicdisById(s201_xx s) {
            handle= s201Service.deleteS201_xxById(s.getId(), s.getTable());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @GetMapping("/s201/all")
    @RequiresRoles("超级管理员")
    public ResponseResult getAllChronicdis(s201_xx s) {
            Result.setData(s201Service.getAllS201_xx(s.getTable()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取全部列表成功！");
            return Result;
    }


    //////////分页查
    @GetMapping("/s201/page")
    @RequiresRoles("超级管理员")
    public ResponseResult queryChronicdisByPage(s201_xx s) {
            Result.setData(s201Service.findS201_xxByPage(s.getTable(), s.getNowPage(), s.getPageSize()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取分页列表成功！");
            return Result;
    }




}
