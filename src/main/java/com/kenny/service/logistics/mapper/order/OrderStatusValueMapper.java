package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.po.order.OrderStatusValue;

@Mapper
public interface OrderStatusValueMapper{

	@Insert("INSERT INTO tb_order_status_value(value,remark) VALUES(#{value},#{remark})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderStatusValue orderStatusValue);

	@Update("UPDATE tb_order_status_value SET value=#{value},remark=#{remark} WHERE id=#{id}")
	int update(OrderStatusValue orderStatusValue);

	@Select("SELECT * FROM tb_order_status_value WHERE id=#{id}")
	OrderStatusValue selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_status_value limit #{offset},#{pageSize}")
	List<OrderStatusValue> selectPage(@Param(value = "offset") Integer offset,
                                      @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_status_value")
	int count();

	@Delete("DELETE FROM tb_order_status_value WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
