package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.dto.paymentDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class payment implements Serializable {


    private int paymentId;  //参合证号
    private String payDate;  //参合时间
    private String invoiceNum;   //参合发票号
    private String payerName;  //姓名
    private String cardID;   //身份证号
    private person payer;   //参合人

    //其余信息连接其他表获取
      /*    家庭编号、
         、性别、年龄、
        家庭住址、
       联系方式、*/

    public payment() {
        super();
    }

    public payment(int paymentId, String payDate, String invoiceNum, String payerName, String cardID, person payer) {
        this.paymentId = paymentId;
        this.payDate = payDate;
        this.invoiceNum = invoiceNum;
        this.payerName = payerName;
        this.cardID = cardID;
        this.payer = payer;
    }

    public payment(paymentDto p) {
        this.paymentId = p.getPaymentId();
        this.payDate = p.getPayDate();
        this.invoiceNum = p.getInvoiceNum();
        this.payerName = p.getPayerName();
        this.cardID = p.getCardID();
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public person getPayer() {
        return payer;
    }

    public void setPayer(person payer) {
        this.payer = payer;
    }
}
