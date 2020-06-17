package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.model.dto.FamilyDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IFamilyService;
import org.wzxy.breeze.service.Iservice.IRegionService;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class familyController  {
    @Autowired
    private IFamilyService familyService;
    @Autowired
    private IRegionService regionService;
    @Autowired
    private getStatusService Status;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;
    //////////分页查
    @GetMapping("/family/page")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人","县合管办领导"},logical = Logical.OR)
    public ResponseResult queryFamilyByPage(FamilyDto familyDto) {
            Result.setData(
                    familyService.findFamilyByPage(
                    Status.getMyRegionId(), familyDto.getNowPage(), familyDto.getPageSize()
                    )
            );
            Result.setStatus(ResponseCode.getOkcode());
            Result.setMessage("获取家庭档案分页列表成功！");
            return Result;
    }


    //////////新增
    @PostMapping("/family")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult addFamily(@Validated  FamilyDto familyDto) {
            handle= familyService.addFamily(familyDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    @DeleteMapping("/family")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult deleteFamilyById(FamilyDto familyDto) {
            handle=familyService.deleteFamilyById(familyDto.getFamicode());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }



    //编辑前查
    @GetMapping("/family")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人","县合管办领导"},logical = Logical.OR)
    public ResponseResult queryFamilyById(FamilyDto familyDto) {
            Result.setData(familyService.findFamilyById(familyDto.getFamicode()));
            Result.setDataBackUp(regionService.getOwnRegions(Status.getMyRegionId()));
            Result.setStatus(ResponseCode.getOkcode());
            Result.setMessage("查找家庭档案信息成功！");
            return Result;
    }




    //////////更新
    @PutMapping("/family")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult updateFamily(@Validated FamilyDto familyDto) {
            handle= familyService.updateFamily(familyDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

}
