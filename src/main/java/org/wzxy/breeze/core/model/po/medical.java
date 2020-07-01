package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class medical implements Serializable {


    /**
     * 机构编码
     */
    private int medicalId;
    /**
     * 机构名称
     */
    @NotBlank(message = "医疗机构名称不能为空")
    private String medicalName;
    //所属区域编码
    private  int  regionId;
    //所属区域编码
    private  String  regionName;
    private  region  reg;
    /**
     * 隶属关系
     */
    private String lsgx;
    /**
     * 所属经济类型
     */
    private String ssjjlx;
    /**
     * 机构级别
     */
    private String jgjb;
    /**
     * 卫生机构大类
     */
    private String wsjgdl;
    /**
     * 卫生机构小类
     */
    private String wsjgxl;

    /**
     * 主管单位
     */
    private String zgdw;
    /**
     * 开业时间
     */
    private String kysj;
    /**
     * 法人代表
     */
    private String frdb;
    /**
     * 注册资金
     */
    private double zczj;
    private int nowPage;
    private int pageSize;

    public medical() {
    }

    public medical(int medicalId, String medicalName, int regionId, String regionName, region reg, String lsgx, String ssjjlx, String jgjb, String wsjgdl, String wsjgxl, String zgdw, String kysj, String frdb, double zczj, int nowPage, int pageSize) {
        this.medicalId = medicalId;
        this.medicalName = medicalName;
        this.regionId = regionId;
        this.regionName = regionName;
        this.reg = reg;
        if(this.reg!=null){
            this.regionName=this.reg.getRegionName();
        }
        this.lsgx = lsgx;
        this.ssjjlx = ssjjlx;
        this.jgjb = jgjb;
        this.wsjgdl = wsjgdl;
        this.wsjgxl = wsjgxl;
        this.zgdw = zgdw;
        this.kysj = kysj;
        this.frdb = frdb;
        this.zczj = zczj;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }


    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public region getReg() {
        return reg;
    }

    public void setReg(region reg) {
        this.reg = reg;
    }

    public String getJgjb() {
        return jgjb;
    }

    public void setJgjb(String jgjb) {
        this.jgjb = jgjb;
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

    public int getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(int medicalId) {
        this.medicalId = medicalId;
    }

    public String getMedicalName() {
        return medicalName;
    }

    public void setMedicalName(String medicalName) {
        this.medicalName = medicalName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getLsgx() {
        return lsgx;
    }

    public void setLsgx(String lsgx) {
        this.lsgx = lsgx;
    }

    public String getSsjjlx() {
        return ssjjlx;
    }

    public void setSsjjlx(String ssjjlx) {
        this.ssjjlx = ssjjlx;
    }

    public String getWsjgdl() {
        return wsjgdl;
    }

    public void setWsjgdl(String wsjgdl) {
        this.wsjgdl = wsjgdl;
    }

    public String getWsjgxl() {
        return wsjgxl;
    }

    public void setWsjgxl(String wsjgxl) {
        this.wsjgxl = wsjgxl;
    }

    public String getZgdw() {
        return zgdw;
    }

    public void setZgdw(String zgdw) {
        this.zgdw = zgdw;
    }

    public String getKysj() {
        return kysj;
    }

    public void setKysj(String kysj) {
        this.kysj = kysj;
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public double getZczj() {
        return zczj;
    }

    public void setZczj(double zczj) {
        this.zczj = zczj;
    }
}
