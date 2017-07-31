package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.order.OrderContract;

@Mapper
public interface OrderContractMapper{

	@Insert("INSERT INTO tb_order_contract(fk_order_customer_id,order_number,contract_number,aname,bname,bbank_name,bbank_number,bbank,time) VALUES(#{fk_order_customer_id},#{order_number},#{contract_number},#{aname},#{bname},#{bbank_name},#{bbank_number},#{bbank},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderContract orderContract);

	@Update("UPDATE tb_order_contract SET fk_order_customer_id=#{fk_order_customer_id},order_number=#{order_number},contract_number=#{contract_number},aname=#{aname},bname=#{bname},bbank_name=#{bbank_name},bbank_number=#{bbank_number},bbank=#{bbank},time=#{time} WHERE id=#{id}")
	int update(OrderContract orderContract);

	@Select("SELECT * FROM tb_order_contract WHERE id=#{id}")
	OrderContract selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_contract limit #{offset},#{pageSize}")
	List<OrderContract> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_contract")
	int count();

	@Delete("DELETE FROM tb_order_contract WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_contract WHERE fk_order_customer_id=#{fk_order_customer_id}")
	OrderContract selectByOrderCustomerId(@Param(value = "fk_order_customer_id") Integer fk_order_customer_id);
}
