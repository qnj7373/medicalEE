package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.core.model.po.menu;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface menusMapper {

   @Select("SELECT * FROM menu WHERE menuId IN " +
            "(SELECT menuId FROM role_menu WHERE roleId=#{roleId})")
    @Results({
            @Result(id=true,column="menuId",property="menuId"),
            @Result(column="menuName",property="menuName"),
            @Result(column="menuPid",property="menuPid"),
            @Result(column="url",property="url")
    })
    List<menu> findMenuByRoleId(int roleId);

    @Select("SELECT * FROM menu WHERE menuPid =  #{menuId}  " )
    List<menu> findMenuBymenuPid(@Param("menuId")String menuId );

    @Select("SELECT * FROM menu WHERE menuId =  #{menuId}  " )
    menu findMenuBymenuId(@Param("menuId")String menuId );

    @Select("SELECT * FROM menu WHERE menuPid =  #{menuId} " +
            "  AND menuId IN (SELECT menuId FROM role_menu WHERE roleId IN " +
            " ( SELECT roleId FROM user_role WHERE uid=#{uid} )" +
            " ) " )
    List<menu> findChildMenu(@Param("menuId")String menuId,@Param("uid")int uid );

    @Select("SELECT * FROM menu " )
    List<menu> findAllMenu ( );


    ////////给新角色赋值菜单
    @Insert("INSERT INTO role_menu (roleId,menuId)  " +
            " VALUES(#{roleId},#{menuId})")
    public boolean addMenuRelation(@Param("roleId") int roleId, @Param("menuId") String menuId);


    @Select("SELECT * FROM menu WHERE menuId IN (SELECT menuId FROM role_menu WHERE roleId=#{roleId})")
    List<menu> findMenusByRId(int roleId);

    @Delete("DELETE  FROM role_menu WHERE roleId = #{roleId} ")
      boolean deleteMenuRelation(@Param("roleId") int roleId);

    @Delete("DELETE  FROM role_menu WHERE menuId = #{menuId} ")
      boolean deleteMenuRelationByMenuId(@Param("menuId")String menuId);



    @Select("SELECT * FROM menu LIMIT  #{startPage},#{pageSize} ")
    List<menu> getMenusByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);


    @Select("SELECT COUNT(*) FROM menu")
    public int getTotalCount();


    @Select("SELECT * FROM menu")
    public List<menu> getAllMenus();


    @Select("SELECT * from menu order by menuId DESC limit 1")
    public  menu  getLastMenu();

    @Select("SELECT IFNULL((SELECT 1 from menu where menuId = #{menuId} limit 1),0)")
    public  int  isExist(String menuId);

    @Select("SELECT IFNULL((SELECT 1 from role_menu where menuId = #{menuId} limit 1),0)")
    public  int  MenuRelationIsExist(String menuId);

 @Insert("INSERT INTO menu (menuId,menuName,menuPid,url) " +
         " VALUES(#{menuId},#{menuName},#{menuPid},#{url})")
 public boolean addMenu(menu menu);


 @Update("UPDATE  menu SET  menuName = #{menuName} ," +
         " menuPid = #{menuPid},url = #{url}" +
         " WHERE menuId= #{menuId}")
 public boolean updateMenu(menu menu);


 @Delete("DELETE  FROM menu WHERE menuId = #{menuId} ")
 boolean deleteMenu(@Param("menuId") String menuId);

}
