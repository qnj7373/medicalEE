package org.wzxy.breeze.controller;


import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.model.vo.loginUser;
import org.wzxy.breeze.service.Iservice.IOrganizationService;
import org.wzxy.breeze.service.Iservice.IRoleService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.UserServiceImpl;

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
////////////////////
//////////分页查
	@GetMapping("/user/page")
	@RequiresRoles("超级管理员")
	public ResponseResult queryUsersByPage(UserDto userDto) {
			userDtopage = userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
			Result.setData(userDtopage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取用户分页列表成功！");
			return Result;
	}


	//////////新增
	@PostMapping("/user")
	@RequiresRoles("超级管理员")
	public ResponseResult AddUser(@Validated  UserDto userDto) {
			handle=userService.addUser(userDto);
			Result.setStatus(handle.getStatus());
			Result.setMessage(handle.getMessage());
			 return Result;
	}

	//查
	@GetMapping("/user")
	@RequiresRoles("超级管理员")
	public ResponseResult queryUserById(UserDto userDto) {
			userDto=userService.queryUserById(userDto.getUid());
			Result.setData(userDto);
			Map<String,Object> map = new HashMap<>();
			map.put("organs", organService.getOrganOfHave(userDto.getUid()));
			map.put("roles", roleService.getRolesOfHave(userDto.getUid()));
			Result.setDataBackUp(map);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("查找用户成功！");
			return Result;
	}

	//更新
    @PutMapping("/user")
	@RequiresRoles("超级管理员")
	public ResponseResult updateUser(@Validated  UserDto userDto) {

			handle=	userService.updateUser(userDto);
			Result.setStatus(handle.getStatus());
			Result.setMessage(handle.getMessage());
			return Result;
	}

	//删除
	   @DeleteMapping("/user")
	   @RequiresRoles("超级管理员")
		public ResponseResult deleteUserById(UserDto userDto) {

				handle=userService.deleteUserById(userDto.getUid());
				Result.setStatus(handle.getStatus());
				Result.setMessage(handle.getMessage());
				return Result;
		}






	///////////////////////////

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public loginUser getLuser() {
		return luser;
	}

	public void setLuser(loginUser luser) {
		this.luser = luser;
	}




}
