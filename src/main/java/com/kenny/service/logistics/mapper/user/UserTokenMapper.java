package com.kenny.service.logistics.mapper.user;


import com.kenny.service.logistics.model.user.UserToken;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserTokenMapper {

    @Insert("INSERT INTO tb_user_token(token,user_id,time) VALUES (#{token},#{user_id},#{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserToken userToken);

    @Select("SELECT * FROM tb_user_token WHERE token=#{token}")
    UserToken selectByToken(String token);

    @Delete("DELETE  FROM tb_user_token WHERE user_id=#{user_id}")
    int delete(UserToken userToken);

    @Delete("DELETE  FROM tb_user_token WHERE user_id=#{user_id}")
    int deleteTokenByUserId(@Param(value = "user_id") Integer user_id);
}