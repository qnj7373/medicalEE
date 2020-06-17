package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.payment;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class paymentDto implements Serializable {


    private int paymentId;  //参合证号
    private String payDate;  //参合时间
    @NotBlank(message = "参合发票号不能为空")
    private String invoiceNum;   //参合发票号
    private String payerName;  //姓名
    @NotBlank(message = "身份证号不能为空")
    private String cardID;   //身份证号
       //  家庭编号、
    private int famicode;
    //     、性别、年龄、
    private String age;
    private String sex;
   // 家庭住址、
    private String liveAddress;
    //   联系方式、*/
    private String telephone;

    private int nowPage;
    private int pageSize;

    public paymentDto() {
        super();
    }

    public paymentDto(payment p) {
        this.paymentId = p.getPaymentId();
        this.payDate = p.getPayDate();
        this.invoiceNum = p.getInvoiceNum();
        this.payerName = p.getPayerName();
        this.cardID = p.getCardID();
        if(p.getPayer()!=null){
            this.famicode = p.getPayer().getFamicode();
            this.age = p.getPayer().getAge();
            this.sex = p.getPayer().getSex();
            this.liveAddress = p.getPayer().getLiveAddress();
            this.telephone = p.getPayer().getTelephone();
        }

    }

    public paymentDto(int paymentId, String payDate, String invoiceNum, String payerName, String cardID, int famicode, String age, String sex, String liveAddress, String telephone, int nowPage, int pageSize) {
        this.paymentId = paymentId;
        this.payDate = payDate;
        this.invoiceNum = invoiceNum;
        this.payerName = payerName;
        this.cardID = cardID;
        this.famicode = famicode;
        this.age = age;
        this.sex = sex;
        this.liveAddress = liveAddress;
        this.telephone = telephone;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
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

    public int getFamicode() {
        return famicode;
    }

    public void setFamicode(int famicode) {
        this.famicode = famicode;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
