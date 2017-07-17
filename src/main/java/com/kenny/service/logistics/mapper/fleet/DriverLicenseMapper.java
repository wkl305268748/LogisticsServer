package com.kenny.service.logistics.mapper.fleet;

import com.kenny.service.logistics.model.fleet.DriverLicense;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface DriverLicenseMapper{

	@Insert("INSERT INTO tb_driver_license(fk_driver_id,number,level,valid_time,unvalid_time,pass_time,work_license,ic_number,files,time) VALUES(#{fk_driver_id},#{number},#{level},#{valid_time},#{unvalid_time},#{pass_time},#{work_license},#{ic_number},#{files},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(DriverLicense driverLicense);

	@Update("UPDATE tb_driver_license SET fk_driver_id=#{fk_driver_id},number=#{number},level=#{level},valid_time=#{valid_time},unvalid_time=#{unvalid_time},pass_time=#{pass_time},work_license=#{work_license},ic_number=#{ic_number},files=#{files},time=#{time} WHERE id=#{id}")
	int update(DriverLicense driverLicense);

	@Select("SELECT * FROM tb_driver_license WHERE id=#{id}")
	DriverLicense selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_driver_license limit #{offset},#{pageSize}")
	List<DriverLicense> selectPage(@Param(value = "offset") Integer offset,
                                   @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_driver_license")
	int count();

	@Delete("DELETE FROM tb_driver_license WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
