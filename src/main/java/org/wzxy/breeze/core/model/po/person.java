package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.dto.PersonDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class person implements Serializable {
    /*农合证号、姓名、
    家庭编号、身份证号、性别、年龄、家庭住址、联系方式
            号*/

    /**
     * 个人编号
     */
    private int perscode;
    /**
     * 姓名
     */
    private String persname;
    /**
     * 身份证号
     */
    private String cardID;

    /**
     * 对应家庭档案
     */
    private int famicode;
    private family family;
    /**
     * 农合证卡号
     */
    private String ruralCard;
    /**
     * 与户主关系
     */
    private String relation;//1:表示户主，2表示配偶,3表示子，4表示女，5表示孙子，6表示孙女


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

    public person() {
        super();
    }

    public person(int perscode, String persname, String cardID, int famicode, org.wzxy.breeze.core.model.po.family family, String ruralCard, String relation, String age, String sex, String birthday, String occupation, String workUnit, String liveAddress, String telephone) {
        this.perscode = perscode;
        this.persname = persname;
        this.cardID = cardID;
        this.famicode = famicode;
        this.family = family;
        this.ruralCard = ruralCard;
        this.relation = relation;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.occupation = occupation;
        this.workUnit = workUnit;
        this.liveAddress = liveAddress;
        this.telephone = telephone;
    }

    public person(PersonDto dto) {
        this.perscode = dto.getPerscode();
        this.persname = dto.getPersname();
        this.cardID = dto.getCardID();
        this.famicode = dto.getFamicode();
        this.ruralCard = dto.getRuralCard();
        //1:表示户主，2表示配偶,3表示子，4表示女，5表示孙子，6表示孙女
        this.relation = dto.getRelation();
        this.age = dto.getAge();
        this.sex = dto.getSex();
        this.birthday = dto.getBirthday();
        this.occupation =dto.getOccupation();
        this.workUnit = dto.getWorkUnit();
        this.liveAddress = dto.getLiveAddress();
        this.telephone = dto.getTelephone();
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

    public org.wzxy.breeze.core.model.po.family getFamily() {
        return family;
    }

    public void setFamily(org.wzxy.breeze.core.model.po.family family) {
        this.family = family;
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
}
