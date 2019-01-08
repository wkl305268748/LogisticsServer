package com.kenny.service.logistics.mapper.user;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.po.user.UserMoney;

@Mapper
public interface UserMoneyMapper{

	@Insert("INSERT INTO tb_user_money(fk_user_id,money,type,remark,time) VALUES(#{fk_user_id},#{money},#{type},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(UserMoney userMoney);

	@Update("UPDATE tb_user_money SET fk_user_id=#{fk_user_id},money=#{money},type=#{type},remark=#{remark},time=#{time} WHERE id=#{id}")
	int update(UserMoney userMoney);

	@Select("SELECT * FROM tb_user_money WHERE id=#{id}")
	UserMoney selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_user_money limit #{offset},#{pageSize}")
	List<UserMoney> selectPage(@Param(value = "offset") Integer offset,
                               @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_user_money")
	int count();

	@Delete("DELETE FROM tb_user_money WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
