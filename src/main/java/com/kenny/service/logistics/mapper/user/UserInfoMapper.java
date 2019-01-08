package com.kenny.service.logistics.mapper.user;

import com.kenny.service.logistics.model.po.user.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by madengfeng on 2016/11/8.
 */
@Mapper
public interface UserInfoMapper {

    @Insert("INSERT INTO tb_user_info(user_id,nickname,sex,img,birthday,company,money) VALUES(#{user_id},#{nickname},#{sex},#{img},#{birthday},#{company},#{money})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserInfo userInfo);

    @Update("UPDATE tb_user_info SET user_id=#{user_id},nickname=#{nickname},sex=#{sex},img=#{img},birthday=#{birthday},company=#{company},money=#{money} WHERE id=#{id}")
    int update(UserInfo userInfo);

    @Select("SELECT * FROM tb_user_info WHERE id=#{id}")
    UserInfo selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_user_info limit #{offset},#{pageSize}")
    List<UserInfo> selectPage(@Param(value = "offset") Integer offset,
                              @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_user_info")
    int count();

    @Delete("DELETE FROM tb_user_info WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_user_info WHERE user_id=#{user_id}")
    UserInfo selectByUserId(@Param(value = "user_id") Integer user_id);

    @Delete("DELETE FROM tb_user_info WHERE user_id=#{user_id}")
    int deleteByUserId(@Param(value = "user_id") Integer user_id);
}
