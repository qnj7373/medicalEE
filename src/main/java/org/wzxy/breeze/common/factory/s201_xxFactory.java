package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.po.s201_xx;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class s201_xxFactory {

    @Bean
    public List<s201_xx> creates201_xxList() {

        return new ArrayList<s201_xx>();
    }

    @Bean
    public Page<s201_xx> creates201_xxPage() {

        return new Page<s201_xx>();
    }



}
