package com.kenny.service.logistics.mapper.user;

import com.kenny.service.logistics.model.user.Sms;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SmsMapper {

    @Insert("INSERT INTO tb_sms (code, phone, cookie, code_type_id, sendtime,subtime,is_submit) VALUES(#{code},#{phone},#{cookie},#{code_type_id},#{sendtime},#{subtime},#{is_submit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Sms code);

    @Update("UPDATE tb_sms SET code=#{code}, phone=#{phone}, cookie=#{cookie}, code_type_id=#{code_type_id}, sendtime=#{sendtime},subtime=#{subtime},is_submit=#{is_submit} WHERE id=#{id}")
    int update(Sms code);

    @Select("SELECT * FROM tb_sms WHERE cookie=#{cookie}")
    Sms selectByCookie(@Param(value = "cookie") String cookie);

    @Select("SELECT * FROM tb_sms WHERE phone=#{phone} AND is_submit=0 order by sendtime desc limit 1")
    Sms selectTopByPhone(@Param(value = "phone") String phone);

    @Delete("DELETE form tb_sms WHERE id=#{id}")
    int delete(Sms code);

}