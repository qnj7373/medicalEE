package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.po.organization;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface organizationMapper {

    @Select("SELECT * FROM organization")
    public List<organization> findAllOrgan();

    @Select("SELECT * FROM organization WHERE administrationId=#{administrationId}")
    @Results({
            @Result(id=true,column="administrationId",property="administrationId"),
            @Result(column="organizationCode",property="organizationCode"),
            @Result(column="regionId",property="regionId"),
            @Result(column="organizationName",property="organizationName"),
            @Result(column="administrationPid",property="administrationPid"),
            @Result(column="level",property="level"),
            @Result(column = "regionId",property = "thisRegion",
                    one = @One(select = "org.wzxy.breeze.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "administrationPid",property = "parent",
                    one  = @One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    public organization findOrganById(int administrationId);


    @Select("SELECT * FROM organization LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="administrationId",property="administrationId"),
            @Result(column="organizationCode",property="organizationCode"),
            @Result(column="regionId",property="regionId"),
            @Result(column="organizationName",property="organizationName"),
            @Result(column="administrationPid",property="administrationPid"),
            @Result(column="level",property="level"),
            @Result(column = "regionId",property = "thisRegion",
                    one =@One(select = "org.wzxy.breeze.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
            @Result(column = "administrationPid",property = "parent",
                    one =@One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    public List<organization> getOrgansByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);


    @Select("SELECT IFNULL((SELECT 1 from organization where administrationId = #{administrationId} limit 1),0)")
    public  int  isExist(int administrationId);

    @Select("SELECT IFNULL((SELECT 1 from organization where regionId = #{regionId} AND level= #{level} limit 1),0)")
    public  int  isXZExist(@Param("regionId")int regionId,@Param("level")String level);


    @Select("SELECT COUNT(*) FROM organization")
    public int getTotalCount();


    @Insert("INSERT INTO organization (organizationCode,regionId,organizationName,administrationPid,level) " +
            " VALUES(#{organizationCode},#{regionId},#{organizationName},#{administrationPid},#{level})")
    public boolean addOrgan(organization organ);

    @Update("UPDATE  organization SET  organizationCode = #{organizationCode} ," +
            "  regionId = #{regionId},organizationName = #{organizationName},administrationPid = #{administrationPid}, " +
            " level = #{level} " +
            " WHERE administrationId= #{administrationId}")
    public boolean updateOrgan(organization organ);



    @Delete("DELETE  FROM organization WHERE administrationId = #{administrationId} ")
    boolean deleteOrgan(@Param("administrationId") int administrationId);

}
