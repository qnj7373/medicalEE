package org.wzxy.breeze.core.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.certificateDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.service.Iservice.ICertificateService;
import org.wzxy.breeze.core.service.Iservice.IChronicdisService;
import org.wzxy.breeze.core.service.serviceImpl.getStatusService;

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


    @GetMapping("/certificate/page")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "获取慢性病证分页列表")
    public ResponseResult queryCertByPage(certificateDto certDto) {
        Result.renderResult(ResponseCode.OK.getCode(),
                "获取慢性病证分页列表成功！",
                certService.findCertByPage(Status.getMyRegionId(), certDto.getNowPage(), certDto.getPageSize())
        );
            return Result;
    }


    @PostMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "新增慢性病证")
    public ResponseResult addCert(@Validated certificateDto certDto) {
            handle= certService.addCert(certDto,Status.getMyRegionId()) ;
            Result.renderResult(handle);
            return Result;
    }

    @DeleteMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "删除慢性病证")
    public ResponseResult deleteCertById(certificateDto certDto) {
            handle=certService.deleteCertById(certDto.getCertificateId());
             Result.renderResult(handle);
            return Result;
    }



    @GetMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "查找慢性病证信息")
    public ResponseResult queryCertById(certificateDto certDto) {
            Result.renderResult(ResponseCode.OK.getCode(),
                    "查找慢性病证信息成功！",
                    certService.findCertById(certDto.getCertificateId()),
                    chronicdisService.getAllChronicdis()
                    );
            return Result;
    }


    @PutMapping("/certificate")
    @RequiresRoles("乡镇农合经办人")
    @MedicalLog(description = "更新慢性病证信息")
    public ResponseResult updateCert(@Validated certificateDto certDto) {
            handle= certService.updateCert(certDto,Status.getMyRegionId());
             Result.renderResult(handle);
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
