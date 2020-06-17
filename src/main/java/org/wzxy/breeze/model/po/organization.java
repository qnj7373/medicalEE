package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.OrganizationDto;

import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class organization implements Serializable {

    //所属行政编码
    private int administrationId;
    //所属区域编码
    private  int  regionId;
    //经办机构编码
    private String organizationCode;
    //经办机构名称
    private String organizationName;
    //所上一级行政编码
    private int administrationPid;
    //级别
    private  String level;

    private organization parent;

    private region thisRegion;


    public organization() {
        super();
    }

    public organization(int administrationId, int regionId, String organizationCode, String organizationName, int administrationPid, String level, organization parent) {
        this.administrationId = administrationId;
        this.regionId = regionId;
        this.organizationCode = organizationCode;
        this.organizationName = organizationName;
        this.administrationPid = administrationPid;
        this.level = level;
        this.parent = parent;
    }

    public organization(int administrationId, int regionId, String organizationCode, String organizationName, int administrationPid, String level, organization parent, region thisRegion) {
        this.administrationId = administrationId;
        this.regionId = regionId;
        this.organizationCode = organizationCode;
        this.organizationName = organizationName;
        this.administrationPid = administrationPid;
        this.level = level;
        this.parent = parent;
        this.thisRegion = thisRegion;
    }

    public organization( OrganizationDto orandto) {
        this.administrationId = orandto.getAdministrationId();
        this.administrationPid=orandto.getAdministrationPid();
        this.regionId = orandto.getRegionId();
        this.organizationCode = orandto.getOrganizationCode();
        this.organizationName = orandto.getOrganizationName();
        this.level = orandto.getLevel();
    }

    public int getAdministrationId() {
        return administrationId;
    }

    public void setAdministrationId(int administrationId) {
        this.administrationId = administrationId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getAdministrationPid() {
        return administrationPid;
    }

    public void setAdministrationPid(int administrationPid) {
        this.administrationPid = administrationPid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public organization getParent() {
        return parent;
    }

    public void setParent(organization parent) {
        this.parent = parent;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public region getThisRegion() {
        return thisRegion;
    }

    public void setThisRegion(region thisRegion) {
        this.thisRegion = thisRegion;
    }
}
