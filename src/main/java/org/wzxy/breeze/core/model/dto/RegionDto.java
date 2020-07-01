package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.region;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class RegionDto implements Serializable {

    private  int  regionId;
    @NotBlank(message = "区域名不能为空")
    private  String  regionName;
    //上一级区域编码
    private String regionPid;
    //上一级经办机构名称
    private String regionPName;
    //级别
    private  String level;


    private int nowPage;
    private int pageSize;


    public RegionDto() {
        super();
    }

    public RegionDto(region reg) {
        this.regionId = reg.getRegionId();
        this.regionName = reg.getRegionName();
        this.regionPid = reg.getRegionPid();
        this.level = reg.getLevel();
        if (reg.getParent()!=null){
            this.regionPName=reg.getParent().getRegionName();
        }else{
            this.regionPName="无";
        }
    }

    public RegionDto(int regionId, String regionName, String regionPid, String regionPName, String level, int nowPage, int pageSize) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.regionPid = regionPid;
        this.regionPName = regionPName;
        this.level = level;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionPid() {
        return regionPid;
    }

    public void setRegionPid(String regionPid) {
        this.regionPid = regionPid;
    }

    public String getRegionPName() {
        return regionPName;
    }

    public void setRegionPName(String regionPName) {
        this.regionPName = regionPName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
