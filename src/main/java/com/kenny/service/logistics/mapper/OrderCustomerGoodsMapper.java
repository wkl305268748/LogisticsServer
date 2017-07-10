package com.kenny.service.logistics.mapper;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.OrderCustomerGoods;

@Mapper
public interface OrderCustomerGoodsMapper{

	@Insert("INSERT INTO order_customer_goods(fk_goods_id,fk_order_customer_id) VALUES(#{fk_goods_id},#{fk_order_customer_id})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderCustomerGoods orderCustomerGoods);

	@Update("UPDATE order_customer_goods SET fk_goods_id=#{fk_goods_id},fk_order_customer_id=#{fk_order_customer_id} WHERE id=#{id}")
	int update(OrderCustomerGoods orderCustomerGoods);

	@Select("SELECT * FROM order_customer_goods WHERE id=#{id}")
	OrderCustomerGoods selectByPrimaryKey(@Param(value = "id")Integer id);

	@Select("SELECT * FROM order_customer_goods limit #{offset},#{pageSize}")
	List<OrderCustomerGoods> selectPage(@Param(value = "offset")Integer offset,
	                                    @Param(value = "pageSize")Integer pageSize);

	@Select("SELECT COUNT(*) FROM order_customer_goods")
	int count();

	@Delete("DELETE FROM order_customer_goods WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id")Integer id);

}
