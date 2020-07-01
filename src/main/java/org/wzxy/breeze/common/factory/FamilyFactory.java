package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.FamilyDto;
import org.wzxy.breeze.core.model.dto.PersonDto;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class FamilyFactory {

    @Bean
    public List<FamilyDto> createFamilyDtoList() {

        return new ArrayList<FamilyDto>();
    }

    @Bean
    public Page<FamilyDto> createFamilyDtoPage() {
        return new Page<FamilyDto>();
    }

    @Bean
    public List<PersonDto> createPersonDtoList() {

        return new ArrayList<PersonDto>();
    }

    @Bean
    public FamilyDto createFamilyDto() {

        return new FamilyDto();
    }

    @Bean
    public Page<PersonDto> createPersonDtoPage() {
        return new Page<PersonDto>();
    }


}
