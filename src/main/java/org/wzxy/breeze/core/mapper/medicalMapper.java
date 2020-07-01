package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.core.model.po.medical;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface medicalMapper {


    @Insert("INSERT INTO medical (medicalName,regionId,lsgx,ssjjlx,jgjb,wsjgdl,wsjgxl,zgdw,kysj,frdb,zczj) " +
            " VALUES(#{medicalName},#{regionId},#{lsgx},#{ssjjlx},#{jgjb},#{wsjgdl},#{wsjgxl},#{zgdw},#{kysj},#{frdb},#{zczj})")
    @Options(useGeneratedKeys=true, keyProperty="medicalId", keyColumn="medicalId")
    public boolean add(medical m);

    @Select("SELECT IFNULL((SELECT 1 FROM medical WHERE medicalId = #{medicalId} LIMIT 1),0)")
    public  int  isExist(int medicalId);


    @Select("SELECT * FROM medical WHERE medicalId = #{medicalId} ")
    @Results({
            @Result(id=true,column="medicalId",property="medicalId"),
            @Result(column="regionId",property="regionId"),
            @Result(column="lsgx",property="lsgx"),
            @Result(column="ssjjlx",property="ssjjlx"),
            @Result(column="jgjb",property="jgjb"),
            @Result(column="wsjgdl",property="wsjgdl"),
            @Result(column="wsjgxl",property="wsjgxl"),
            @Result(column="zgdw",property="zgdw"),
            @Result(column="kysj",property="kysj"),
            @Result(column="frdb",property="frdb"),
            @Result(column="zczj",property="zczj"),
            @Result(column = "regionId",property = "reg",
                    one = @One(select = "org.wzxy.breeze.core.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
    })
    medical findById(int medicalId);

    @Select("SELECT * FROM medical")
    @Results({
            @Result(id=true,column="medicalId",property="medicalId"),
            @Result(column="regionId",property="regionId"),
            @Result(column="lsgx",property="lsgx"),
            @Result(column="ssjjlx",property="ssjjlx"),
            @Result(column="jgjb",property="jgjb"),
            @Result(column="wsjgdl",property="wsjgdl"),
            @Result(column="wsjgxl",property="wsjgxl"),
            @Result(column="zgdw",property="zgdw"),
            @Result(column="kysj",property="kysj"),
            @Result(column="frdb",property="frdb"),
            @Result(column="zczj",property="zczj"),
            @Result(column = "regionId",property = "reg",
                    one = @One(select = "org.wzxy.breeze.core.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
    })
    public List<medical> getAll();

    @Delete("DELETE  FROM medical WHERE medicalId = #{medicalId} ")
    boolean delete(@Param("medicalId") int medicalId);


    @Update("UPDATE  medical SET  medicalName=#{medicalName},regionId=#{regionId},lsgx=#{lsgx}," +
            "ssjjlx=#{ssjjlx},jgjb=#{jgjb}," +
            "wsjgdl=#{wsjgdl},wsjgxl=#{wsjgxl},zgdw=#{zgdw},kysj=#{kysj},frdb=#{frdb},zczj=#{zczj} " +
            " WHERE medicalId= #{medicalId}")
    public boolean update(medical m);


    @Select("SELECT * FROM medical  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="medicalId",property="medicalId"),
            @Result(column="regionId",property="regionId"),
            @Result(column="lsgx",property="lsgx"),
            @Result(column="ssjjlx",property="ssjjlx"),
            @Result(column="jgjb",property="jgjb"),
            @Result(column="wsjgdl",property="wsjgdl"),
            @Result(column="wsjgxl",property="wsjgxl"),
            @Result(column="zgdw",property="zgdw"),
            @Result(column="kysj",property="kysj"),
            @Result(column="frdb",property="frdb"),
            @Result(column="zczj",property="zczj"),
            @Result(column = "regionId",property = "reg",
                    one = @One(select = "org.wzxy.breeze.core.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
    })
    List<medical> getByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);




    @Select("SELECT COUNT(*) FROM medical")
    public int getTotalCount();



}
