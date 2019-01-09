package com.kenny.service.logistics.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kenny.service.logistics.model.po.user.UserToken;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

    @Insert("INSERT INTO tb_user_token(token,user_id,time,is_valid) VALUES(#{token},#{user_id},#{time},#{is_valid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserToken userToken);

    @Update("UPDATE tb_user_token SET token=#{token},user_id=#{user_id},time=#{time},is_valid=#{is_valid} WHERE id=#{id}")
    int update(UserToken userToken);

    @Select("SELECT * FROM tb_user_token WHERE id=#{id}")
    UserToken selectByPrimaryKey(@Param(value = "id")Integer id);

    @Select("SELECT * FROM tb_user_token limit #{offset},#{pageSize}")
    List<UserToken> selectPage(@Param(value = "offset")Integer offset,
                               @Param(value = "pageSize")Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_user_token")
    int count();

    @Delete("DELETE FROM tb_user_token WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id")Integer id);

    @Select("SELECT * FROM tb_user_token WHERE token=#{token}")
    UserToken selectByToken(String token);

    @Delete("DELETE  FROM tb_user_token WHERE user_id=#{user_id}")
    int deleteByUserId(Integer user_id);
}