package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.RegionDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class region implements Serializable {

   // 行政区域信息包括区域编码、区域名称、级别。

    private  int  regionId;
    private  String  regionName;
    //上一级区域编码
    private String regionPid;
    //级别
    private  String level;

    private region parent;

    public region() {
        super();
    }

    public region(int regionId, String regionName, String regionPid, String level, region parent) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.regionPid = regionPid;
        this.level = level;
        this.parent = parent;
    }

    public region(RegionDto Regiondto ) {
        this.regionId = Regiondto.getRegionId();
        this.regionName = Regiondto.getRegionName();
        this.regionPid = Regiondto.getRegionPid();
        this.level = Regiondto.getLevel();
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public region getParent() {
        return parent;
    }

    public void setParent(region parent) {
        this.parent = parent;
    }
}
