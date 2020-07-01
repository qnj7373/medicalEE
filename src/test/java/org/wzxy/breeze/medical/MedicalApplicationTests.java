package org.wzxy.breeze.medical;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.wzxy.breeze.core.mapper.userMapper;
import org.wzxy.breeze.core.model.po.User;

import javax.annotation.Resource;

@SpringBootTest
class MedicalApplicationTests {
@Resource
    private userMapper mapper;
    @Test
    void contextLoads() {
    }

}
