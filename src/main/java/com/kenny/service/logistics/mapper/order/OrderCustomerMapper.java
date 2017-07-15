package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.order.OrderCustomer;

@Mapper
public interface OrderCustomerMapper{

	@Insert("INSERT INTO order_customer(serial_number,order_number,send_name,send_phone,send_addr,send_addr_info,recive_name,recive_phone,recive_addr,recive_addr_info,send_time,recive_time,dispatching_type,status,order_users,order_users_from,remark,time) VALUES(#{serial_number},#{order_number},#{send_name},#{send_phone},#{send_addr},#{send_addr_info},#{recive_name},#{recive_phone},#{recive_addr},#{recive_addr_info},#{send_time},#{recive_time},#{dispatching_type},#{status},#{order_users},#{order_users_from},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderCustomer orderCustomer);

	@Update("UPDATE order_customer SET serial_number=#{serial_number},order_number=#{order_number},send_name=#{send_name},send_phone=#{send_phone},send_addr=#{send_addr},send_addr_info=#{send_addr_info},recive_name=#{recive_name},recive_phone=#{recive_phone},recive_addr=#{recive_addr},recive_addr_info=#{recive_addr_info},send_time=#{send_time},recive_time=#{recive_time},dispatching_type=#{dispatching_type},status=#{status},order_users=#{order_users},order_users_from=#{order_users_from},remark=#{remark},time=#{time} WHERE id=#{id}")
	int update(OrderCustomer orderCustomer);

	@Select("SELECT * FROM order_customer WHERE id=#{id}")
	OrderCustomer selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM order_customer limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM order_customer")
	int count();

	@Delete("DELETE FROM order_customer WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
