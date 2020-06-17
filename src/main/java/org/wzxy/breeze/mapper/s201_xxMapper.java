package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.model.po.chronicdis;
import org.wzxy.breeze.model.po.s201_xx;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface s201_xxMapper {


    @Insert("INSERT INTO "+ " ${table} " +" (name) " +
            " VALUES( #{name} )")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public boolean adds201_xx(s201_xx s);



    @Select("SELECT IFNULL((SELECT 1 FROM "+ " ${table} " +" WHERE id = #{id} LIMIT 1),0)")
    public  int  isExist(@Param("id")int id,@Param("table") String table);



    @Select("SELECT * FROM "+ " ${table} " + " WHERE id = #{id} ")
    s201_xx finds201_xxById(@Param("id")int id,@Param("table") String table);



    @Select("SELECT * FROM  " + " ${table} " + "")
    public List<s201_xx> getAlls201_xx(@Param("table") String table);



    @Delete("DELETE  FROM  "+"${table}"+"  WHERE id = #{id} ")
    boolean deleteS201_xx(@Param("id")int id,@Param("table") String table);


    @Update("UPDATE  " + " ${table} " + " SET  name = #{name} " +
            " WHERE id= #{id}")
    public boolean updateS201_xx(s201_xx s);


    @Select("SELECT * FROM " + " ${table} " + "  LIMIT  #{startPage},#{pageSize} ")
    List<s201_xx> getS201_xxByPage(@Param("table") String table,@Param("startPage") int startPage, @Param("pageSize") int pageSize);



    @Select("SELECT COUNT(*) FROM " + " ${table} " + "")
    public int getTotalCount(@Param("table") String table);




}
