package com.kenny.service.logistics.mapper.fleet;

import com.kenny.service.logistics.model.fleet.License;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface LicenseMapper{

	@Insert("INSERT INTO tb_license(number,type,pass_time,valid_date,unvalide_date,files,remark,time) VALUES(#{number},#{type},#{pass_time},#{valid_date},#{unvalide_date},#{files},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(License license);

	@Update("UPDATE tb_license SET number=#{number},type=#{type},pass_time=#{pass_time},valid_date=#{valid_date},unvalide_date=#{unvalide_date},files=#{files},remark=#{remark},time=#{time} WHERE id=#{id}")
	int update(License license);

	@Select("SELECT * FROM tb_license WHERE id=#{id}")
	License selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_license limit #{offset},#{pageSize}")
	List<License> selectPage(@Param(value = "offset") Integer offset,
                             @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_license")
	int count();

	@Delete("DELETE FROM tb_license WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
