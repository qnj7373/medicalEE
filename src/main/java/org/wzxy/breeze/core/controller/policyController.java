package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.policyDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IPolicyService;

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

    @PostMapping("/policy")
    @RequiresRoles("县合管办领导")
    @MedicalLog(description = "新增报销政策")
    public ResponseResult addPolicy(@Validated policyDto pDto) {
            handle=policyService.addPolicy(pDto);
            Result.renderResult(handle);
            return Result;
    }

    @PutMapping("/policy")
    @RequiresRoles("县合管办领导")
    @MedicalLog(description = "更新报销政策")
    public ResponseResult updatePolicy(@Validated policyDto pDto) {
            handle=policyService.updatePolicy(pDto);
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/policy")
    @RequiresRoles("县合管办领导")
    @MedicalLog(description = "查找政策信息")
    public ResponseResult queryPolicyById(policyDto pDto) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "查找政策信息成功！",
                    policyService.findPolicyById(pDto.getId())
            );
            return Result;
    }


    @DeleteMapping("/policy")
    @RequiresRoles("县合管办领导")
    @MedicalLog(description = "删除报销政策")
    public ResponseResult deletePolicyById(policyDto pDto) {
            handle=policyService.deletePolicyById(pDto.getId());
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/policy/all")
    @RequiresRoles("县合管办领导")
    @MedicalLog(description = "获取全部政策列表")
    public ResponseResult getAllPolicy() {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取全部政策列表成功！",
                    policyService.getAllPolicy()
            );
            return Result;
    }


    @GetMapping("/policy/page")
    @RequiresRoles("县合管办领导")
    @MedicalLog(description = "获取政策分页列表")
    public ResponseResult queryPolicyByPage(policyDto pDto) {
            Result.renderResult(
                    ResponseCode.OK.getCode(),
                    "获取政策分页列表成功！",
                    policyService.findPolicyByPage(
                            pDto.getNowPage(), pDto.getPageSize()
                    )
            );
            return Result;
    }

}
