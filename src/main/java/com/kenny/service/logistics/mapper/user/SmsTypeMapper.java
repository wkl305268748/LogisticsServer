package com.kenny.service.logistics.mapper.user;


import com.kenny.service.logistics.model.user.SmsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SmsTypeMapper {

    @Select("SELECT * FROM tb_sms_type WHERE type=#{type}")
    SmsType selectByType(@Param(value = "type") String type);
}