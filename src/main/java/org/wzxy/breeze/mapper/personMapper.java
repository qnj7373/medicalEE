package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.person;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface personMapper {

    @Select("SELECT * FROM person WHERE perscode=#{perscode}")
    @Results({
            @Result(id=true,column="perscode",property="perscode"),
            @Result(column="persname",property="persname"),
            @Result(column="cardID",property="cardID"),
            @Result(column="famicode",property="famicode"),
            @Result(column = "famicode",property = "family",
                    one = @One(select = "org.wzxy.breeze.mapper.familyMapper.findFamilyByFamicode",fetchType = FetchType.LAZY)
            ),/////关系
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="relation",property="relation"),
            @Result(column="age",property="age"),
            @Result(column="sex",property="sex"),
            @Result(column="birthday",property="birthday"),
            @Result(column="occupation",property="occupation"),
            @Result(column="workUnit",property="workUnit"),
            @Result(column="liveAddress",property="liveAddress"),
            @Result(column="telephone",property="telephone"),
    })
    public person findPersonById(int perscode);


    @Select("SELECT * FROM person WHERE cardID=#{cardID}")
    @Results({
            @Result(id=true,column="perscode",property="perscode"),
            @Result(column="persname",property="persname"),
            @Result(column="cardID",property="cardID"),
            @Result(column="famicode",property="famicode"),
            @Result(column = "famicode",property = "family",
                    one = @One(select = "org.wzxy.breeze.mapper.familyMapper.findFamilyByFamicode",fetchType = FetchType.LAZY)
            ),/////关系
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="relation",property="relation"),
            @Result(column="age",property="age"),
            @Result(column="sex",property="sex"),
            @Result(column="birthday",property="birthday"),
            @Result(column="occupation",property="occupation"),
            @Result(column="workUnit",property="workUnit"),
            @Result(column="liveAddress",property="liveAddress"),
            @Result(column="telephone",property="telephone"),
    })
    public person findPersonByCardID(String cardID);


    @Select("SELECT * FROM person WHERE famicode  IN (SELECT famicode FROM family WHERE holderName LIKE '%${name}%' " +
            "             AND    famicode IN   (SELECT famicode  FROM family  WHERE   regionId IN  ( SELECT regionId FROM region  WHERE  regionId=2 OR regionPid=2 ) ) )" )
    @Results({
            @Result(id=true,column="perscode",property="perscode"),
            @Result(column="persname",property="persname"),
            @Result(column="cardID",property="cardID"),
            @Result(column="famicode",property="famicode"),
            @Result(column = "famicode",property = "family",
                    one = @One(select = "org.wzxy.breeze.mapper.familyMapper.findFamilyByFamicode",fetchType = FetchType.LAZY)
            ),/////关系
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="relation",property="relation"),
            @Result(column="age",property="age"),
            @Result(column="sex",property="sex"),
            @Result(column="birthday",property="birthday"),
            @Result(column="occupation",property="occupation"),
            @Result(column="workUnit",property="workUnit"),
            @Result(column="liveAddress",property="liveAddress"),
            @Result(column="telephone",property="telephone"),
    })
    public List<person> findPersonByHolder(@Param("regionId") int regionId,@Param("name") String name);


    @Select("SELECT * FROM person WHERE cardID LIKE '%${cardID}%'   " +
            " AND    famicode IN   (SELECT famicode  FROM family  WHERE   regionId IN  ( SELECT regionId FROM region  WHERE  regionId=#{regionId} OR regionPid=#{regionId} ) )  " )
    @Results({
            @Result(id=true,column="perscode",property="perscode"),
            @Result(column="persname",property="persname"),
            @Result(column="cardID",property="cardID"),
            @Result(column="famicode",property="famicode"),
            @Result(column = "famicode",property = "family",
                    one = @One(select = "org.wzxy.breeze.mapper.familyMapper.findFamilyByFamicode",fetchType = FetchType.LAZY)
            ),/////关系
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="relation",property="relation"),
            @Result(column="age",property="age"),
            @Result(column="sex",property="sex"),
            @Result(column="birthday",property="birthday"),
            @Result(column="occupation",property="occupation"),
            @Result(column="workUnit",property="workUnit"),
            @Result(column="liveAddress",property="liveAddress"),
            @Result(column="telephone",property="telephone"),
    })
    public List<person> findPersonByCardIDToExpense(@Param("regionId")int regionId,@Param("cardID") String cardID);




    @Select("SELECT COUNT(*) FROM person WHERE famicode = #{famicode} ")
    public int getTotalCount(@Param("famicode") int famicode);

    @Select("SELECT * FROM person  WHERE famicode=#{famicode} LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="perscode",property="perscode"),
            @Result(column="persname",property="persname"),
            @Result(column="cardID",property="cardID"),
            @Result(column="famicode",property="famicode"),
            @Result(column = "famicode",property = "family",
                    one = @One(select = "org.wzxy.breeze.mapper.familyMapper.findFamilyByFamicode",fetchType = FetchType.LAZY)
            ),/////关系
            @Result(column="ruralCard",property="ruralCard"),
            @Result(column="relation",property="relation"),
            @Result(column="age",property="age"),
            @Result(column="sex",property="sex"),
            @Result(column="birthday",property="birthday"),
            @Result(column="occupation",property="occupation"),
            @Result(column="workUnit",property="workUnit"),
            @Result(column="liveAddress",property="liveAddress"),
            @Result(column="telephone",property="telephone"),
    })
    List<person> getPersonsByPage(@Param("famicode") int famicode,@Param("startPage") int startPage, @Param("pageSize") int pageSize);


    @Insert("INSERT INTO person (persname,cardID,famicode,ruralCard," +
            "relation,age,sex,birthday,occupation,workUnit,liveAddress,telephone) " +
            " VALUES(#{persname},#{cardID},#{famicode},#{ruralCard}," +
            "#{relation},#{age},#{sex},#{birthday},#{occupation},#{workUnit},#{liveAddress},#{telephone})")
    @Options(useGeneratedKeys=true, keyProperty="perscode", keyColumn="perscode")
    public boolean addPerson(person person);



    @Update("UPDATE  person SET  persname = #{persname} ," +
            " cardID = #{cardID},famicode= #{famicode}, " +
            " ruralCard = #{ruralCard},relation = #{relation}, " +
            " age = #{age},sex = #{sex} ,birthday = #{birthday} ,occupation = #{occupation} ," +
            " workUnit = #{workUnit},liveAddress = #{liveAddress} ,telephone = #{telephone} " +
            " WHERE perscode= #{perscode}")
    public boolean updatePerson(person person);

    @Select("SELECT IFNULL((SELECT 1 from person where famicode = #{famicode} limit 1),0)")
    public  int  familyIsExist(int famicode);

    @Select("SELECT IFNULL((SELECT 1 from person where cardID = #{cardID} limit 1),0)")
    public  int  cardIDIsExist(String cardID);


    @Select("SELECT IFNULL((SELECT 1 from person where cardID IN (SELECT cardID FROM person WHERE  famicode  IN (SELECT famicode FROM family WHERE  regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId}) ) " +
            " ) limit 1),0)")
    public  int  cardIDIsInManage(@Param("cardID") String cardID,@Param("regionId")int regionId);


    @Select("SELECT IFNULL((SELECT 1 from person where perscode = #{perscode} limit 1),0)")
    public  int  isExist(int perscode);


    @Select("SELECT IFNULL((SELECT 1 from person where famicode = #{famicode} AND relation=#{relation} limit 1),0)")
    public  int  isHolderOrPartnerExist(@Param("famicode") int famicode,@Param("relation")String relation);



    @Delete("DELETE  FROM person WHERE famicode = #{famicode} ")
    boolean deletePersonOfFamily(@Param("famicode") int famicode);



    @Delete("DELETE  FROM person WHERE perscode = #{perscode} ")
    boolean deletePerson(@Param("perscode") int perscode);


    @Update("UPDATE  person SET  persname = #{persname}  " +
            " WHERE famicode= #{famicode} AND relation=#{relation}")
    public boolean upDateholder(@Param("famicode") int famicode,@Param("relation")String relation,@Param("persname")String persname);



}
