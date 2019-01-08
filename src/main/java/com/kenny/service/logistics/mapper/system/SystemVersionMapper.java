package com.kenny.service.logistics.mapper.system;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.po.system.SystemVersion;

@Mapper
public interface SystemVersionMapper{

	@Insert("INSERT INTO tb_system_version(type,version,version_number,changelog,url,time) VALUES(#{type},#{version},#{version_number},#{changelog},#{url},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(SystemVersion systemVersion);

	@Update("UPDATE tb_system_version SET type=#{type},version=#{version},version_number=#{version_number},changelog=#{changelog},url=#{url},time=#{time} WHERE id=#{id}")
	int update(SystemVersion systemVersion);

	@Select("SELECT * FROM tb_system_version WHERE id=#{id}")
	SystemVersion selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_system_version WHERE type=#{type} ORDER BY version_number DESC,time DESC limit 0,1")
	SystemVersion selectTopByType(@Param(value = "type") String type);

	@Select("SELECT * FROM tb_system_version limit #{offset},#{pageSize}")
	List<SystemVersion> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_system_version")
	int count();

	@Delete("DELETE FROM tb_system_version WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
