package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.expenseDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.IExpenseService;
import org.wzxy.breeze.core.service.serviceImpl.getStatusService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class expenseController {


    @Autowired
    private IExpenseService expenseService;
    @Autowired
    private getStatusService Status;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;

    //////////新增
    @PostMapping("/expense")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "新增报销信息")
    public ResponseResult addExpense(@Validated  expenseDto eDto) {
            handle=expenseService.addExpense(eDto,Status.getAdministrationId() );
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //编辑前查
    @GetMapping("/expense")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人"},logical = Logical.OR)
    @MedicalLog(description = "查找报销信息")
    public ResponseResult queryExpenseById(expenseDto eDto) {
            Result.setData(
             expenseService.findExpenseById(eDto.getId())
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找报销信息成功！");
            return Result;
    }


    //////////更新
    @PutMapping("/expense")
    @RequiresRoles("县合管办经办人")
    @MedicalLog(description = "更新报销信息")
    public ResponseResult updateExpense(expenseDto eDto) {
            handle= expenseService.updateExpenseState(eDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    @DeleteMapping("/expense")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "删除报销信息")
    public ResponseResult deleteExpenseById(expenseDto eDto) {
            handle=expenseService.deleteExpenseById(eDto.getId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }



    //////////分页查
    @GetMapping("/expense/page")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人"},logical = Logical.OR)
    @MedicalLog(description = "获取报销信息分页列表")
    public ResponseResult queryExpenseByPage(expenseDto eDto) {
            Result.setData(
                    expenseService.findExpenseByPage(
                            Status.getAdministrationId(),
                            eDto.getState(),
                            eDto.getNowPage(), eDto.getPageSize()
                    )
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取报销信息分页列表成功！");
            return Result;
    }




}
