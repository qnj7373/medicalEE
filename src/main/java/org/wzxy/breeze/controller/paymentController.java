package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.model.dto.paymentDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IPaymentService;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class paymentController {

    @Autowired
    private IPaymentService paymentService;
    @Autowired
    private getStatusService Status;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;

    //////////新增
    @PostMapping("/payment")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult addPayment(@Validated paymentDto payDto) {
            handle=paymentService.addPayment(payDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //////////更新
    @PutMapping("/payment")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult updatePayment(@Validated paymentDto payDto) {
            handle=paymentService.updatePayment(payDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //编辑前查
    @GetMapping("/payment")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult queryPaymentById(paymentDto payDto) {
            Result.setData(
                    paymentService.findPaymentById(payDto.getPaymentId())
                    );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找参合登记信息成功！");
            return Result;
    }


    @DeleteMapping("/payment")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult deletePaymentById(paymentDto payDto) {
            handle=paymentService.deletePaymentById(payDto.getPaymentId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }



    //////////分页查
    @GetMapping("/payment/page")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult queryPaymentByPage(paymentDto payDto) {
            Result.setData(
                    paymentService.findPaymentByPage(Status.getMyRegionId(), payDto.getNowPage(), payDto.getPageSize() ) );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取慢性病分页列表成功！");
            return Result;
    }


}
