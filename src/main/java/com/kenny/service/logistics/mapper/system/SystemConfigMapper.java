package com.kenny.service.logistics.mapper.system;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.system.SystemConfig;

@Mapper
public interface SystemConfigMapper{

	@Insert("INSERT INTO tb_system_config(name,code,value) VALUES(#{name},#{code},#{value})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(SystemConfig systemConfig);

	@Update("UPDATE tb_system_config SET name=#{name},code=#{code},value=#{value} WHERE id=#{id}")
	int update(SystemConfig systemConfig);

	@Select("SELECT * FROM tb_system_config WHERE id=#{id}")
	SystemConfig selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_system_config limit #{offset},#{pageSize}")
	List<SystemConfig> selectPage(@Param(value = "offset") Integer offset,
                                  @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_system_config")
	int count();

	@Delete("DELETE FROM tb_system_config WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_system_config WHERE code=#{code}")
	SystemConfig selectByCode(@Param(value = "code") String code);
}
