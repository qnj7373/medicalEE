package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.dto.expenseDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class expense implements Serializable {

    private int Id;
    private String cardID;//身份证号
    private String payerName;//姓名
    private String illname; //疾病名称
    private String runyear;  //年度
    private double amount;   //本次报销金额
    private int administrationId;   //经办机构
    private String state;   //审核及汇款状态等
    private organization operator;   //经办机构


    public expense() {
        super();
    }

    public expense(int id, String cardID, String payerName, String illname, String runyear, double amount, int administrationId, String state, organization operator) {
        Id = id;
        this.cardID = cardID;
        this.payerName = payerName;
        this.illname = illname;
        this.runyear = runyear;
        this.amount = amount;
        this.administrationId = administrationId;
        this.state = state;
        this.operator = operator;
    }

    public expense(expenseDto e) {
        this.Id =  e.getId();
        this.cardID =  e.getCardID();
        this.payerName =  e.getPayerName();
        this.illname =  e.getIllname();
        this.runyear =  e.getRunyear();
        this.amount =  e.getAmount();
        this.state=e.getState();
        this.administrationId =  e.getAdministrationId();
    }




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public organization getOperator() {
        return operator;
    }

    public void setOperator(organization operator) {
        this.operator = operator;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getIllname() {
        return illname;
    }

    public void setIllname(String illname) {
        this.illname = illname;
    }

    public String getRunyear() {
        return runyear;
    }

    public void setRunyear(String runyear) {
        this.runyear = runyear;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }
}
