package com.kenny.service.logistics.mapper.user;

import com.kenny.service.logistics.model.user.UserInfo;
import org.apache.ibatis.annotations.*;

/**
 * Created by madengfeng on 2016/11/8.
 */
@Mapper
public interface UserInfoMapper {
    @Insert("INSERT INTO tb_user_info(nickname,sex,img,user_id,birthday) VALUES (#{nickname},#{sex},#{img},#{user_id},#{birthday})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserInfo userInfo);

    @Select("SELECT * FROM tb_user_info WHERE user_id=#{user_id}")
    UserInfo selectByUserId(@Param(value = "user_id") Integer user_id);

    @Update("UPDATE tb_user_info SET nickname=#{nickname},sex=#{sex},img=#{img},birthday=#{birthday} WHERE user_id=#{user_id}")
    int update(UserInfo userInfo);

    @Delete("DELETE FROM tb_user_info WHERE user_id=#{user_id}")
    int deleteByUserId(@Param(value = "user_id") Integer user_id);
}
