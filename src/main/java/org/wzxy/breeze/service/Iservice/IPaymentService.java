package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.certificateDto;
import org.wzxy.breeze.model.dto.paymentDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface IPaymentService {

    Page<paymentDto> findPaymentByPage(int  regionId, int nowPage, int pageSize);

    HandleResult addPayment(paymentDto payDto) ;

    HandleResult deletePaymentById(int  paymentId);

    HandleResult updatePayment(paymentDto payDto) ;

    paymentDto findPaymentById(int  paymentId);


}
