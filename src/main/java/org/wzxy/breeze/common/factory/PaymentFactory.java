package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.paymentDto;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class PaymentFactory {


    @Bean
    public List<paymentDto> createPaymentDtoList() {

        return new ArrayList<paymentDto>();
    }

    @Bean
    public Page<paymentDto> createPaymentDtoPage()
    {
        return new Page<paymentDto>();

    }

    @Bean
    public paymentDto createPaymentDto() {

        return new paymentDto();
    }




}
