package com.kenny.service.logistics.mapper.user;


import com.kenny.service.logistics.model.user.SmsType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SmsTypeMapper {

    @Insert("INSERT INTO tb_sms_type(name,type,template_id,message,values,signame) VALUES(#{name},#{type},#{template_id},#{message},#{values},#{signame})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SmsType smsType);

    @Update("UPDATE tb_sms_type SET name=#{name},type=#{type},template_id=#{template_id},message=#{message},values=#{values},signame=#{signame} WHERE id=#{id}")
    int update(SmsType smsType);

    @Select("SELECT * FROM tb_sms_type WHERE id=#{id}")
    SmsType selectByPrimaryKey(@Param(value = "id")Integer id);

    @Select("SELECT * FROM tb_sms_type limit #{offset},#{pageSize}")
    List<SmsType> selectPage(@Param(value = "offset")Integer offset,
                             @Param(value = "pageSize")Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_sms_type")
    int count();

    @Delete("DELETE FROM tb_sms_type WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id")Integer id);

    @Select("SELECT * FROM tb_sms_type WHERE type=#{type}")
    SmsType selectByType(@Param(value = "type") String type);
}