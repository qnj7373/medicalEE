package org.wzxy.breeze.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.po.menu;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author 覃能健
 * @create 2020-05
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class MenuDto implements Serializable {

    private  String menuId;
    @NotBlank(message = "菜单名称不能为空")
    private  String menuName;
    private  String menuPid;
    private  String url;
    private  String selectMenuName;

    private int nowPage;
    private int pageSize;


    public MenuDto() {
        super();
    }

    public MenuDto(String menuId, String menuName, String menuPid, String url, String selectMenuName, int nowPage, int pageSize) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPid = menuPid;
        this.url = url;
        this.selectMenuName = selectMenuName;
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    public MenuDto(menu m) {
        this.menuId = m.getMenuId();
        this.menuName = m.getMenuName();
        this.menuPid = m.getMenuPid();
        this.url = m.getUrl();
    }

    public String getSelectMenuName() {
        return selectMenuName;
    }

    public void setSelectMenuName(String selectMenuName) {
        this.selectMenuName = selectMenuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(String menuPid) {
        this.menuPid = menuPid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
