package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.OrderCustomer;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {

    @Insert("INSERT INTO tb_car(car_plate,car_type,car_resource,remark,time,visible) " +
            "VALUES(#{car_plate},#{car_type},#{car_resource},#{remark},#{time},#{visible})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Car car);

    @Select("SELECT * FROM tb_car WHERE id = #{id}")
    Car selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_car")
    List<Car> select();

    @Select("SELECT * FROM tb_car WHERE visible = true limit #{offset},#{pageSize}")
    List<Car> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_car WHERE visible = true")
    int count();

    @Update("UPDATE tb_car SET car_plate=#{car_plate},car_type=#{car_type},car_resource=#{car_resource},remark=#{remark},time=#{time},visible=#{visible} WHERE id=#{id}")
    int update(Car car);
}
