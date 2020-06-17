package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.expenseDto;
import org.wzxy.breeze.model.dto.policyDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.expense;
import org.wzxy.breeze.model.po.policy;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IExpenseService {


    Page<expenseDto> findExpenseByPage(int administrationId,String state,int nowPage, int pageSize);

    HandleResult addExpense(expenseDto eDto,int administrationId) ;

    HandleResult updateExpense(expenseDto eDto) ;

    HandleResult updateExpenseState(expenseDto eDto) ;

    expenseDto findExpenseById(int Id);

    HandleResult deleteExpenseById(int Id);

}
