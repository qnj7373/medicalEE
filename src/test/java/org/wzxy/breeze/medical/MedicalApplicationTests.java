package org.wzxy.breeze.medical;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.wzxy.breeze.core.mapper.userMapper;
import org.wzxy.breeze.core.model.dto.UserDto;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.service.Iservice.IUserService;

import javax.annotation.Resource;

@SpringBootTest
class MedicalApplicationTests {
    @Autowired
    private IUserService userService;
    @Test
    void contextLoads() {

        UserDto u = new UserDto();
        u.setUpwd("123");
        u.setRoleIds("1,");
        u.setTempistrationId("1");
        userService.addUser(u);

    }

}
