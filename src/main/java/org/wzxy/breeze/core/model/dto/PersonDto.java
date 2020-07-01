package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.wzxy.breeze.core.model.po.person;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PersonDto implements Serializable {


    /**
     * 个人编号
     */
    private int perscode;
    /**
     * 对应家庭档案
     */
    private int famicode;
    /**
     * 农合证卡号
     */
    private String ruralCard;
    /**
     * 与户主关系
     */
    private String relation;//1:表示户主，2表示配偶,3表示子，4表示女，5表示孙子，6表示孙女
    /**
     * 身份证号
     */
    @NotBlank(message = "身份证号不能为空")
    private String cardID;
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空")
    private String persname;
    /**
     * 年龄
     */
    private String age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 是否农村户口
     */
    private String Rural;
    /**
     * 职业
     */
    private String occupation;
    /**
     * 工作单位
     */
    private String workUnit;
    /**
     * 常住地址
     */
    private String liveAddress;
    /**
     * 联系电话
     */
    private String telephone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  birthTime;
    private int nowPage;
    private int pageSize;

    public PersonDto() {
        super();
    }

    public PersonDto(person p) {
        this.perscode = p.getPerscode();
        this.persname = p.getPersname();
        this.cardID = p.getCardID();
        this.famicode = p.getFamicode();
        this.ruralCard = p.getRuralCard();
        //1:表示户主，2表示配偶,3表示子，4表示女，5表示孙子，6表示孙女
        this.relation = p.getRelation();
        this.age = p.getAge();
        this.sex = p.getSex();
        this.birthday = p.getBirthday();
        this.occupation =p.getOccupation();
        this.workUnit = p.getWorkUnit();
        this.liveAddress = p.getLiveAddress();
        this.telephone = p.getTelephone();
    }

    public PersonDto(int perscode, int famicode, String ruralCard, String relation, String cardID, String persname, String age, String sex, String birthday, String rural, String occupation, String workUnit, String liveAddress, String telephone, Date birthTime, int nowPage, int pageSize) {
        this.perscode = perscode;
        this.famicode = famicode;
        this.ruralCard = ruralCard;
        this.relation = relation;
        this.cardID = cardID;
        this.persname = persname;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        Rural = rural;
        this.occupation = occupation;
        this.workUnit = workUnit;
        this.liveAddress = liveAddress;
        this.telephone = telephone;
        this.birthTime = birthTime;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public int getPerscode() {
        return perscode;
    }

    public void setPerscode(int perscode) {
        this.perscode = perscode;
    }

    public int getFamicode() {
        return famicode;
    }

    public void setFamicode(int famicode) {
        this.famicode = famicode;
    }


    public String getRuralCard() {
        return ruralCard;
    }

    public void setRuralCard(String ruralCard) {
        this.ruralCard = ruralCard;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getPersname() {
        return persname;
    }

    public void setPersname(String persname) {
        this.persname = persname;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRural() {
        return Rural;
    }

    public void setRural(String rural) {
        Rural = rural;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
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

    public Date getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(Date birthTime) {
        this.birthTime = birthTime;
    }
}
