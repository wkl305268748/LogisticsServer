package com.kenny.service.logistics.mapper.user;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.user.UserAuth;

@Mapper
public interface UserAuthMapper{

	@Insert("INSERT INTO tb_user_auth(user,auth_codes,role_id,time) VALUES(#{user},#{auth_codes},#{role_id},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(UserAuth userAuth);

	@Update("UPDATE tb_user_auth SET user=#{user},auth_codes=#{auth_codes},role_id=#{role_id},time=#{time} WHERE id=#{id}")
	int update(UserAuth userAuth);

	@Select("SELECT * FROM tb_user_auth WHERE id=#{id}")
	UserAuth selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_user_auth limit #{offset},#{pageSize}")
	List<UserAuth> selectPage(@Param(value = "offset") Integer offset,
                              @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_user_auth")
	int count();

	@Delete("DELETE FROM tb_user_auth WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
