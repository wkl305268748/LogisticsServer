package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.OrderTaking;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderTakingMapper {

    @Insert("INSERT INTO tb_order_taking(fk_order_customer_id,fk_car_id,fk_driver_id,recive,pay,status,time) " +
            "VALUES(#{fk_order_customer_id},#{fk_car_id},#{fk_driver_id},#{recive},#{pay},#{status},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OrderTaking orderTaking);

    @Select("SELECT * FROM tb_order_taking WHERE id = #{id}")
    OrderTaking selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_taking")
    List<OrderTaking> select();

    @Select("SELECT * FROM tb_order_taking order by time desc limit #{offset},#{pageSize}")
    List<OrderTaking> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_order_taking")
    int count();

    @Update("UPDATE tb_order_taking SET fk_order_customer_id=#{fk_order_customer_id},fk_car_id=#{fk_car_id},fk_driver_id=#{fk_driver_id},recive=#{recive},pay=#{pay},status=#{status},time=#{time}  WHERE id=#{id}")
    int update(OrderTaking orderTaking);
}
