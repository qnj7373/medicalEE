package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.OrganizationDto;
import org.wzxy.breeze.model.dto.RegionDto;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class RegionFactory {

    @Bean
    public List<RegionDto> createRegionDtoList() {

        return new ArrayList<RegionDto>();
    }


    @Bean
    public Page<RegionDto> createRegionPage()
    {
        return new Page<RegionDto>();
    }

}
