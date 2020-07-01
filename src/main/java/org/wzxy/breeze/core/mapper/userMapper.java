package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.core.model.po.User;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface userMapper {

    @Select("SELECT IFNULL((SELECT 1 from user where uid = #{uid} limit 1),0)")
    public  int  isExist(int uid);

    @Select("SELECT IFNULL((SELECT 1 from user_role where uid = #{uid} limit 1),0)")
    public  int  UserRelationIsExist(int uid);

    @SelectProvider(type = userMapperProvider.class,method = "findUserByFactor")
    @Results({
            @Result(id=true,column="uid",property="uid"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column="upwd",property="upwd"),
            @Result(column="uname",property="uname"),
            @Result(column = "administrationId",property = "thisOrgan",
                    one = @One(select = "org.wzxy.breeze.core.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "uid",property = "roles",
                    many = @Many(select = "org.wzxy.breeze.core.mapper.rolesMapper.findRoleByUid",fetchType = FetchType.LAZY)
            )
    })
     List<User> findUserByFactor(User user);

    @Select("SELECT * FROM user")
    @Results({
            @Result(id=true,column="uid",property="uid"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column="upwd",property="upwd"),
            @Result(column="uname",property="uname"),
            @Result(column = "administrationId",property = "thisOrgan",
                    one = @One(select = "org.wzxy.breeze.core.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "uid",property = "roles",
                    many = @Many(select = "org.wzxy.breeze.core.mapper.rolesMapper.findRoleByUid",fetchType = FetchType.LAZY)
            )
    })
     List<User> findAllUser();
//////
@Select("SELECT * FROM user WHERE  uid=#{uid}  ")
public User findUserByUid(@Param("uid") int uid);


    @Insert("INSERT INTO user (upwd,uname,administrationId) " +
        " VALUES(#{upwd},#{uname},#{administrationId})")
    @Options(useGeneratedKeys=true, keyProperty="uid", keyColumn="uid")
    public boolean addUser(User user);

    @Delete("DELETE  FROM user WHERE uid = #{uid}")
    public boolean deleteUserById(User user);

    @Select("SELECT * FROM user WHERE uid = #{uid}")
    @Results({
            @Result(id=true,column="uid",property="uid"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column="upwd",property="upwd"),
            @Result(column="uname",property="uname"),
            @Result(column = "administrationId",property = "thisOrgan",
                    one = @One(select = "org.wzxy.breeze.core.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "uid",property = "roles",
                    many = @Many(select = "org.wzxy.breeze.core.mapper.rolesMapper.findRoleByUid",fetchType = FetchType.LAZY)
            )
    })
    public User queryUserById(int uid);

    @Update("UPDATE  user SET  upwd = #{upwd} , uname = #{uname}  " +
            " WHERE uid = #{uid}")
    public boolean updateUser(User user);

    @Select("SELECT COUNT(*) FROM user")
    public int getTotalCount();

    @Select("SELECT * FROM user LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="uid",property="uid"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column="upwd",property="upwd"),
            @Result(column="uname",property="uname"),
            @Result(column = "administrationId",property = "thisOrgan",
                    one = @One(select = "org.wzxy.breeze.core.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "uid",property = "roles",
                    many = @Many(select = "org.wzxy.breeze.core.mapper.rolesMapper.findRoleByUid",fetchType = FetchType.LAZY)
            )
    })
    public List<User> getUsersByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);



    class userMapperProvider{

        StringBuffer sql= new StringBuffer();

        public  String findUserByFactor(User user){
            sql.append("SELECT * FROM user");
            if(user.getUid()!=0){
                sql.append(" WHERE uid = #{uid}");
            }
            if(user.getUname()!=null){
                if(user.getUid()!=0){
                    sql.append(" AND ");
                }else{
                    sql.append(" WHERE ");
                }
                sql.append(" uname = #{uname}");
            }
            if(user.getUpwd()!=null){
                if(user.getUid()!=0||user.getUname()!=null){
                    sql.append(" AND ");
                }else{
                    sql.append(" WHERE ");
                }
                sql.append(" upwd = #{upwd}");
            }
            return  sql.toString();
        }



    }

}
