package com.kenny.service.logistics.mapper.fleet;

import org.apache.ibatis.annotations.*;

import java.util.List;

import com.kenny.service.logistics.model.fleet.Driver;

@Mapper
public interface DriverMapper {

    @Insert("INSERT INTO tb_driver(name,sex,phone,fk_user_id,is_sms,idcard,email,hometown,remark,time,visible) VALUES(#{name},#{sex},#{phone},#{fk_user_id},#{is_sms},#{idcard},#{email},#{hometown},#{remark},#{time},#{visible})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Driver driver);

    @Update("UPDATE tb_driver SET name=#{name},sex=#{sex},phone=#{phone},fk_user_id=#{fk_user_id},is_sms=#{is_sms},idcard=#{idcard},email=#{email},hometown=#{hometown},remark=#{remark},time=#{time},visible=#{visible} WHERE id=#{id}")
    int update(Driver driver);

    @Select("SELECT * FROM tb_driver WHERE id=#{id}")
    Driver selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_driver WHERE visible=#{visible} order by time desc limit #{offset},#{pageSize}")
    List<Driver> selectPageByVisible(@Param(value = "offset") Integer offset,
                                     @Param(value = "pageSize") Integer pageSize,
                                     @Param(value = "visible") Boolean visible);

    @Select("SELECT COUNT(*) FROM tb_driver WHERE visible=#{visible} order by time desc")
    int countByVisible(@Param(value = "visible") Boolean visible);

    @Delete("DELETE FROM tb_driver WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
