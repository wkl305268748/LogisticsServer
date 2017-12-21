package com.kenny.service.logistics.mapper.order;

import com.kenny.service.logistics.model.order.OrderCustomer;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import com.kenny.service.logistics.model.order.Order;

@Mapper
public interface OrderMapper{

	@Insert("INSERT INTO tb_order(order_number,serial_number,fk_customer_id,is_company,fk_want_company_id,fk_company_id,status,time) VALUES(#{order_number},#{serial_number},#{fk_customer_id},#{is_company},#{fk_want_company_id},#{fk_company_id},#{status},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Order order);

	@Update("UPDATE tb_order SET order_number=#{order_number},serial_number=#{serial_number},fk_customer_id=#{fk_customer_id},is_company=#{is_company},fk_want_company_id=#{fk_want_company_id},fk_company_id=#{fk_company_id},status=#{status},time=#{time} WHERE id=#{id}")
	int update(Order order);

	@Select("SELECT * FROM tb_order WHERE id=#{id}")
	Order selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_order ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPage(@Param(value = "offset") Integer offset,
                           @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_order")
	int count();

	@Delete("DELETE FROM tb_order WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	//按状态
	@Select("SELECT * FROM tb_order WHERE status = #{status}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order WHERE status = #{status}")
	int countByStatus(@Param(value = "status") String status);

	//按下单用户
	@Select("SELECT * FROM tb_order WHERE fk_customer_id = #{fk_customer_id}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByCustomer(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_customer_id") Integer fk_customer_id);

	@Select("SELECT COUNT(*) FROM tb_order WHERE fk_customer_id = #{fk_customer_id}")
	int countByCustomer(@Param(value = "fk_customer_id") Integer fk_customer_id);

	//按接单物流公司查询（指定物流公司或者接单物流公司）
	@Select("SELECT * FROM tb_order WHERE fk_company_id = #{fk_company_id} OR fk_want_company_id = #{fk_company_id}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByCompany(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_company_id") Integer fk_company_id);

	@Select("SELECT COUNT(*) FROM tb_order WHERE fk_company_id = #{fk_company_id} OR fk_want_company_id = #{fk_company_id}")
	int countByCompany(@Param(value = "fk_company_id") Integer fk_company_id);

	//按接单物流公司查询和状态（指定物流公司或者接单物流公司）
	@Select("SELECT * FROM tb_order WHERE fk_company_id = #{fk_company_id} OR fk_want_company_id = #{fk_company_id}  AND status = #{status} ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByCompanyAndStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_company_id") Integer fk_company_id,@Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order WHERE (fk_company_id = #{fk_company_id} OR fk_want_company_id = #{fk_company_id}) AND status = #{status}")
	int countByCompanyAndStatus(@Param(value = "fk_company_id") Integer fk_company_id,@Param(value = "status") String status);

	//联合OrderTaking表
	//按司机ID查询所有订单
	@Select("SELECT tb_order.* FROM tb_order,tb_order_taking WHERE tb_order.id = tb_order_taking.fk_order_id AND tb_order_taking.fk_driver_id = #{fk_driver_id} ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByDriver(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_driver_id") Integer fk_driver_id);

	@Select("SELECT COUNT(*) FROM tb_order,tb_order_taking WHERE tb_order.id = tb_order_taking.fk_order_id AND tb_order_taking.fk_driver_id = #{fk_driver_id}")
	int countByDriver(@Param(value = "fk_driver_id") Integer fk_driver_id);

	@Select("SELECT tb_order.* FROM tb_order,tb_order_taking WHERE tb_order.id = tb_order_taking.fk_order_id AND tb_order_taking.fk_driver_id = #{fk_driver_id} AND tb_order.status = #{status} ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByDriverAndStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_driver_id") Integer fk_driver_id,@Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order,tb_order_taking WHERE tb_order.id = tb_order_taking.fk_order_id AND tb_order_taking.fk_driver_id = #{fk_driver_id} AND tb_order.status = #{status}")
	int countByDriverAndStatus(@Param(value = "fk_driver_id") Integer fk_driver_id,@Param(value = "status") String status);

	//查询开放订单
	@Select("SELECT * FROM tb_order WHERE is_company = 0 AND status = #{status} ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByOpenCompany(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize,@Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order WHERE is_company = 0 AND status = #{status}")
	int countByOpenCompany(@Param(value = "status") String status);

	//按下单用户和状态
	@Select("SELECT * FROM tb_order WHERE fk_user_id = #{fk_customer_id} and status = #{status}  ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Order> selectPageByCustomerAndStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize, @Param(value = "fk_customer_id") Integer fk_customer_id,@Param(value = "status") String status);

	@Select("SELECT COUNT(*) FROM tb_order WHERE fk_customer_id = #{fk_customer_id}  and status = #{status}")
	int countByCustomerAndStatus(@Param(value = "fk_customer_id") Integer fk_customer_id,@Param(value = "status") String status);

	//按天统计订单数
	@Select("SELECT COUNT(*) FROM tb_order WHERE date_format(time,'%Y-%m-%d') = date_format(#{day},'%Y-%m-%d')")
	int countByDay(@Param(value = "day")Date day);

	//按天统计指定下单人的订单数
	@Select("SELECT COUNT(*) FROM tb_order WHERE date_format(time,'%Y-%m-%d') = date_format(#{day},'%Y-%m-%d') AND fk_customer_id = #{fk_customer_id}")
	int countByDayAndCustomer(@Param(value = "day")Date day,@Param(value = "fk_customer_id")Integer fk_customer_id);

	//按天统计指定公司的订单数
	@Select("SELECT COUNT(*) FROM tb_order WHERE date_format(time,'%Y-%m-%d') = date_format(#{day},'%Y-%m-%d') AND fk_want_company_id = #{fk_want_company_id}")
	int countByDayAndWantCompany(@Param(value = "day")Date day,@Param(value = "fk_want_company_id")Integer fk_want_company_id);
}
