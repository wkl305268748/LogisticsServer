package com.kenny.service.logistics.mapper.profit;

import org.apache.ibatis.annotations.*;

import java.util.List;

import com.kenny.service.logistics.model.profit.Profit;

@Mapper
public interface ProfitMapper {

    @Insert("INSERT INTO tb_profit(fk_order_customer_id,order_number,recive,pay,recive_now,pay_now,is_recive,is_pay,profit,time,belong_user_id) VALUES(#{fk_order_customer_id},#{order_number},#{recive},#{pay},#{recive_now},#{pay_now},#{is_recive},#{is_pay},#{profit},#{time},#{belong_user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Profit profit);

    @Update("UPDATE tb_profit SET fk_order_customer_id=#{fk_order_customer_id},order_number=#{order_number},recive=#{recive},pay=#{pay},recive_now=#{recive_now},pay_now=#{pay_now},is_recive=#{is_recive},is_pay=#{is_pay},profit=#{profit},time=#{time},belong_user_id=#{belong_user_id} WHERE id=#{id}")
    int update(Profit profit);

    @Select("SELECT * FROM tb_profit WHERE id=#{id}")
    Profit selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_profit ORDER BY time DESC limit #{offset},#{pageSize}")
    List<Profit> selectPage(@Param(value = "offset") Integer offset,
                            @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_profit")
    int count();

    @Delete("DELETE FROM tb_profit WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_profit WHERE fk_order_customer_id = #{fk_order_customer_id}")
    Profit selectPageByOrderCustomer(@Param(value = "fk_order_customer_id") Integer fk_order_customer_id);

    @Select("SELECT * FROM tb_profit WHERE belong_user_id = #{belong_user_id} ORDER BY time DESC limit #{offset},#{pageSize}")
    List<Profit> selectPageByBelongUser(@Param(value = "offset") Integer offset,
                                        @Param(value = "pageSize") Integer pageSize,
                                        @Param(value = "belong_user_id") Integer belong_user_id);

    @Select("SELECT COUNT(*) FROM tb_profit WHERE belong_user_id = #{belong_user_id}")
    int countByBelongUser(@Param(value = "belong_user_id") Integer belong_user_id);
}
