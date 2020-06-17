package org.wzxy.breeze.mapper;

import org.apache.ibatis.annotations.*;
import org.wzxy.breeze.model.po.chronicdis;
import org.wzxy.breeze.model.po.policy;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
public interface policyMapper {

    /**
     * @author 覃能健
     * @create 2020-06
     */

        @Insert("INSERT INTO policy (runyear,maxline,rate) " +
                " VALUES(#{runyear},#{maxline},#{rate})")
        @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
        public boolean addPolicy(policy p);

        @Select("SELECT IFNULL((SELECT 1 FROM policy WHERE id = #{id} LIMIT 1),0)")
        public  int  isExist(int id);


        @Select("SELECT * FROM policy WHERE id = #{id} ")
        policy findPolicyById(int id);


        @Select("SELECT * FROM policy WHERE runyear = #{runyear} ")
        policy findPolicyByRunyear(String runyear);

        @Select("SELECT * FROM policy")
        public List<policy> getAllPolicy();

        @Delete("DELETE  FROM policy WHERE id = #{id} ")
        boolean deletePolicy(@Param("id") int id);

        @Update("UPDATE  policy SET  runyear = #{runyear},maxline = #{maxline},rate= #{rate}" +
                " WHERE id= #{id}")
        public boolean updatePolicy(policy p);


        @Select("SELECT * FROM policy  LIMIT  #{startPage},#{pageSize} ")
        List<policy> getPolicyByPage(@Param("startPage") int startPage, @Param("pageSize") int pageSize);


        @Select("SELECT COUNT(*) FROM policy")
        public int getTotalCount();


}
