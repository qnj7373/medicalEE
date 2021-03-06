package org.wzxy.breeze.core.service.serviceImpl;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wzxy.breeze.common.utils.MedicalStringUtils;
import org.wzxy.breeze.common.utils.PwdEncryption;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.mapper.rolesMapper;
import org.wzxy.breeze.core.mapper.userMapper;
import org.wzxy.breeze.core.model.dto.UserDto;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.model.vo.loginUser;
import org.wzxy.breeze.core.service.Iservice.IUserService;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl  implements IUserService {
	@Autowired
	private loginUser luser;
	@Autowired
	private List<User> UserList ;
	@Autowired
	private List<UserDto> UserDtoList ;
	@Autowired
	private Page<User> Userpage;
	@Autowired
	private Page<UserDto> userDtopage;
	@Autowired
	private User user;
	@Resource
	private userMapper userDao;
	@Resource
	private rolesMapper rolesDao;
	@Autowired
	private HandleResult handle ;
	@Resource
	private RedisTemplate<String,Object> redisTemplate;
	private int exist=-1;

	@Override
	public List<User> findUserByFactor(User user) {
		UserList.clear();
		UserList=userDao.findUserByFactor(user);
		return UserList;
	}



//////////////////////============+++++++++=============//////////////////////////
/////系统用户的业务逻辑

	@Override
	@Caching(evict={@CacheEvict(value = "userZone", allEntries = true),
			@CacheEvict(value = "menuZone", allEntries = true)})
	@Transactional(rollbackFor = {RuntimeException.class,Error.class})
	public HandleResult deleteUserById(int uid) {

		exist=userDao.isExist(uid);
		if (exist==1){
			user=userDao.queryUserById(uid);
				if(userDao.deleteUserById(user)){
					handle.setStatus(ResponseCode.OK.getCode());
					handle.setMessage("删除用户成功!");
				}else{
					handle.setStatus(ResponseCode.FAIL.getCode());
					handle.setMessage("删除用户失败!");
				}
			exist=userDao.UserRelationIsExist(uid);
			if (exist==1){
				rolesDao.deleteRoleRelation(uid);
			}

			return handle;
		}else{
			handle.setStatus(ResponseCode.FAIL.getCode());
			handle.setMessage("删除失败，用户不存在!");
			return handle;
		}

	}



	@Override
	@Cacheable(value = "userZone" , key = "'queryUserById'+#uid")
	public UserDto queryUserById(int uid) {
		user=userDao.queryUserById(uid);
		String[] rId=rolesDao.findRoleIdByUserId(uid);
		if(user!=null) {
			UserDto userDto =  new UserDto(user);
			userDto.setrId(rId);
			return userDto;
		}else {
			return null;
		}

	}

	@Override
	@Caching(evict={@CacheEvict(value = "userZone", allEntries = true),
			@CacheEvict(value = "menuZone", allEntries = true)})
	@Transactional(rollbackFor = {RuntimeException.class,Error.class})
	public HandleResult updateUser(UserDto userdto)
	{

		exist=userDao.isExist(userdto.getUid());
		String tempistrationId = userdto.getTempistrationId();
		if (exist==1){
			if(!("".equals(tempistrationId))){
				userdto.setAdministrationId(Integer.parseInt(tempistrationId));
			}
			if(userDao.updateUser(new User(userdto))){
				handle.setStatus(ResponseCode.OK.getCode());
				handle.setMessage("更新用户成功!");
			}else{
				handle.setStatus(ResponseCode.FAIL.getCode());
				handle.setMessage("更新用户失败!");
			}

			String[] split = userdto.getRoleIds().split(",");
			//之前的关系全删
			//新增所有的关系
			if(split.length>0){
				rolesDao.deleteRoleRelation(userdto.getUid());
				for (String s:
						split ) {
					if(!"".equals(s)){
						rolesDao.addRoleRelation(userdto.getUid(), Integer.parseInt(s));
					}
				}
			}

			return handle;

		}else{
			handle.setStatus(ResponseCode.FAIL.getCode());
			handle.setMessage("更新失败，用户不存在!");
			return handle;
		}


	}

	@Override
	@CacheEvict(cacheNames = "userZone",allEntries = true)
	@Transactional(rollbackFor = {RuntimeException.class,Error.class})
	public HandleResult resetPassword(UserDto userdto) {
		exist=userDao.isExist(userdto.getUid());
		if (exist==1){
			User resetUser =  new User(userdto);
			String salt =  new SecureRandomNumberGenerator().nextBytes().toString();
			resetUser.setUpwd(PwdEncryption.Encrypt(userdto.getUpwd(), salt));
			resetUser.setSalt(salt);
			if(userDao.resetPassword(resetUser)){
				handle.setStatus(ResponseCode.OK.getCode());
				handle.setMessage("重置用户密码成功!");
			}else{
				handle.setStatus(ResponseCode.FAIL.getCode());
				handle.setMessage("重置用户密码失败!");
			}
			return handle;

		}else{
			handle.setStatus(ResponseCode.FAIL.getCode());
			handle.setMessage("重置密码失败，用户不存在!");
			return handle;
		}
	}

	@Override
	public HandleResult userLogout(String token) {
		if (!MedicalStringUtils.isNull(token)&&MedicalStringUtils.isBlank(token)){
			redisTemplate.delete(token);
			handle.setStatus(ResponseCode.OK.getCode());
			handle.setMessage("退出登录成功!");
		}else{
			handle.setStatus(ResponseCode.FAIL.getCode());
			handle.setMessage("退出登录失败!你还没有登录!");
		}
		return handle;
	}

	@Override
	public boolean hasLogin(String loginUid) {
		Long expire = redisTemplate.getExpire(loginUid);
		if (expire>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void exitUser(String loginUid) {
		String token=redisTemplate.opsForValue().get(loginUid).toString();
		redisTemplate.delete(token);
		redisTemplate.delete(loginUid);
		return;
	}

	@Override
	@CacheEvict(cacheNames = "userZone",allEntries = true)
	@Transactional(rollbackFor = {RuntimeException.class,Error.class})
	public HandleResult addUser(UserDto userdto) {
		exist=userDao.isExist(userdto.getUid());
		if (exist==0){
			userdto.setAdministrationId(Integer.parseInt(userdto.getTempistrationId()));
			User addUser =  new User(userdto);
			String salt =  new SecureRandomNumberGenerator().nextBytes().toString();
			addUser.setUpwd(PwdEncryption.Encrypt(userdto.getUpwd(), salt));
			addUser.setSalt(salt);
				if(userDao.addUser(addUser)){
					handle.setStatus(ResponseCode.OK.getCode());
					handle.setMessage("新增用户成功!");
				}else{
					handle.setStatus(ResponseCode.FAIL.getCode());
					handle.setMessage("新增用户失败!");
				}
			String[] roleIds = userdto.getRoleIds().split(",");
			for (String s:
					roleIds) {
				if(!"".equals(s)){
					rolesDao.addRoleRelation(addUser.getUid(), Integer.parseInt(s));
				}

			}

			return handle;

		}else{
			handle.setStatus(ResponseCode.FAIL.getCode());
			handle.setMessage("新增失败，用户已存在!");
			return handle;
		}

	}


	@Override
	@Cacheable(value = "userZone" , key = "'UserPaging'+#nowPage+','+#pageSize")
	public Page<UserDto> UserPaging(int nowPage, int pageSize) {
		List<User> usersByPage = userDao.getUsersByPage(nowPage * pageSize, pageSize);
		UserDtoList.clear();
		for (User u:
		usersByPage) {
			UserDtoList.add( new UserDto(u));
		}
		userDtopage.setDatas(UserDtoList);
		userDtopage.setNowPage(nowPage+1);
		userDtopage.setDataTotalCount(userDao.getTotalCount());
		userDtopage.setPageSize(pageSize);
		userDtopage.setPageTotalCount(userDtopage.getPageTotalCount());
	return userDtopage;
	}



	/////系统用户的业务逻辑末

}
