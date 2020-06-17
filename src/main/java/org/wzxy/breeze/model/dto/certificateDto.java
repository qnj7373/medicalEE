package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.wzxy.breeze.model.po.certificate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class certificateDto implements Serializable {


    private int certificateId ;      //  慢病证号//p
    private String ruralCard;     //农合证号、//根据身份证号自己查
    @NotBlank(message = "身份证号不能为空")
    private String cardID;        // 身份证号、
    @NotBlank(message = "疾病名称不能为空")
    private String illname;         // 疾病名称
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String startTime;    //起始时间
    private String endTime;         //  终止时间
    // 姓名
    private String persname;
    private int nowPage;
    private int pageSize;

    public certificateDto() {
        super();
    }


    public certificateDto(certificate c) {
        this.certificateId = c.getCertificateId();
        this.cardID = c.getCardID();
        this.illname = c.getIllname();
        this.startTime = c.getStartTime();
        this.endTime = c.getEndTime();
        if(c.getPayer()!=null){
            this.ruralCard = c.getPayer().getRuralCard();
            this.persname = c.getPayer().getPersname();
        }

    }

    public certificateDto(int certificateId, String ruralCard, String cardID, String illname, Date startDate, Date endDate, String startTime, String endTime, String persname, int nowPage, int pageSize) {
        this.certificateId = certificateId;
        this.ruralCard = ruralCard;
        this.cardID = cardID;
        this.illname = illname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.persname = persname;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getRuralCard() {
        return ruralCard;
    }

    public void setRuralCard(String ruralCard) {
        this.ruralCard = ruralCard;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getIllname() {
        return illname;
    }

    public void setIllname(String illname) {
        this.illname = illname;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPersname() {
        return persname;
    }

    public void setPersname(String persname) {
        this.persname = persname;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
