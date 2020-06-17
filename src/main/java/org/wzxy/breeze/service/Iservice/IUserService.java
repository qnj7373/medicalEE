package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IUserService {


   public  HandleResult deleteUserById(int uid);

   public UserDto queryUserById(int uid) ;

   public HandleResult updateUser(UserDto userdto) ;

   public HandleResult addUser(UserDto userdto) ;

   public Page<UserDto> UserPaging(int nowPage, int pageSize) ;

    List<User> findUserByFactor(User user);



}
