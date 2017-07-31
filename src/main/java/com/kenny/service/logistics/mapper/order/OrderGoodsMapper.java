package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.order.OrderGoods;

@Mapper
public interface OrderGoodsMapper{

	@Insert("INSERT INTO tb_order_goods(fk_order_customer_id,name,size,weight,number,freight,remark) VALUES(#{fk_order_customer_id},#{name},#{size},#{weight},#{number},#{freight},#{remark})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderGoods orderGoods);

	@Update("UPDATE tb_order_goods SET fk_order_customer_id=#{fk_order_customer_id},name=#{name},size=#{size},weight=#{weight},remark=#{remark},number=#{number},freight=#{freight}, WHERE id=#{id}")
	int update(OrderGoods orderGoods);

	@Select("SELECT * FROM tb_order_goods WHERE id=#{id}")
	OrderGoods selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_goods limit #{offset},#{pageSize}")
	List<OrderGoods> selectPage(@Param(value = "offset") Integer offset,
                                @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_goods")
	int count();

	@Delete("DELETE FROM tb_order_goods WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_order_goods WHERE fk_order_customer_id=#{fk_order_customer_id}")
	int deleteByOrderCustomer(@Param(value = "fk_order_customer_id") Integer fk_order_customer_id);


	@Select("SELECT * FROM tb_order_goods WHERE fk_order_customer_id=#{fk_order_customer_id}")
	List<OrderGoods> selectByOrderCustomerId(@Param(value = "fk_order_customer_id") Integer fk_order_customer_id);
}
