package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.core.model.po.role;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public interface rolesMapper {

    @Select("SELECT IFNULL((SELECT 1 from role where roleId = #{roleId} limit 1),0)")
    public  int  isExist(int roleId);

    @Select("SELECT IFNULL((SELECT 1 from role_menu where roleId = #{roleId} limit 1),0)")
    public  int  RoleRelationIsExist(int roleId);

    @Select("SELECT * FROM role ")
    List<role> findAllRole( );

    @Select("SELECT id FROM user_role WHERE uid=#{uid}")
    int[] findRelation( @Param("uid") int uid);

    @Select("SELECT * FROM role WHERE roleId IN (SELECT roleId FROM user_role WHERE uid=#{uid})")
    @Results({
            @Result(id=true,column="roleId",property="roleId"),
            @Result(column="roleName",property="roleName"),
            @Result(column = "roleId",property = "menus",
                    one = @One(select = "org.wzxy.breeze.core.mapper.menusMapper.findMenuByRoleId",fetchType = FetchType.LAZY)
            )
    })
    List<role> findRoleByUserId(int uid);

    @Select("SELECT roleId FROM role WHERE roleId IN (SELECT roleId FROM user_role WHERE uid=#{uid})")
    String[] findRoleIdByUserId(int uid);



////////给新用户赋值角色
    @Insert("INSERT INTO user_role (uid,roleId)  " +
            " VALUES(#{uid},#{roleId})")
    public boolean addRoleRelation(@Param("uid") int uid,@Param("roleId") int roleId);


    ////////新角色
    @Insert("INSERT INTO role (roleName)  " +
            " VALUES(#{roleName})")
    @Options(useGeneratedKeys=true, keyProperty="roleId", keyColumn="roleId")
    public boolean addRole(role role);


    @Update("UPDATE  user_role  SET roleId= #{roleId}  " +
            " WHERE id = #{id}")
    public boolean updateRoleRelation(@Param("id") int id,@Param("roleId") int roleId);


    @Update("UPDATE  role  SET roleName= #{roleName}  " +
            " WHERE roleId = #{roleId}")
    public boolean updateRole(role role);

    @Delete("DELETE  FROM user_role WHERE uid = #{uid} ")
    public boolean deleteRoleRelation(@Param("uid") int uid);

    @Delete("DELETE  FROM role WHERE roleId = #{roleId} ")
    public boolean deleteRoleById(@Param("roleId") int roleId);

    @Select("SELECT * FROM role WHERE roleId IN (SELECT roleId FROM user_role WHERE uid=#{uid})")
    @Results({
            @Result(id=true,column="roleId",property="roleId"),
            @Result(column="roleName",property="roleName"),
            @Result(column = "roleId",property = "menus",
                    many = @Many(select = "org.wzxy.breeze.core.mapper.menusMapper.findMenuByRoleId",fetchType = FetchType.EAGER)
            )
    })
    List<role> findRoleByUid(int uid);

    @Select("SELECT * FROM role WHERE roleId = #{roleId}")
    role findByRoleId(int roleId);


    @Select("SELECT * FROM role LIMIT  #{startPage},#{pageSize} ")
    public List<role> getRolesByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) FROM role")
    public int getTotalCount();

}
