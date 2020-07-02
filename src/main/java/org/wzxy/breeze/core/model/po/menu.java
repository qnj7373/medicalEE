package org.wzxy.breeze.core.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.core.model.dto.MenuDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class menu implements Serializable {
    private  String menuId;
    private  String menuName;
    private  String menuPid;
    private  String url;
    private List<menu> childMenus;



    public menu() {
        super();
    }

    public menu(String menuId, String menuName, String menuPid, String url, List<menu> childMenus) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPid = menuPid;
        this.url = url;
        this.childMenus = childMenus;
    }

    public menu(MenuDto menudto) {
        this.menuId = menudto.getMenuId();
        this.menuName = menudto.getMenuName();
        this.menuPid = menudto.getMenuPid();
        this.url = menudto.getUrl();
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

    public List<menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<menu> childMenus) {
        this.childMenus = childMenus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        //这里的code需要改成你要判断如重复的属性名称
        result = prime * result + ((menuId == null)?0:menuId.hashCode());
        return result;
    }

    /**
     * 重写equals方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }if(obj == null){
            return false;
        }if (getClass() != obj.getClass()){
            return false;
        }
        //Parts是我的实体类名称
        menu menu = (menu) obj;
        //code改成你需要的属性名
        if(menuId == null){
            if(menu.menuId != null){
                return false;
            }
        }else if(!menuId.equals(menu.menuId)){
            return false;
        }
        return true;
    }

}

