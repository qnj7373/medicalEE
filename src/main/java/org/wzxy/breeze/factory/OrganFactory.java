package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.MenuDto;
import org.wzxy.breeze.model.dto.OrganizationDto;
import org.wzxy.breeze.model.dto.RoleDto;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class OrganFactory {

    @Bean
    public List<OrganizationDto> createOrganizationDtoList() {

        return new ArrayList<OrganizationDto>();
    }

    @Bean
    public Page<OrganizationDto> createOrganPage()
    {
        return new Page<OrganizationDto>();
    }

}
