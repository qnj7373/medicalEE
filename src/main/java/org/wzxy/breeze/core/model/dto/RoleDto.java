package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.role;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-05
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class RoleDto implements Serializable {

    private  int roleId;
    @NotBlank(message = "角色名不能为空")
    private  String roleName;
    private String menuIds;

    private int nowPage;
    private int pageSize;

    public RoleDto() {
        super();
    }

    public RoleDto(role r) {
        this.roleId = r.getRoleId();
        this.roleName = r.getRoleName();
    }

    public RoleDto(int roleId, String roleName,  String menuIds, int nowPage, int pageSize) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.menuIds = menuIds;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }
}
