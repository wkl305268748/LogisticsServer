package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.Profit;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProfitMapper {

    @Insert("INSERT INTO tb_profit(order_number,fk_order_taking_id,recive,pay,is_recive,is_pay,profit) " +
            "VALUES(#{order_number},#{fk_order_taking_id},#{recive},#{pay},#{is_recive},#{is_pay},#{profit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Profit profit);

    @Select("SELECT * FROM tb_profit WHERE id = #{id}")
    Profit selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_profit")
    List<Profit> select();

    @Select("SELECT * FROM tb_profit limit #{offset},#{pageSize}")
    List<Profit> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_profit")
    int count();

    @Update("UPDATE tb_profit SET order_number=#{order_number},fk_order_taking_id=#{fk_order_taking_id},recive=#{recive},pay=#{pay},is_pay=#{is_pay},is_recive=#{is_recive},profit=#{profit} WHERE id=#{id}")
    int update(Profit profit);
}
