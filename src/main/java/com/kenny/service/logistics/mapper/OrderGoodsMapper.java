package com.kenny.service.logistics.mapper;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.OrderGoods;

@Mapper
public interface OrderGoodsMapper{

	@Insert("INSERT INTO order_goods(fk_order_customer_id,name,size,weight,remark) VALUES(#{fk_order_customer_id},#{name},#{size},#{weight},#{remark})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderGoods orderGoods);

	@Update("UPDATE order_goods SET fk_order_customer_id=#{fk_order_customer_id},name=#{name},size=#{size},weight=#{weight},remark=#{remark} WHERE id=#{id}")
	int update(OrderGoods orderGoods);

	@Select("SELECT * FROM order_goods WHERE id=#{id}")
	OrderGoods selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM order_goods limit #{offset},#{pageSize}")
	List<OrderGoods> selectPage(@Param(value = "offset") Integer offset,
                                @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM order_goods")
	int count();

	@Delete("DELETE FROM order_goods WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
