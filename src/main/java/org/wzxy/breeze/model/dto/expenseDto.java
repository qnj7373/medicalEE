package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.expense;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class expenseDto implements Serializable {

    private int Id;
    @NotBlank(message = "身份证号不能为空")
    private String cardID;//身份证号
    private String payerName;//姓名
    @NotBlank(message = "疾病名称不能为空")
    private String illname; //疾病名称
    private String runyear;  //年度
    private double amount;   //本次报销金额
    @NotBlank(message = "报销金额输入不能为空")
    private String inputAmount;   //本次报销金额输入
    private String state;   //审核及汇款状态等
    private int administrationId;   //经办机构
    //经办机构名称
    private String organizationName;

    private int nowPage;
    private int pageSize;


    public expenseDto() {
        super();
    }

    public expenseDto(expense e) {
        this.Id =  e.getId();
        this.cardID =  e.getCardID();
        this.payerName =  e.getPayerName();
        this.illname =  e.getIllname();
        this.runyear =  e.getRunyear();
        this.amount =  e.getAmount();
        this.administrationId =  e.getAdministrationId();
        this.state = e.getState();
        if (e.getOperator()!=null){
            this.organizationName=e.getOperator().getOrganizationName();
        }

    }


    public expenseDto(int id, String cardID, String payerName, String illname, String runyear, double amount, String inputAmount, String state, int administrationId, String organizationName, int nowPage, int pageSize) {
        Id = id;
        this.cardID = cardID;
        this.payerName = payerName;
        this.illname = illname;
        this.runyear = runyear;
        this.amount = amount;
        this.inputAmount = inputAmount;
        this.state = state;
        this.administrationId = administrationId;
        this.organizationName = organizationName;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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

    public String getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(String inputAmount) {
        this.inputAmount = inputAmount;
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

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
