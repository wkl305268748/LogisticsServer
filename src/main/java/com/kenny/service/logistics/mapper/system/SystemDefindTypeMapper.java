package com.kenny.service.logistics.mapper.system;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.system.SystemDefindType;

@Mapper
public interface SystemDefindTypeMapper{

	@Insert("INSERT INTO tb_system_defind_type(name,code) VALUES(#{name},#{code})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(SystemDefindType systemDefindType);

	@Update("UPDATE tb_system_defind_type SET name=#{name},code=#{code} WHERE id=#{id}")
	int update(SystemDefindType systemDefindType);

	@Select("SELECT * FROM tb_system_defind_type WHERE id=#{id}")
	SystemDefindType selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_system_defind_type limit #{offset},#{pageSize}")
	List<SystemDefindType> selectPage(@Param(value = "offset") Integer offset,
                                      @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_system_defind_type")
	int count();

	@Delete("DELETE FROM tb_system_defind_type WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
