package org.wzxy.breeze.core.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.common.annotation.MedicalLog;
import org.wzxy.breeze.core.model.dto.UserDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.ResponseResult;
import org.wzxy.breeze.core.model.vo.loginUser;
import org.wzxy.breeze.core.service.Iservice.IOrganizationService;
import org.wzxy.breeze.core.service.Iservice.IRoleService;
import org.wzxy.breeze.core.service.Iservice.IUserService;
import org.wzxy.breeze.core.service.serviceImpl.UserServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/medical")
public class UserController  {
	@Autowired
	private  Page<UserDto> userDtopage;
	@Autowired
	private loginUser luser;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrganizationService organService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private  ResponseResult Result;
	@Autowired
	private HandleResult handle ;


	@GetMapping("/user/page")
	@RequiresRoles("超级管理员")
	@MedicalLog(description = "获取用户分页列表")
	public ResponseResult queryUsersByPage(UserDto userDto) {
			userDtopage = userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
			Result.setData(userDtopage);
			Result.setStatus(ResponseCode.OK.getCode());
			Result.setMessage("获取用户分页列表成功！");
			return Result;
	}


	@PostMapping("/user")
	@RequiresRoles("超级管理员")
	@MedicalLog(description = "新增用户")
	public ResponseResult AddUser(@Validated  UserDto userDto) {
			handle=userService.addUser(userDto);
			Result.setStatus(handle.getStatus());
			Result.setMessage(handle.getMessage());
			 return Result;
	}

	@GetMapping("/user")
	@RequiresRoles("超级管理员")
	@MedicalLog(description = "查找用户")
	public ResponseResult queryUserById(UserDto userDto) {
			userDto=userService.queryUserById(userDto.getUid());
			Result.setData(userDto);
			Map<String,Object> map = new HashMap<>();
			map.put("organs", organService.getOrganOfHave(userDto.getUid()));
			map.put("roles", roleService.getRolesOfHave(userDto.getUid()));
			Result.setDataBackUp(map);
			Result.setStatus(ResponseCode.OK.getCode());
			Result.setMessage("查找用户成功！");
			return Result;
	}


    @PutMapping("/user")
	@RequiresRoles("超级管理员")
	@MedicalLog(description = "更新用户")
	public ResponseResult updateUser(@Validated  UserDto userDto) {

			handle=	userService.updateUser(userDto);
			Result.setStatus(handle.getStatus());
			Result.setMessage(handle.getMessage());
			return Result;
	}

	   @DeleteMapping("/user")
	   @RequiresRoles("超级管理员")
	   @MedicalLog(description = "删除用户")
		public ResponseResult deleteUserById(UserDto userDto) {

				handle=userService.deleteUserById(userDto.getUid());
				Result.setStatus(handle.getStatus());
				Result.setMessage(handle.getMessage());
				return Result;
		}


}
