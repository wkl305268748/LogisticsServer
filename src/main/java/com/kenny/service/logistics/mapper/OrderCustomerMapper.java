package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.OrderCustomer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderCustomerMapper {

    @Insert("INSERT INTO tb_order_customer(id,serial_number,order_number,send_name,send_phone,send_addr,send_addr_info,recive_name,revice_phone,recive_addr,recive_addr_info,send_time,recive_time,dispatching_type,time) " +
            "VALUES(#{id},#{serial_number},#{order_number},#{send_name},#{send_phone},#{send_addr},#{send_addr_info},#{recive_name},#{revice_phone},#{recive_addr},#{recive_addr_info},#{send_time},#{recive_time},#{dispatching_type},#{time)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OrderCustomer orderCustomer);

    @Select("SELECT * FROM tb_order_customer WHERE id = #{id}")
    OrderCustomer selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_customer")
    List<OrderCustomer> select();
}
