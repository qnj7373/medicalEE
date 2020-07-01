package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.core.model.po.region;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface regionMapper {


    @Select("SELECT * FROM region")
    public List<region> findAllRegion();

    @Select("SELECT * FROM region WHERE regionId=#{regionId}")
    @Results({
            @Result(id=true,column="regionId",property="regionId"),
            @Result(column="regionName",property="regionName"),
            @Result(column="regionPid",property="regionPid"),
            @Result(column="level",property="level"),
            @Result(column = "regionPid",property = "parent",
                    one = @One(select = "org.wzxy.breeze.core.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            )
    })
    public region findRegionById(int  regionId);

    @Select("SELECT * FROM region WHERE regionId=#{regionId} OR regionPid=#{regionId} ")
    public List<region> findOwnRegions(int  regionId);


    @Select("SELECT * FROM region LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="regionId",property="regionId"),
            @Result(column="regionName",property="regionName"),
            @Result(column="regionPid",property="regionPid"),
            @Result(column="level",property="level"),
            @Result(column = "regionPid",property = "parent",
                    one = @One(select = "org.wzxy.breeze.core.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            )
    })
    public List<region> getRegionByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);


    @Select("SELECT IFNULL((SELECT 1 from region where regionId = #{regionId} limit 1),0)")
    public  int  isExist(int  regionId);


    @Select("SELECT COUNT(*) FROM region")
    public int getTotalCount();


    @Insert("INSERT INTO region (regionName,regionPid,level) " +
            " VALUES(#{regionName},#{regionPid},#{level})")
    public boolean addRegion(region region);

    @Update("UPDATE  region SET  regionName = #{regionName} ," +
            " regionPid = #{regionPid},level = #{level} " +
            " WHERE regionId= #{regionId}")
    public boolean updateRegion(region region);



    @Delete("DELETE  FROM region WHERE regionId = #{regionId} ")
    boolean deleteRegion(@Param("regionId") int regionId);



}
