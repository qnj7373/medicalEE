package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.model.dto.policyDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IPolicyService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class policyController {


    @Autowired
    private IPolicyService policyService;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;

    //////////新增
    @PostMapping("/policy")
    @RequiresRoles("县合管办领导")
    public ResponseResult addPolicy(@Validated policyDto pDto) {
            handle=policyService.addPolicy(pDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //////////更新
    @PutMapping("/policy")
    @RequiresRoles("县合管办领导")
    public ResponseResult updatePolicy(@Validated policyDto pDto) {
            handle=policyService.updatePolicy(pDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //编辑前查
    @GetMapping("/policy")
    @RequiresRoles("县合管办领导")
    public ResponseResult queryPolicyById(policyDto pDto) {
            Result.setData(policyService.findPolicyById(pDto.getId()));
            Result.setStatus(ResponseCode.getOkcode());
            Result.setMessage("查找政策信息成功！");
            return Result;
    }


    @DeleteMapping("/policy")
    @RequiresRoles("县合管办领导")
    public ResponseResult deletePolicyById(policyDto pDto) {
            handle=policyService.deletePolicyById(pDto.getId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @GetMapping("/policy/all")
    @RequiresRoles("县合管办领导")
    public ResponseResult getAllPolicy() {
            Result.setData( policyService.getAllPolicy());
            Result.setStatus(ResponseCode.getOkcode());
            Result.setMessage("获取全部政策列表成功！");
            return Result;
    }


    //////////分页查
    @GetMapping("/policy/page")
    @RequiresRoles("县合管办领导")
    public ResponseResult queryPolicyByPage(policyDto pDto) {
            Result.setData(policyService.findPolicyByPage(pDto.getNowPage(), pDto.getPageSize()));
            Result.setStatus(ResponseCode.getOkcode());
            Result.setMessage("获取政策分页列表成功！");
            return Result;
    }

}
