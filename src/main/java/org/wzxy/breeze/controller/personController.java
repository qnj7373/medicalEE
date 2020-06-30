package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.wzxy.breeze.model.dto.FamilyDto;
import org.wzxy.breeze.model.dto.PersonDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IPersonService;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@RestController
@RequestMapping("/medical")
public class personController {
    @Autowired
    private IPersonService personService ;
    @Autowired
    private  ResponseResult Result ;
    @Autowired
    private HandleResult handle;
    @Autowired
    private getStatusService Status;


    //////////新增
    @PostMapping("/person")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult addPerson(@Validated PersonDto personDto) {
            handle= personService.addPerson(personDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }

    //////////分页查
    @GetMapping("/person/page")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人","县合管办领导"},logical = Logical.OR)
    public ResponseResult queryPersonByPage(PersonDto personDto) {
            Result.setData(
            personService.findPersonByPage(personDto.getFamicode(), personDto.getNowPage(), personDto.getPageSize())
            );
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("获取家庭成员分页列表成功！");
            return Result;
    }

//编辑前查
    @GetMapping("/person")
    @RequiresRoles(value={"乡镇农合经办人","县合管办经办人","县合管办领导"},logical = Logical.OR)
    public ResponseResult queryPersonById(PersonDto personDto) {
            Result.setData(personService.findPersonById(personDto.getPerscode()));
            Result.setStatus(ResponseCode.OK.getCode());
            Result.setMessage("查找家庭成员信息成功！");
            return Result;
    }

    //参合前查
    @PostMapping("/person/family/holder")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult queryPersonByHolderName(FamilyDto familyDto) {
            List<PersonDto> personByHolder = personService.findPersonByHolder(familyDto.getHolderName(), Status.getMyRegionId());
            if (personByHolder.size()!=0){
                Result.setData(
                        personByHolder
                );
                Result.setDataBackUp(personByHolder.get(0).getFamicode());
                Result.setStatus(ResponseCode.OK.getCode());
                Result.setMessage("根据户主姓名查找家庭成员成功！");
            }else{
                Result.setStatus(ResponseCode.FAIL.getCode());
                Result.setMessage("不存在该户主的家庭成员或该户不在管辖范围内！");
            }

            return Result;
    }

    //报销前查
    @PostMapping("/person/card")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult queryPersonByCardID(PersonDto personDto) {
            List<PersonDto> personByCardID = personService.findPersonByCardID(personDto.getCardID(), Status.getMyRegionId());
            if (personByCardID!=null){
                Result.setData(
                        personByCardID
                );
                Result.setStatus(ResponseCode.OK.getCode());
                Result.setMessage("根据身份证查找家庭成员成功！");
            }else{
                Result.setStatus(ResponseCode.FAIL.getCode());
                Result.setMessage("该身份证不存在或该成员未参合或不在管辖范围内！");
            }

            return Result;
    }


    //////////更新
    @PutMapping("/person")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult updatePerson(@Validated PersonDto personDto) {
            handle= personService.updatePerson(personDto);
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }



    @DeleteMapping("/person")
    @RequiresRoles("乡镇农合经办人")
    public ResponseResult deletePersonById(PersonDto personDto) {
            handle=personService.deletePersonById(personDto.getPerscode());
            Result.setStatus(handle.getStatus());
            Result.setMessage(handle.getMessage());
            return Result;
    }


    //将前台的date数据进行转换
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

}
