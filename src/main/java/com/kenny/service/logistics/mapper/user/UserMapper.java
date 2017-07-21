package com.kenny.service.logistics.mapper.user;

import com.kenny.service.logistics.model.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO tb_user(username,phone,password,type,regtime,is_disable,is_valid) VALUES(#{username},#{phone},#{password},#{type},#{regtime},#{is_disable},#{is_valid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE tb_user SET username=#{username},phone=#{phone},password=#{password},type=#{type},regtime=#{regtime},is_disable=#{is_disable},is_valid=#{is_valid} WHERE id=#{id}")
    int update(User user);

    @Select("SELECT * FROM tb_user WHERE id=#{id}")
    User selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_user limit #{offset},#{pageSize}")
    List<User> selectPage(@Param(value = "offset") Integer offset,
                          @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_user")
    int count();

    @Delete("DELETE FROM tb_user WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);


    @Select("SELECT * FROM tb_user WHERE type=#{type} limit #{offset},#{pageSize}")
    List<User> selectPageByType(@Param(value = "offset") Integer offset,
                                @Param(value = "pageSize") Integer pageSize,
                                @Param(value = "type") String type);

    @Select("SELECT COUNT(*) WHERE type=#{type} FROM tb_user")
    int countByType(@Param(value = "type") String type);

    @Select("SELECT * FROM tb_user WHERE username=#{username} AND type=#{type} AND is_valid = 1")
    User selectByUserName(@Param(value = "username") String username,@Param(value = "type") String type);

    @Select("SELECT * FROM tb_user WHERE phone=#{phone} AND type=#{type} AND is_valid = 1")
    User selectByPhone(@Param(value = "phone") String phone,@Param(value = "type") String type);

}