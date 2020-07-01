package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.family;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class FamilyDto implements Serializable {


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
    private  String  regionName;
    /**
     * 户主信息
     */
    private int perscode;
    private String holderName;
    /**
     *户属性
     */
    @NotBlank(message = "户属性不能为空")
    private String housePro;
    /**
     * 人口数
     */
    private int popuNum;
    /**
     * 农业人口数
     */
    private int agriNum;
    /**
     * 家庭住址
     */
    @NotBlank(message = "家庭住址不能为空")
    private String address;
    /**
     * 建档时间
     */
    private String creattime;

    private int nowPage;
    private int pageSize;

    public FamilyDto() {
        super();
    }

    public FamilyDto(int famicode, int regionId, String regionName, int perscode, String holderName, String housePro, int popuNum, int agriNum, String address, String creattime, int nowPage, int pageSize) {
        this.famicode = famicode;
        this.regionId = regionId;
        this.regionName = regionName;
        this.perscode = perscode;
        this.holderName = holderName;
        this.housePro = housePro;
        this.popuNum = popuNum;
        this.agriNum = agriNum;
        this.address = address;
        this.creattime = creattime;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public FamilyDto(family f) {
        this.famicode = f.getFamicode();
        this.regionId = f.getRegionId();
        this.regionName = f.getVillage().getRegionName();
        this.perscode = f.getPerscode();
        this.holderName = f.getHolderName();
        this.housePro = f.getHousePro();
        this.popuNum = f.getPopuNum();
        this.address = f.getAddress();
        this.creattime = f.getCreattime();
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


    public int getPerscode() {
        return perscode;
    }

    public void setPerscode(int perscode) {
        this.perscode = perscode;
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

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public int getAgriNum() {
        return agriNum;
    }

    public void setAgriNum(int agriNum) {
        this.agriNum = agriNum;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
