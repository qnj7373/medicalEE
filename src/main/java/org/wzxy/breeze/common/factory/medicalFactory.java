package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.po.medical;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class medicalFactory {

    @Bean
    public List<medical> createMedicalList() {

        return new ArrayList<medical>();
    }

    @Bean
    public Page<medical> createMedicalDtoPage() {

        return new Page<medical>();
    }

}
