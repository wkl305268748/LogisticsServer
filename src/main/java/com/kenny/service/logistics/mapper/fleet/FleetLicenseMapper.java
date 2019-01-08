package com.kenny.service.logistics.mapper.fleet;

import com.kenny.service.logistics.model.po.fleet.FleetLicense;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FleetLicenseMapper {

	@Insert("INSERT INTO tb_fleet_license(number,type,pass_time,valid_date,unvalide_date,files,fk_driver_id,fk_car_id,remark,time) VALUES(#{number},#{type},#{pass_time},#{valid_date},#{unvalide_date},#{files},#{fk_driver_id},#{fk_car_id},#{remark},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(FleetLicense fleetLicense);

	@Update("UPDATE tb_fleet_license SET number=#{number},type=#{type},pass_time=#{pass_time},valid_date=#{valid_date},unvalide_date=#{unvalide_date},files=#{files},remark=#{remark},time=#{time},fk_driver_id=#{fk_driver_id},fk_car_id=#{fk_car_id} WHERE id=#{id}")
	int update(FleetLicense fleetLicense);

	@Select("SELECT * FROM tb_fleet_license WHERE id=#{id}")
    FleetLicense selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_fleet_license limit #{offset},#{pageSize}")
	List<FleetLicense> selectPage(@Param(value = "offset") Integer offset,
                                  @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_fleet_license")
	int count();

	@Delete("DELETE FROM tb_fleet_license WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);


	@Select("SELECT * FROM tb_fleet_license WHERE fk_car_id=#{fk_car_id}")
	List<FleetLicense> selectByCar(@Param(value = "fk_car_id") Integer fk_car_id);

	@Select("SELECT * FROM tb_fleet_license WHERE fk_driver_id=#{fk_driver_id}")
	List<FleetLicense> selectByDriver(@Param(value = "fk_driver_id") Integer fk_car_id);
}
