package com.kenny.service.logistics.mapper.user;

import com.kenny.service.logistics.model.po.user.Sms;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SmsMapper {
    @Insert("INSERT INTO tb_sms(code,phone,cookie,code_type_id,sendtime,subtime,is_submit) VALUES(#{code},#{phone},#{cookie},#{code_type_id},#{sendtime},#{subtime},#{is_submit})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Sms sms);

    @Update("UPDATE tb_sms SET code=#{code},phone=#{phone},cookie=#{cookie},code_type_id=#{code_type_id},sendtime=#{sendtime},subtime=#{subtime},is_submit=#{is_submit} WHERE id=#{id}")
    int update(Sms sms);

    @Select("SELECT * FROM tb_sms WHERE id=#{id}")
    Sms selectByPrimaryKey(@Param(value = "id")Integer id);

    @Select("SELECT * FROM tb_sms limit #{offset},#{pageSize}")
    List<Sms> selectPage(@Param(value = "offset")Integer offset,
                         @Param(value = "pageSize")Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_sms")
    int count();

    @Delete("DELETE FROM tb_sms WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id")Integer id);

    @Select("SELECT * FROM tb_sms WHERE cookie=#{cookie}")
    Sms selectByCookie(@Param(value = "cookie") String cookie);

    @Select("SELECT * FROM tb_sms WHERE phone=#{phone} AND is_submit=0 order by sendtime desc limit 1")
    Sms selectTopByPhone(@Param(value = "phone") String phone);
}