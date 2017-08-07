package com.kenny.service.logistics.mapper.order;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

import com.kenny.service.logistics.model.order.OrderStatus;

@Mapper
public interface OrderStatusMapper {

    @Insert("INSERT INTO tb_order_status(fk_order_customer_id,order_number,status,fk_user_id,time) VALUES(#{fk_order_customer_id},#{order_number},#{status},#{fk_user_id},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OrderStatus orderStatus);

    @Update("UPDATE tb_order_status SET order_number=#{order_number},status=#{status},fk_user_id=#{fk_user_id},time=#{time} WHERE id=#{id}")
    int update(OrderStatus orderStatus);

    @Select("SELECT * FROM tb_order_status WHERE id=#{id}")
    OrderStatus selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_status limit #{offset},#{pageSize}")
    List<OrderStatus> selectPage(@Param(value = "offset") Integer offset,
                                 @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_order_status")
    int count();

    @Delete("DELETE FROM tb_order_status WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_status WHERE fk_order_customer_id=#{fk_order_customer_id}")
    List<OrderStatus> selectByOrderId(@Param(value = "fk_order_customer_id") Integer fk_order_customer_id);

    @Select("SELECT * FROM tb_order_status WHERE status=#{status} limit #{offset},#{pageSize}")
    List<OrderStatus> selectPageByStatus(@Param(value = "offset") Integer offset,
                                         @Param(value = "pageSize") Integer pageSize,
                                         @Param(value = "status") String status);

    @Select("SELECT COUNT(*) FROM tb_order_status WHERE status=#{status}")
    int countByStatus(@Param(value = "status") String status);
}
