package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.model.po.expense;
import org.wzxy.breeze.model.po.policy;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface expenseMapper {

    @Insert("INSERT INTO expense (cardID,payerName,illname,runyear,amount,administrationId,state) " +
            " VALUES(#{cardID},#{payerName},#{illname},#{runyear},#{amount},#{administrationId},#{state})")
    @Options(useGeneratedKeys=true, keyProperty="Id", keyColumn="Id")
    public boolean addExpense(expense e);

    @Select("SELECT IFNULL((SELECT 1 FROM expense WHERE Id = #{Id} LIMIT 1),0)")
    public  int  isExist(int id);


    @Select("SELECT * FROM expense WHERE Id = #{Id} ")
    @Results({
            @Result(id=true,column="Id",property="Id"),
            @Result(column="cardID",property="cardID"),
            @Result(column="payerName",property="payerName"),
            @Result(column="illname",property="illname"),
            @Result(column="runyear",property="runyear"),
            @Result(column="amount",property="amount"),
            @Result(column="state",property="state"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column = "administrationId",property = "operator",
                    one = @One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    expense findExpenseById(@Param("Id") int Id);


    @Select("SELECT * FROM expense WHERE cardID = #{cardID} ")
    @Results({
            @Result(id=true,column="Id",property="Id"),
            @Result(column="cardID",property="cardID"),
            @Result(column="payerName",property="payerName"),
            @Result(column="illname",property="illname"),
            @Result(column="runyear",property="runyear"),
            @Result(column="amount",property="amount"),
            @Result(column="state",property="state"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column = "administrationId",property = "operator",
                    one = @One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    List<expense> findExpenseByCardID(@Param("cardID") String cardID);


    @Delete("DELETE  FROM expense WHERE Id = #{Id} ")
    boolean deleteExpense(@Param("Id") int Id);

    @Update("UPDATE  expense SET  cardID = #{cardID},payerName = #{payerName},illname= #{illname}," +
            " runyear = #{runyear},amount = #{amount}  " +
            " WHERE Id= #{Id}")
    public boolean updateExpense(expense e);


    @Update("UPDATE  expense SET  state = #{state}  " +
            " WHERE Id= #{Id}")
    public boolean updateExpenseState(expense e);


    @Select("SELECT * FROM expense  WHERE  " +
            " administrationId  IN (SELECT administrationId FROM organization WHERE administrationId= #{administrationId}  OR administrationPid= #{administrationId} )  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="Id",property="Id"),
            @Result(column="cardID",property="cardID"),
            @Result(column="payerName",property="payerName"),
            @Result(column="illname",property="illname"),
            @Result(column="runyear",property="runyear"),
            @Result(column="amount",property="amount"),
            @Result(column="state",property="state"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column = "administrationId",property = "operator",
                    one = @One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    List<expense> getExpenseByPage(@Param("administrationId") int administrationId, @Param("startPage") int startPage, @Param("pageSize") int pageSize);



    @Select("SELECT * FROM expense  WHERE  " +   "  state=#{state} or state=#{remit}  AND "  +
            " administrationId  IN (SELECT administrationId FROM organization WHERE administrationId= #{administrationId}  OR administrationPid= #{administrationId} )  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="Id",property="Id"),
            @Result(column="cardID",property="cardID"),
            @Result(column="payerName",property="payerName"),
            @Result(column="illname",property="illname"),
            @Result(column="runyear",property="runyear"),
            @Result(column="amount",property="amount"),
            @Result(column="state",property="state"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column = "administrationId",property = "operator",
                    one = @One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    List<expense> getExpenseByStatePage(@Param("administrationId") int administrationId, @Param("state")  String state ,@Param("remit")  String remit ,@Param("startPage") int startPage, @Param("pageSize") int pageSize);




    @Select("SELECT * FROM expense  WHERE  " +   "  state=#{state} or state=#{audit} or state=#{pass} AND "  +
            " administrationId  IN (SELECT administrationId FROM organization WHERE administrationId= #{administrationId}  OR administrationPid= #{administrationId} )  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="Id",property="Id"),
            @Result(column="cardID",property="cardID"),
            @Result(column="payerName",property="payerName"),
            @Result(column="illname",property="illname"),
            @Result(column="runyear",property="runyear"),
            @Result(column="amount",property="amount"),
            @Result(column="state",property="state"),
            @Result(column="administrationId",property="administrationId"),
            @Result(column = "administrationId",property = "operator",
                    one = @One(select = "org.wzxy.breeze.mapper.organizationMapper.findOrganById",fetchType = FetchType.LAZY)
            )
    })
    List<expense> checkExpenseByStatePage(@Param("administrationId") int administrationId, @Param("state")  String state ,@Param("audit")  String audit,@Param("pass")  String pass ,@Param("startPage") int startPage, @Param("pageSize") int pageSize);


    @Select("SELECT COUNT(*) FROM expense WHERE administrationId  IN (SELECT administrationId FROM organization WHERE administrationId= #{administrationId}  OR administrationPid= #{administrationId} )")
    public int getTotalCount(int administrationId);



}
