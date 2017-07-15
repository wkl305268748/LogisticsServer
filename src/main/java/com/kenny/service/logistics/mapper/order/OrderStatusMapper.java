package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.order.OrderStatus;

@Mapper
public interface OrderStatusMapper{

	@Insert("INSERT INTO order_status(order_number,status,time) VALUES(#{order_number},#{status},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderStatus orderStatus);

	@Update("UPDATE order_status SET order_number=#{order_number},status=#{status},time=#{time} WHERE id=#{id}")
	int update(OrderStatus orderStatus);

	@Select("SELECT * FROM order_status WHERE id=#{id}")
	OrderStatus selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM order_status limit #{offset},#{pageSize}")
	List<OrderStatus> selectPage(@Param(value = "offset") Integer offset,
                                 @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM order_status")
	int count();

	@Delete("DELETE FROM order_status WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
