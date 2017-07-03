package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.OrderCustomer;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderCustomerMapper {

    @Insert("INSERT INTO tb_order_customer(serial_number,order_number,send_name,send_phone,send_addr,send_addr_info,recive_name,recive_phone,recive_addr,recive_addr_info,send_time,recive_time,dispatching_type,time,status,remark) " +
            "VALUES(#{serial_number},#{order_number},#{send_name},#{send_phone},#{send_addr},#{send_addr_info},#{recive_name},#{recive_phone},#{recive_addr},#{recive_addr_info},#{send_time},#{recive_time},#{dispatching_type},#{time},#{status},#{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OrderCustomer orderCustomer);

    @Select("SELECT * FROM tb_order_customer WHERE id = #{id}")
    OrderCustomer selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_customer")
    List<OrderCustomer> select();

    @Select("SELECT * FROM tb_order_customer WHERE status = #{status} order by time desc limit #{offset},#{pageSize}")
    List<OrderCustomer> selectPageByStatus(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize,  @Param(value = "status") String status);

    @Select("SELECT COUNT(*) FROM tb_order_customer WHERE status = #{status}")
    int countByStatus(@Param(value = "status") String status);


    @Select("SELECT * FROM tb_order_customer order by time desc limit #{offset},#{pageSize}")
    List<OrderCustomer> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_order_customer")
    int count();

    @Update("UPDATE tb_order_customer SET serial_number=#{serial_number},order_number=#{order_number},send_name=#{send_name},send_phone=#{send_phone},send_addr=#{send_addr},send_addr_info=#{send_addr_info}," +
            "recive_name=#{recive_name},recive_phone=#{recive_phone},recive_addr=#{recive_addr},recive_addr_info=#{recive_addr_info},send_time=#{send_time},recive_time=#{recive_time},dispatching_type=#{dispatching_type},time=#{time},status=#{status},remark=#{remark}  WHERE id=#{id}")
    int update(OrderCustomer orderCustomer);

    @Delete("DELETE FROM tb_order_customer WHERE id=#{id}")
    int deleteById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_customer WHERE #{where} order by time desc limit 0,20")
    int search(@Param(value = "where")String where);
}
