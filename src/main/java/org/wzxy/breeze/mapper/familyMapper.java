package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.family;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface familyMapper {



    @Select("SELECT IFNULL((SELECT 1 from family where famicode = #{famicode} limit 1),0)")
    public  int  isExist(int famicode);


    @Select("SELECT * FROM family WHERE  regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId} " +
            "  OR regionId IN (SELECT regionId FROM region WHERE  regionPid IN (SELECT regionId FROM region WHERE  regionPid=#{regionId} )   )  )     " +
            "  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="famicode",property="famicode"),
            @Result(column="regionId",property="regionId"),
            @Result(column = "regionId",property = "village",
                    one = @One(select = "org.wzxy.breeze.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
            @Result(column="perscode",property="perscode"),
            @Result(column = "perscode",property = "holder",
                    one = @One(select = "org.wzxy.breeze.mapper.personMapper.findPersonById",fetchType = FetchType.LAZY)
            ),
            @Result(column="holderName",property="holderName"),
            @Result(column="housePro",property="housePro"),
            @Result(column="popuNum",property="popuNum"),
            @Result(column="address",property="address"),
            @Result(column="creattime",property="creattime"),
    })
    List<family> getFamilysByPage(@Param("regionId")int  regionId,@Param("startPage") int startPage, @Param("pageSize") int pageSize);



    @Insert("INSERT INTO family (regionId,perscode,holderName,housePro,popuNum,address,creattime) " +
            " VALUES(#{regionId},#{perscode},#{holderName},#{housePro},#{popuNum},#{address},#{creattime})")
    @Options(useGeneratedKeys=true, keyProperty="famicode", keyColumn="famicode")
    public boolean addFamily(family family);


    @Select("SELECT * FROM family WHERE famicode =  #{famicode}  " )
    @Results({
            @Result(id=true,column="famicode",property="famicode"),
            @Result(column="regionId",property="regionId"),
            @Result(column = "regionId",property = "village",
                    one = @One(select = "org.wzxy.breeze.mapper.regionMapper.findRegionById",fetchType = FetchType.LAZY)
            ),
            @Result(column="perscode",property="perscode"),
            @Result(column = "perscode",property = "holder",
                    one = @One(select = "org.wzxy.breeze.mapper.personMapper.findPersonById",fetchType = FetchType.LAZY)
            ),
            @Result(column="holderName",property="holderName"),
            @Result(column="housePro",property="housePro"),
            @Result(column="popuNum",property="popuNum"),
            @Result(column="address",property="address"),
            @Result(column="creattime",property="creattime"),
    })
    family findFamilyByFamicode(@Param("famicode")int famicode );

    @Select("SELECT * FROM family WHERE perscode =  #{perscode}  " )
    family findFamilyByPerscode(@Param("perscode")int perscode );


    @Select("SELECT COUNT(*) FROM family WHERE regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId}  " +
            " OR regionId IN (SELECT regionId FROM region WHERE  regionPid IN (SELECT regionId FROM region WHERE  regionPid=#{regionId} ) )  )  ")
    public int getTotalCount(int regionId);


    @Delete("DELETE  FROM family WHERE famicode = #{famicode} ")
    boolean deleteFamily(@Param("famicode") int famicode);




    @Update("UPDATE  family SET  regionId = #{regionId} ," +
            " perscode = #{perscode},holderName = #{holderName}, " +
            " housePro = #{housePro},popuNum= #{popuNum},address = #{address} " +
            " WHERE famicode= #{famicode}")
    public boolean updateFamily(family family);



}
