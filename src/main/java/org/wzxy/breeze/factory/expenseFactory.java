package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.expenseDto;
import org.wzxy.breeze.model.dto.policyDto;
import org.wzxy.breeze.model.po.expense;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class expenseFactory {


    @Bean
    public List<expenseDto> createExpenseDtoList() {

        return new ArrayList<expenseDto>();
    }

    @Bean
    public List<expense> createExpenseList() {

        return new ArrayList<expense>();
    }

    @Bean
    public Page<expenseDto> createExpenseDtoPage() {

        return new Page<expenseDto>();
    }



}
