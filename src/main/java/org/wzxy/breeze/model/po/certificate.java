package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.certificateDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class certificate implements Serializable {

    private int certificateId ;      //  慢病证号//p
    private String ruralCard;     //农合证号、//根据身份证号自己查
    private String cardID;        // 身份证号、
    private String illname;         // 疾病名称
    private String startTime;    //起始时间
    private String endTime;         //  终止时间
    private person payer;   //持证人

    public certificate() {
        super();
    }

    public certificate(certificateDto c) {
        this.certificateId = c.getCertificateId();
        this.ruralCard = c.getRuralCard();
        this.cardID = c.getCardID();
        this.illname = c.getIllname();
        this.startTime = c.getStartTime();
        this.endTime = c.getEndTime();
    }

    public certificate(int certificateId, String ruralCard, String cardID, String illname, String startTime, String endTime, person payer) {
        this.certificateId = certificateId;
        this.ruralCard = ruralCard;
        this.cardID = cardID;
        this.illname = illname;
        this.startTime = startTime;
        this.endTime = endTime;
        this.payer = payer;
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

    public person getPayer() {
        return payer;
    }

    public void setPayer(person payer) {
        this.payer = payer;
    }
}
