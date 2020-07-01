package org.wzxy.breeze.core.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.wzxy.breeze.core.model.po.payment;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface paymentMapper {


    @Select("SELECT IFNULL((SELECT 1 from payment where paymentId = #{paymentId} limit 1),0)")
    public  int  isExist(int paymentId);


    @Select("SELECT IFNULL((SELECT 1 from payment where cardID = #{cardID} limit 1),0)")
    public  int  isPay(String cardID);


    @Select("SELECT * FROM payment WHERE  cardID IN  " +
            " (SELECT cardID FROM person WHERE  famicode  IN  " +
            " (SELECT famicode FROM family WHERE  regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId}) ) " +
            " ) " +
            "  LIMIT  #{startPage},#{pageSize} ")
    @Results({
            @Result(id=true,column="paymentId",property="paymentId"),
            @Result(column="payDate",property="payDate"),
            @Result(column="invoiceNum",property="invoiceNum"),
            @Result(column="payerName",property="payerName"),
            @Result(column="cardID",property="cardID"),
            @Result(column = "cardID",property = "payer",
                    one = @One(select = "org.wzxy.breeze.core.mapper.personMapper.findPersonByCardID",fetchType = FetchType.LAZY)
            ),
    })
    List<payment> getPaymentsByPage(@Param("regionId")int  regionId, @Param("startPage") int startPage, @Param("pageSize") int pageSize);



    @Insert("INSERT INTO payment (payDate,invoiceNum,payerName,cardID) " +
            " VALUES(#{payDate},#{invoiceNum},#{payerName},#{cardID})")
    @Options(useGeneratedKeys=true, keyProperty="paymentId", keyColumn="paymentId")
    public boolean addPayment(payment pay);


    @Select("SELECT * FROM payment WHERE paymentId =  #{paymentId}  " )
    @Results({
            @Result(id=true,column="paymentId",property="paymentId"),
            @Result(column="payDate",property="payDate"),
            @Result(column="invoiceNum",property="invoiceNum"),
            @Result(column="payerName",property="payerName"),
            @Result(column="cardID",property="cardID"),
            @Result(column = "cardID",property = "payer",
                    one = @One(select = "org.wzxy.breeze.core.mapper.personMapper.findPersonByCardID",fetchType = FetchType.LAZY)
            ),
    })
    payment findPaymentById(@Param("paymentId")int paymentId );


    @Select("SELECT COUNT(*) FROM payment WHERE  cardID IN  " +
            " (SELECT cardID FROM person WHERE  famicode  IN  " +
            " (SELECT famicode FROM family WHERE  regionId IN  (SELECT regionId FROM region WHERE  regionId=#{regionId} OR regionPid=#{regionId}) ) " +
            " ) ")
    public int getTotalCount(int regionId);


    @Delete("DELETE  FROM payment WHERE paymentId = #{paymentId} ")
    boolean deletePayment(@Param("paymentId") int paymentId);


    @Update("UPDATE  payment SET  payDate = #{payDate} ," +
            " invoiceNum = #{invoiceNum},payerName = #{payerName}, " +
            " cardID = #{cardID} " +
            " WHERE paymentId= #{paymentId}")
    public boolean updatePayment(payment pay);


}
