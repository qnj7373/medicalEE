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

    @PostMapping("/expense")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "新增报销信息")
    public ResponseResult addExpense(@Validated  expenseDto eDto) {
            handle=expenseService.addExpense(eDto,Status.getAdministrationId() );
            Result.renderResult(handle);
            return Result;
    }

    @GetMapping("/expense")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人"},logical = Logical.OR)
    @MedicalLog(description = "查找报销信息")
    public ResponseResult queryExpenseById(expenseDto eDto) {

            Result.renderResult(ResponseCode.OK.getCode(),
                    "查找报销信息成功！",
                    expenseService.findExpenseById(eDto.getId())
                    );

            return Result;
    }


    @PutMapping("/expense")
    @RequiresRoles("县合管办经办人")
    @MedicalLog(description = "更新报销信息")
    public ResponseResult updateExpense(expenseDto eDto) {
            handle= expenseService.updateExpenseState(eDto);
            Result.renderResult(handle);
            return Result;
    }


    @DeleteMapping("/expense")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "删除报销信息")
    public ResponseResult deleteExpenseById(expenseDto eDto) {
            handle=expenseService.deleteExpenseById(eDto.getId());
            Result.renderResult(handle);
            return Result;
    }



    @GetMapping("/expense/page")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人"},logical = Logical.OR)
    @MedicalLog(description = "获取报销信息分页列表")
    public ResponseResult queryExpenseByPage(expenseDto eDto) {
            Result.renderResult(ResponseCode.OK.getCode(),
                    "获取报销信息分页列表成功！",
                    expenseService.findExpenseByPage(
                            Status.getAdministrationId(),
                            eDto.getState(),
                            eDto.getNowPage(), eDto.getPageSize()
                    )
            );
            return Result;
    }




}
