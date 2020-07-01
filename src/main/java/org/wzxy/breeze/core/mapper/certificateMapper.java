package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.core.model.dto.expenseDto;
import org.wzxy.breeze.core.model.po.certificate;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface certificateMapper {



    @Select("SELECT IFNULL((SELECT 1 from certificate where certificateId = #{certificateId} limit 1),0)")
    public  int  isExist(int certificateId);


    @Select("SELECT IFNULL((SELECT 1 from certificate where cardID = #{cardID} AND illname=#{illname} limit 1),0)")
    public  int  expenseIsConsistent(expenseDto eDto);


    @Select("SELECT IFNULL((SELECT 1 from certificate where cardID = #{cardID} limit 1),0)")
    public  int  isHave(String cardID);

    @Select("SELECT COUNT(*) FROM certificate WHERE cardID = #{cardID} ")
    public  int  isOnlyOne(String cardID);

    @Select("SELECT * FROM certificate WHERE  cardID IN  " +
            " (SELECT cardID FROM person WHERE  famicode  IN  " +
            " (SELECT famicode FROM family WHERE  regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId}) ) " +
            " ) " +
            "  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="certificateId",property="certificateId"),
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="cardID",property="cardID"),
            @Result(column="illname",property="illname"),
            @Result(column="startTime",property="startTime"),
            @Result(column="endTime",property="endTime"),
            @Result(column = "cardID",property = "payer",
                    one = @One(select = "org.wzxy.breeze.core.mapper.personMapper.findPersonByCardID",fetchType = FetchType.LAZY)
            ),
    })
    List<certificate> getCertificateByPage(@Param("regionId")int  regionId, @Param("startPage") int startPage, @Param("pageSize") int pageSize);



    @Insert("INSERT INTO certificate (ruralCard,cardID,illname,startTime,endTime) " +
            " VALUES(#{ruralCard},#{cardID},#{illname},#{startTime},#{endTime})")
    @Options(useGeneratedKeys=true, keyProperty="certificateId", keyColumn="certificateId")
    public boolean addCertificate(certificate cert);


    @Select("SELECT * FROM certificate WHERE certificateId  =  #{certificateId }  " )
    @Results({
            @Result(id=true,column="certificateId",property="certificateId"),
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="cardID",property="cardID"),
            @Result(column="illname",property="illname"),
            @Result(column="startTime",property="startTime"),
            @Result(column="endTime",property="endTime"),
            @Result(column = "cardID",property = "payer",
                    one = @One(select = "org.wzxy.breeze.core.mapper.personMapper.findPersonByCardID",fetchType = FetchType.LAZY)
            ),
    })
    certificate findCertificateById(@Param("certificateId")int certificateId );


    @Select("SELECT COUNT(*) FROM certificate WHERE  cardID IN  " +
            " (SELECT cardID FROM person WHERE  famicode  IN  " +
            " (SELECT famicode FROM family WHERE  regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId}) ) " +
            " ) ")
    public int getTotalCount(int regionId);


    @Delete("DELETE  FROM certificate WHERE certificateId = #{certificateId} ")
    boolean deleteCertificate(@Param("certificateId") int certificateId);



    @Update("UPDATE  certificate SET  ruralCard = #{ruralCard} ," +
            " cardID = #{cardID},illname = #{illname}, " +
            " startTime = #{startTime} ,endTime=#{endTime}" +
            " WHERE certificateId= #{certificateId}")
    public boolean updateCertificate(certificate cert);



}
