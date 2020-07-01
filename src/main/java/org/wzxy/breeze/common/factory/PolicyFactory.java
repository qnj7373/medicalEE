package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.policyDto;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class PolicyFactory {


    @Bean
    public List<policyDto> createPolicyDtoList() {

        return new ArrayList<policyDto>();
    }

    @Bean
    public Page<policyDto> createPolicyDtoPage() {

        return new Page<policyDto>();
    }


}
