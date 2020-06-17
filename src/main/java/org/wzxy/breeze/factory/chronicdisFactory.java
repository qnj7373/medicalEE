package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.MenuDto;
import org.wzxy.breeze.model.dto.chronicdisDto;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class chronicdisFactory {


    @Bean
    public List<chronicdisDto> createChronicdisDtoList() {

        return new ArrayList<chronicdisDto>();
    }

    @Bean
    public Page<chronicdisDto> createChronicdisDtoPage() {

        return new Page<chronicdisDto>();
    }


}
