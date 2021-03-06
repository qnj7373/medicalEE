package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.MenuDto;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class MenuFactory {

    @Bean
    public List<MenuDto> createMenuDtoList() {

        return new ArrayList<MenuDto>();
    }

    @Bean
    public MenuDto createMenuDto() {
        return new MenuDto();
    }

    @Bean
    public Page<MenuDto> createMenuPage() {
        return new Page<MenuDto>();
    }

}
