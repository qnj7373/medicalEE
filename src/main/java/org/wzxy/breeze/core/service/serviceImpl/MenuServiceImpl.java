package org.wzxy.breeze.core.service.serviceImpl;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.core.mapper.menusMapper;
import org.wzxy.breeze.core.mapper.userMapper;
import org.wzxy.breeze.core.model.dto.MenuDto;
import org.wzxy.breeze.core.model.po.HandleResult;
import org.wzxy.breeze.core.model.po.User;
import org.wzxy.breeze.core.model.po.menu;
import org.wzxy.breeze.core.model.po.role;
import org.wzxy.breeze.core.model.vo.Page;
import org.wzxy.breeze.core.model.vo.ResponseCode;
import org.wzxy.breeze.core.service.Iservice.IMenuService;
import org.wzxy.breeze.common.utils.getOutDistinct;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 覃能健
 * @create 2020-05
 */

@Service
public class MenuServiceImpl  implements IMenuService {
    @Resource
    private  userMapper  userDao;
    @Resource
    private menusMapper menuDao;
    @Autowired
    private List<MenuDto> menuDtoList;
    @Autowired
    private Page<MenuDto> menuPage;
    private List<Map<String,String>> zTreeList = new ArrayList<>();
   @Autowired
    private HandleResult handle ;
    private int exist=-1;


    @Override
    @Cacheable(value = "menuZone" , key = "'getTreeOfAdd'")
    public List<Map<String, String>> getTreeOfAdd() {
        List<menu> allMenu = menuDao.findAllMenu();
        zTreeList.clear();
        for (menu m:allMenu){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", m.getMenuId());
            zTreeMap.put("pId",m.getMenuPid());
            zTreeMap.put("name", m.getMenuName());
            zTreeMap.put("open", "true");
            zTreeMap.put("checked", "false");
            zTreeList.add(zTreeMap);
        }
        return zTreeList;
    }

    @Override
    @Cacheable(value = "menuZone" , key = "'getTreeOfAdd'")
    public List<Map<String, String>> getTreeOfHave(int roleId) {

        List<menu> allMenu = menuDao.findAllMenu();
        List<menu> menusByRId = menuDao.findMenusByRId(roleId);
        zTreeList.clear();
        for (menu m:allMenu){
            Map<String,String> zTreeMap = new HashedMap();
            zTreeMap.put("id", m.getMenuId());
            zTreeMap.put("pId", m.getMenuPid());
            zTreeMap.put("name", m.getMenuName());
            zTreeMap.put("open", "true");

            for (menu have:
                    menusByRId) {
                if(m.getMenuId().equals(have.getMenuId())){
                    zTreeMap.put("checked", "true");
                    break;
                }else{
                    zTreeMap.put("checked", "false");
                }
            }


            zTreeList.add(zTreeMap);
        }

        return zTreeList;



    }

    @Override
    @Cacheable(value = "menuZone" , key = "'findMenuByPage'+#nowPage+','+#pageSize")
    public Page<MenuDto> findMenuByPage(int nowPage, int pageSize) {
        List<menu> menus = menuDao.getMenusByPage(nowPage * pageSize, pageSize);
        menuDtoList.clear();
        for (menu m:
                menus) {
            menuDtoList.add( new MenuDto(m) );
        }
        menuPage.setDatas(menuDtoList);
        menuPage.setNowPage(nowPage+1);
        menuPage.setDataTotalCount(menuDao.getTotalCount());
        menuPage.setPageSize(pageSize);
        menuPage.setPageTotalCount(menuPage.getPageTotalCount());
        return menuPage;
    }

    @Override
    @CacheEvict(cacheNames = "menuZone",allEntries = true)
    public HandleResult addMenu(MenuDto menuDto) {
        String mPid = menuDto.getMenuPid();
        StringBuffer temp=new StringBuffer();
        int id=0;
        id++;
        if("0".equals(mPid)){
            temp.append("m");
        }else{
            String menuId = menuDao.findMenuBymenuId(mPid).getMenuId();
            temp.append(menuId);
        }
        temp.append(id);
        while (menuDao.isExist(temp.toString())==1){
            id++;
            temp.replace(temp.length()-1, temp.length(), String.valueOf(id));
        }
        exist=menuDao.isExist(temp.toString());
        if(exist==0){
            menuDto.setMenuId(temp.toString());

            if(  menuDao.addMenu( new menu(menuDto) )){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("新增菜单成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("新增菜单失败!");
            }


            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("新增失败，菜单已存在!");
            return handle;
        }


    }

    @Override
    @CacheEvict(cacheNames = "menuZone",allEntries = true)
    public HandleResult updateMenu(MenuDto menuDto) {
       exist=menuDao.isExist(menuDto.getMenuId());
        if(exist==1){
            if(  menuDao.updateMenu( new menu(menuDto)) ){
                handle.setStatus(ResponseCode.OK.getCode());
                handle.setMessage("更新菜单成功!");
            }else{
                handle.setStatus(ResponseCode.FAIL.getCode());
                handle.setMessage("更新菜单失败!");
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("更新失败，菜单不存在!");
            return handle;
        }

    }

    @Override
    @Cacheable(value = "menuZone" , key = "'findMenuById'+#menuId")
    public MenuDto findMenuById(String menuId) {
        MenuDto menuDto =   new MenuDto(menuDao.findMenuBymenuId(menuId));
          if("0".equals(menuDto.getMenuPid())){
              menuDto.setSelectMenuName("无");

          }else{
              menuDto.setSelectMenuName(menuDao.findMenuBymenuId(menuDto.getMenuPid()).getMenuName());

          }
        return menuDto;
    }

    @Override
    @CacheEvict(cacheNames = "menuZone",allEntries = true)
    public HandleResult deleteMenuById(String menuId) {
        exist=menuDao.isExist(menuId);
        if(exist==1){
            menuDao.deleteMenu(menuId);
            exist=menuDao.MenuRelationIsExist(menuId);
            if(exist==1){
                if(  menuDao.deleteMenuRelationByMenuId(menuId) ){
                    handle.setStatus(ResponseCode.OK.getCode());
                    handle.setMessage("删除菜单成功!");
                }else{
                    handle.setStatus(ResponseCode.FAIL.getCode());
                    handle.setMessage("删除菜单失败!");
                }
            }

            return handle;
        }else{
            handle.setStatus(ResponseCode.FAIL.getCode());
            handle.setMessage("删除失败，菜单不存在!");
            return handle;
        }
    }


    @Override
    @Cacheable(value = "menuZone" , key = "'getMenusIndex'+#userId")
    public List<menu> getMenusIndex(int userId) {
        User user = userDao.queryUserById(userId);
        List<role> roles = user.getRoles();
        List<menu> menus = new ArrayList<menu>();
        menus.clear();
        if(user!=null){
            for (role r:
                    user.getRoles()) {

                for (menu m:
                        r.getMenus()) {
                    if("0".equals(m.getMenuPid())){
                        m.setChildMenus(menuDao.findChildMenu(m.getMenuId(), userId));
                        menus.add(m);
                    }

                }
            }
        }
        getOutDistinct getDistinct = new getOutDistinct();
        //////查重
        menus=getDistinct.distinct(menus);

        return menus;
    }

    @Override
    @Cacheable(value = "menuZone" , key = "'getAllMenus'")
    public List<menu> getAllMenus() {
        return  menuDao.getAllMenus();
    }
}
