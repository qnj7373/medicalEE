package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.UserDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;

public interface IUserService {


   public  HandleResult deleteUserById(int uid);

   public UserDto queryUserById(int uid) ;

   public HandleResult updateUser(UserDto userdto) ;

   public HandleResult resetPassword(UserDto userdto) ;

   public HandleResult userLogout(String token) ;

   public boolean hasLogin(String loginUid) ;

   public void exitUser(String loginUid) ;

   public HandleResult addUser(UserDto userdto) ;

   public Page<UserDto> UserPaging(int nowPage, int pageSize) ;

    List<User> findUserByFactor(User user);



}
