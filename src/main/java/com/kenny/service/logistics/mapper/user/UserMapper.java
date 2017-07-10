package com.kenny.service.logistics.mapper.user;

import com.kenny.service.logistics.model.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO tb_user(username,password,regtime,is_disable,phone) VALUES(#{username},#{password},#{regtime},#{is_disable},#{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE tb_user SET username=#{username},password=#{password},regtime=#{regtime},is_disable=#{is_disable},phone=#{phone} WHERE id=#{id}")
    int update(User user);

    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    int delete(User user);

    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User selectByPrimaryKey(@Param(value = "id") Integer id);

     @Select("SELECT * FROM tb_user WHERE username=#{username}")
    User selectUserByUserName(@Param(value = "username") String username);

    @Select("SELECT * FROM tb_user WHERE phone=#{phone}")
    User selectUserByPhone(@Param(value = "phone") String phone);

    @Select("SELECT * FROM tb_user limit #{offset},#{pageSize}")
    List<User> selectPage(@Param(value = "offset") Integer offset, @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_user")
    int count();
}