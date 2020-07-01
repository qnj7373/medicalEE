package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.expenseDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.vo.Page;

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
