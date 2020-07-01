package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.core.model.po.chronicdis;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface chronicdisMapper {


    @Insert("INSERT INTO chronicdis (illcode,illname) " +
            " VALUES(#{illcode},#{illname})")
    @Options(useGeneratedKeys=true, keyProperty="illcode", keyColumn="illcode")
    public boolean addChronicdis(chronicdis c);

    @Select("SELECT IFNULL((SELECT 1 FROM chronicdis WHERE illcode = #{illcode} LIMIT 1),0)")
    public  int  isExist(int illcode);


    @Select("SELECT * FROM chronicdis WHERE illcode = #{illcode} ")
    chronicdis findChronicdisById(int illcode);

    @Select("SELECT * FROM chronicdis")
    public List<chronicdis> getAllChronicdis();

    @Delete("DELETE  FROM chronicdis WHERE illcode = #{illcode} ")
    boolean deleteChronicdis(@Param("illcode") int illcode);


    @Update("UPDATE  chronicdis SET  illname = #{illname} " +
            " WHERE illcode= #{illcode}")
    public boolean updateChronicdis(chronicdis c);


    @Select("SELECT * FROM chronicdis  LIMIT  #{startPage},#{pageSize} ")
    List<chronicdis> getChronicdisByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);




    @Select("SELECT COUNT(*) FROM chronicdis")
    public int getTotalCount();

}
