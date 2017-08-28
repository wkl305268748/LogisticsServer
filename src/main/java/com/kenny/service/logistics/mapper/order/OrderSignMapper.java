package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.order.OrderSign;

@Mapper
public interface OrderSignMapper{

	@Insert("INSERT INTO tb_order_sign(fk_order_id,order_img,time) VALUES(#{fk_order_id},#{order_img},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderSign orderSign);

	@Update("UPDATE tb_order_sign SET fk_order_id=#{fk_order_id},order_img=#{order_img},time=#{time} WHERE id=#{id}")
	int update(OrderSign orderSign);

	@Select("SELECT * FROM tb_order_sign WHERE id=#{id}")
	OrderSign selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_sign limit #{offset},#{pageSize}")
	List<OrderSign> selectPage(@Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_sign")
	int count();

	@Delete("DELETE FROM tb_order_sign WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Delete("DELETE FROM tb_order_sign WHERE fk_order_id=#{fk_order_id}")
	int deleteByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

	@Select("SELECT * FROM tb_order_sign WHERE fk_order_id = #{fk_order_id}")
	OrderSign selectByOrderId(@Param(value = "fk_order_id") Integer fk_order_id);

}
