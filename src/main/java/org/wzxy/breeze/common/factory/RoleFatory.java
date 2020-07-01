package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.RoleDto;
import org.wzxy.breeze.core.model.po.role;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class RoleFatory {

    @Bean
    public List<RoleDto> createRoleDtoList() {
        return new ArrayList<RoleDto>();
    }

    @Bean
    public List<role> createRoleList() {
        return new ArrayList<role>();
    }

    @Bean
    public Page<RoleDto> createRolepage()
    {
        return new Page<RoleDto>();
    }


}
