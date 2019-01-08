package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.po.order.OrderTaking;

@Mapper
public interface OrderTakingMapper{

	@Insert("INSERT INTO tb_order_taking(fk_order_id,fk_car_id,fk_driver_id,freight,safes,recive,pay,time) VALUES(#{fk_order_id},#{fk_car_id},#{fk_driver_id},#{freight},#{safes},#{recive},#{pay},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderTaking orderTaking);

	@Update("UPDATE tb_order_taking SET fk_order_id=#{fk_order_id},fk_car_id=#{fk_car_id},fk_driver_id=#{fk_driver_id},freight=#{freight},safes=#{safes},recive=#{recive},pay=#{pay},time=#{time} WHERE id=#{id}")
	int update(OrderTaking orderTaking);

	@Select("SELECT * FROM tb_order_taking WHERE id=#{id}")
	OrderTaking selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_taking limit #{offset},#{pageSize}")
	List<OrderTaking> selectPage(@Param(value = "offset") Integer offset,
								 @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_taking")
	int count();

	@Delete("DELETE FROM tb_order_taking WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_order_taking WHERE fk_order_id = #{fk_order_id}")
	int deleteByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

	@Select("SELECT * FROM tb_order_taking WHERE fk_order_id = #{fk_order_id}")
	OrderTaking selectByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

}
