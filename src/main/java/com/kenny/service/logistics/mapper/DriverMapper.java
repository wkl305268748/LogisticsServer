package com.kenny.service.logistics.mapper;

import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.Driver;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DriverMapper {

    @Insert("INSERT INTO tb_driver(name,sex,phone,driver_license,remark,time,visible) " +
            "VALUES(#{name},#{sex},#{phone},#{driver_license},#{remark},#{time},#{visible})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Driver driver);

    @Select("SELECT * FROM tb_driver WHERE id = #{id}")
    Driver selectById(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_driver")
    List<Driver> select();

    @Select("SELECT * FROM tb_driver WHERE visible = true limit #{offset},#{pageSize}")
    List<Driver> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_driver WHERE visible = true")
    int count();

    @Update("UPDATE tb_driver SET name=#{name},sex=#{sex},phone=#{phone},driver_license=#{driver_license},remark=#{remark},time=#{time},visible=#{visible} WHERE id=#{id}")
    int update(Driver driver);
}
