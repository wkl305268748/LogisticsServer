package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

import com.kenny.service.logistics.model.po.order.OrderStatus;

@Mapper
public interface OrderStatusMapper {

    @Insert("INSERT INTO tb_order_status(fk_order_id,order_number,status,fk_user_id,time) VALUES(#{fk_order_id},#{order_number},#{status},#{fk_user_id},#{time})")
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

    @Delete("DELETE FROM tb_order_status WHERE fk_order_id=#{fk_order_id}")
    int deleteByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

    @Select("SELECT * FROM tb_order_status WHERE fk_order_id=#{fk_order_id}")
    List<OrderStatus> selectByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

    //按天统计指定操作用户和状态的订单数
    @Select("SELECT COUNT(*) FROM tb_order_status WHERE date_format(time,'%Y-%m-%d') = date_format(#{day},'%Y-%m-%d') AND fk_user_id = #{fk_user_id} AND status=#{status}")
    int countByDayAndUserAndStatus(@Param(value = "day")Date day, @Param(value = "fk_user_id")Integer fk_user_id, @Param(value = "status")String status);
}
