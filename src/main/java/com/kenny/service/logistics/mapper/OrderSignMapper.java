package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.OrderSign;
import com.kenny.service.logistics.model.OrderTaking;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderSignMapper {

    @Insert("INSERT INTO tb_order_sign(fk_order_taking_id,order_img,time) " +
            "VALUES(#{fk_order_taking_id},#{order_img},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(OrderSign orderSign);

    @Select("SELECT * FROM tb_order_sign WHERE id = #{id}")
    OrderSign selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_order_sign WHERE fk_order_taking_id = #{fk_order_taking_id}")
    OrderSign selectByOrderTakingId(@Param(value = "fk_order_taking_id") Integer fk_order_taking_id);

    @Select("SELECT * FROM tb_order_sign")
    List<OrderSign> select();

    @Select("SELECT * FROM tb_order_sign  limit #{offset},#{pageSize}")
    List<OrderSign> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_order_sign")
    int count();

    @Update("UPDATE tb_order_sign SET fk_order_taking_id=#{fk_order_taking_id},order_img=#{order_img},time=#{time} WHERE id=#{id}")
    int update(OrderSign orderSign);
}
