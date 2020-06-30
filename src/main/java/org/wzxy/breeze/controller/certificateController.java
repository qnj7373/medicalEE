package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.wzxy.breeze.model.dto.certificateDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.ICertificateService;
import org.wzxy.breeze.service.Iservice.IChronicdisService;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class certificateController {
    @Autowired
    private ICertificateService certService;
    @Autowired
    private IChronicdisService chronicdisService;
    @Autowired
    private getStatusService Status;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;


    //////////分页查

    @GetMapping("/certificate/page")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult queryCertByPage(certificateDto certDto) {
            Result.setData(
           certService.findCertByPage(Status.getMyRegionId(), certDto.getNowPage(), certDto.getPageSize())
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取慢性病证分页列表成功！");
            return Result;
    }


    //////////新增
    @PostMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult addCert(@Validated certificateDto certDto) {
            handle= certService.addCert(certDto,Status.getMyRegionId()) ;
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @DeleteMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult deleteCertById(certificateDto certDto) {
            handle=certService.deleteCertById(certDto.getCertificateId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }



    //编辑前查
    @GetMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult queryCertById(certificateDto certDto) {
            Result.setData( certService.findCertById(certDto.getCertificateId()));
            Result.setDataBackUp(chronicdisService.getAllChronicdis());
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找慢性病证信息成功！");
            return Result;
    }


    //////////更新
    //@PostMapping("/updateCert")
    @PutMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult updateCert(@Validated certificateDto certDto) {
            handle= certService.updateCert(certDto,Status.getMyRegionId());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    //将前台的date数据进行转换
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 这里的转化要和传进来的字符串的格式一致
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

}
