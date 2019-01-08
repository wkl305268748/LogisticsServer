package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.po.order.OrderCustomer;

@Mapper
public interface OrderCustomerMapper{

	@Insert("INSERT INTO tb_order_customer(fk_order_id,order_number,send_name,send_phone,send_addr,send_addr_info,recive_name,recive_phone,recive_addr,recive_addr_info,send_time,recive_time,dispatching_type,remark,time) VALUES(#{fk_order_id},#{order_number},#{send_name},#{send_phone},#{send_addr},#{send_addr_info},#{recive_name},#{recive_phone},#{recive_addr},#{recive_addr_info},#{send_time},#{recive_time},#{dispatching_type},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderCustomer orderCustomer);

	@Update("UPDATE tb_order_customer SET fk_order_id=#{fk_order_id},order_number=#{order_number},send_name=#{send_name},send_phone=#{send_phone},send_addr=#{send_addr},send_addr_info=#{send_addr_info},recive_name=#{recive_name},recive_phone=#{recive_phone},recive_addr=#{recive_addr},recive_addr_info=#{recive_addr_info},send_time=#{send_time},recive_time=#{recive_time},dispatching_type=#{dispatching_type},remark=#{remark} WHERE id=#{id}")
	int update(OrderCustomer orderCustomer);

	@Select("SELECT * FROM tb_order_customer WHERE id=#{id}")
	OrderCustomer selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_customer limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_customer")
	int count();

	@Delete("DELETE FROM tb_order_customer WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_order_customer WHERE fk_order_id = #{fk_order_id}")
	int deleteByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

	@Select("SELECT * FROM tb_order_customer WHERE fk_order_id = #{fk_order_id}")
	OrderCustomer selectByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);
}
