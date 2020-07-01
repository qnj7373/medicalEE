package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.dto.FamilyDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class family implements Serializable {

/*    家庭档案信息包括家庭编号、户主姓名、
    身份证号、所在村编号，建档时间*/
    /**
     * 家庭编号
     */
    private int famicode;
    /**
     * 村编号
     */
    private  int  regionId;
    private region village;
    /**
     * 户主信息
     */
    private int perscode; ///////////////////////////
    private String holderName;
    private person holder;
    /**
     *户属性
     */
    private String housePro;
    /**
     * 人口数
     */
    private int popuNum;
    /**
     * 家庭住址
     */
    private String address;
    /**
     * 建档时间
     */
    private String creattime;

    public family() {
        super();
    }

    public family(int famicode, int regionId, region village, int perscode, String holderName, person holder, String housePro, int popuNum, String address, String creattime) {
        this.famicode = famicode;
        this.regionId = regionId;
        this.village = village;
        this.perscode = perscode;
        this.holderName = holderName;
        this.holder = holder;
        this.housePro = housePro;
        this.popuNum = popuNum;
        this.address = address;
        this.creattime = creattime;
    }

    public family(FamilyDto dto) {
        this.famicode = dto.getFamicode();
        this.regionId = dto.getRegionId();
        this.perscode = dto.getPerscode();
        this.holderName =dto.getHolderName();
        this.housePro = dto.getHousePro();
        this.popuNum = dto.getPopuNum();
        this.address = dto.getAddress();
        this.creattime = dto.getCreattime();
    }

    public int getFamicode() {
        return famicode;
    }

    public void setFamicode(int famicode) {
        this.famicode = famicode;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public region getVillage() {
        return village;
    }

    public void setVillage(region village) {
        this.village = village;
    }

    public int getPerscode() {
        return perscode;
    }

    public void setPerscode(int perscode) {
        this.perscode = perscode;
    }

    public person getHolder() {
        return holder;
    }

    public void setHolder(person holder) {
        this.holder = holder;
    }

    public String getHousePro() {
        return housePro;
    }

    public void setHousePro(String housePro) {
        this.housePro = housePro;
    }

    public int getPopuNum() {
        return popuNum;
    }

    public void setPopuNum(int popuNum) {
        this.popuNum = popuNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
