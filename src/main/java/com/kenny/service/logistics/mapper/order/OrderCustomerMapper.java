package com.kenny.service.logistics.mapper.order;

import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import com.kenny.service.logistics.model.order.OrderCustomer;

@Mapper
public interface OrderCustomerMapper{

	@Insert("INSERT INTO tb_order_customer(serial_number,order_number,send_name,send_phone,send_addr,send_addr_info,recive_name,recive_phone,recive_addr,recive_addr_info,send_time,recive_time,dispatching_type,status,remark,time,fk_user_id,freight,safes,total) VALUES(#{serial_number},#{order_number},#{send_name},#{send_phone},#{send_addr},#{send_addr_info},#{recive_name},#{recive_phone},#{recive_addr},#{recive_addr_info},#{send_time},#{recive_time},#{dispatching_type},#{status},#{remark},#{time},#{fk_user_id},#{freight},#{safes},#{total})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(OrderCustomer orderCustomer);

	@Update("UPDATE tb_order_customer SET serial_number=#{serial_number},order_number=#{order_number},send_name=#{send_name},send_phone=#{send_phone},send_addr=#{send_addr},send_addr_info=#{send_addr_info},recive_name=#{recive_name},recive_phone=#{recive_phone},recive_addr=#{recive_addr},recive_addr_info=#{recive_addr_info},send_time=#{send_time},recive_time=#{recive_time},dispatching_type=#{dispatching_type},status=#{status},remark=#{remark},time=#{time},fk_user_id=#{fk_user_id},freight=#{freight},safes=#{safes},total=#{total} WHERE id=#{id}")
	int update(OrderCustomer orderCustomer);

	@Select("SELECT * FROM tb_order_customer WHERE id=#{id}")
	OrderCustomer selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_customer ORDER BY time DESC limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order_customer")
	int count();

	@Delete("DELETE FROM tb_order_customer WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order_customer WHERE status = #{status}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPageByStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order_customer WHERE status = #{status}")
	int countByStatus(@Param(value = "status") String status);

	@Select("SELECT * FROM tb_order_customer WHERE fk_user_id = #{fk_user_id}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPageByUser(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_user_id") Integer fk_user_id);

	@Select("SELECT COUNT(*) FROM tb_order_customer WHERE fk_user_id = #{fk_user_id}")
	int countByUser(@Param(value = "fk_user_id") Integer fk_user_id);

	@Select("SELECT * FROM tb_order_customer WHERE fk_user_id = #{fk_user_id} and status = #{status}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPageByUserAndStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_user_id") Integer fk_user_id,@Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order_customer WHERE fk_user_id = #{fk_user_id}  and status = #{status}")
	int countByUserAndStatus(@Param(value = "fk_user_id") Integer fk_user_id,@Param(value = "status") String status);

	//联合tb_user查询
	@Select("SELECT * FROM tb_order_customer INNER JOIN tb_user ON tb_order_customer.fk_user_id = tb_user.id WHERE tb_user.type = #{user_type}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<OrderCustomer> selectPageByUserType(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "user_type") String user_type);

	@Select("SELECT COUNT(*) FROM tb_order_customer INNER JOIN tb_user ON tb_order_customer.fk_user_id = tb_user.id WHERE tb_user.type = #{user_type}")
	int countByUserType(@Param(value = "user_type") String user_type);

	//按天统计
	@Select("SELECT COUNT(*) FROM tb_order_customer WHERE date_format(time,'%Y-%m-%d') = date_format(#{day},'%Y-%m-%d')")
	int countByDay(@Param(value = "day")Date day);


	@Select("SELECT COUNT(*) FROM tb_order_customer WHERE date_format(time,'%Y-%m-%d') = date_format(#{day},'%Y-%m-%d') AND fk_user_id = #{fk_user_id}")
	int countByDayAndUser(@Param(value = "day")Date day,@Param(value = "fk_user_id")Integer fk_user_id);
}
