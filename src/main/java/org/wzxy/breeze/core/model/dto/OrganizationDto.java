package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.organization;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-06
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class OrganizationDto implements Serializable {


    //所属行政编码
    private int administrationId;
    //所属区域编码
    private  int  regionId;
    private  String  regionName;
    private String  Pid;
    //经办机构编码
    private String organizationCode;
    //经办机构名称
    @NotBlank(message = "经办机构名称不能为空")
    private String organizationName;
    //上一级行政编码
    private int administrationPid;
    //上一级经办机构名称
    private String administrationPName;
    //级别
    private  String level;
    private  String organIds;
    private  String organPName;

    private int nowPage;
    private int pageSize;

    public OrganizationDto() {
        super();
    }

    public OrganizationDto(int administrationId, int regionId, String pid, String organizationCode, String organizationName, int administrationPid, String administrationPName, String level, String organIds, String organPName, int nowPage, int pageSize) {
        this.administrationId = administrationId;
        this.regionId = regionId;
        Pid = pid;
        this.organizationCode = organizationCode;
        this.organizationName = organizationName;
        this.administrationPid = administrationPid;
        this.administrationPName = administrationPName;
        this.level = level;
        this.organIds = organIds;
        this.organPName = organPName;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public OrganizationDto(int administrationId, int regionId, String regionName, String pid, String organizationCode, String organizationName, int administrationPid, String administrationPName, String level, String organIds, String organPName, int nowPage, int pageSize) {
        this.administrationId = administrationId;
        this.regionId = regionId;
        this.regionName = regionName;
        Pid = pid;
        this.organizationCode = organizationCode;
        this.organizationName = organizationName;
        this.administrationPid = administrationPid;
        this.administrationPName = administrationPName;
        this.level = level;
        this.organIds = organIds;
        this.organPName = organPName;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public OrganizationDto(organization oran) {
        this.administrationId = oran.getAdministrationId();
        this.regionId = oran.getRegionId();
        this.organizationCode = oran.getOrganizationCode();
        this.organizationName = oran.getOrganizationName();
        this.administrationPid = oran.getAdministrationPid();
        this.level = oran.getLevel();
        if (oran.getParent()!=null){
            this.organPName=oran.getParent().getOrganizationName();
        }else{
            this.organPName="无";
        }
        if(oran.getParent()!=null){
            this.administrationPName=oran.getParent().getOrganizationName();
            this.administrationPName =oran.getParent().getOrganizationName();
        }else{
            this.administrationPName="无";
        }
        if(oran.getThisRegion()!=null){
            this.regionName=oran.getThisRegion().getRegionName();
        }else{
            this.regionName="无";
        }
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

    public String getAdministrationPName() {
        return administrationPName;
    }

    public void setAdministrationPName(String administrationPName) {
        this.administrationPName = administrationPName;
    }

    public String getOrganIds() {
        return organIds;
    }

    public void setOrganIds(String organIds) {
        this.organIds = organIds;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String pid) {
        Pid = pid;
    }


    public String getOrganPName() {
        return organPName;
    }

    public void setOrganPName(String organPName) {
        this.organPName = organPName;
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
}
