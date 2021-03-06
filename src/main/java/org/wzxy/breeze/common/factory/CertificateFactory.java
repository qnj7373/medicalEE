package org.wzxy.breeze.common.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.core.model.dto.certificateDto;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class CertificateFactory {

    @Bean
    public List<certificateDto> createCertificateDtoList() {

        return new ArrayList<certificateDto>();
    }

    @Bean
    public Page<certificateDto> createCertificateDtoPage()
    {
        return new Page<certificateDto>();

    }

    @Bean
    public certificateDto createCertificateDto() {

        return new certificateDto();
    }





}
