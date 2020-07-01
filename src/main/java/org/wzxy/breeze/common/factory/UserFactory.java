package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.UserDto;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.loginUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class UserFactory {

    @Bean
    public loginUser createLoginUser() {
        return new loginUser();
    }

    @Bean
    public List<User> createUserList() {
        return  new ArrayList<User>();
    }
    @Bean
    public List<UserDto> createUserDtoList() {

        return  new ArrayList<UserDto>();
    }

    @Bean
    public Page<User> createUserPage() {
        return  new Page<User>();
    }

    @Bean
    public Page<UserDto> createUserDtoPage() {
        return  new Page<UserDto>();
    }

    @Bean
    public User createUser() {
        return new User();
    }


}
