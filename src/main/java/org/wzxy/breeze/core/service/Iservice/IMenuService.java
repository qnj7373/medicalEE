package org.wzxy.breeze.core.service.Iservice;

import org.wzxy.breeze.core.model.dto.MenuDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.menu;
import org.wzxy.breeze.core.model.vo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-05
 */
public interface IMenuService {

    List<menu> getMenusIndex(int userId);

    List<menu> getAllMenus();

    List<Map<String, String>> getTreeOfAdd();

    List<Map<String, String>> getTreeOfHave(int roleId);

    Page<MenuDto> findMenuByPage(int nowPage, int pageSize);

    HandleResult addMenu(MenuDto menuDto) ;

    HandleResult updateMenu(MenuDto menuDto) ;

    MenuDto findMenuById(String menuId);

    HandleResult deleteMenuById(String menuId);


}
