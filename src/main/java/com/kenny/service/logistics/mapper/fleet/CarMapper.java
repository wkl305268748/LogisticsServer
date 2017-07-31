package com.kenny.service.logistics.mapper.fleet;

import com.kenny.service.logistics.model.fleet.Car;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CarMapper{

	@Insert("INSERT INTO tb_car(plate,type,resource,two_plate,driver_phone,driver_name,energy,length,weight,vin,brand,engine,axle,wheelbase,tire,factory_time,buy_time,buy_price,limited_time,tow_maintain_time,insurance_policy,insurance_company,insurance_time,front_img,tail_img,remark,time,visible) VALUES(#{plate},#{type},#{resource},#{two_plate},#{driver_phone},#{driver_name},#{energy},#{length},#{weight},#{vin},#{brand},#{engine},#{axle},#{wheelbase},#{tire},#{factory_time},#{buy_time},#{buy_price},#{limited_time},#{tow_maintain_time},#{insurance_policy},#{insurance_company},#{insurance_time},#{front_img},#{tail_img},#{remark},#{time},#{visible})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Car car);

	@Update("UPDATE tb_car SET plate=#{plate},type=#{type},resource=#{resource},two_plate=#{two_plate},driver_phone=#{driver_phone},driver_name=#{driver_name},energy=#{energy},length=#{length},weight=#{weight},vin=#{vin},brand=#{brand},engine=#{engine},axle=#{axle},wheelbase=#{wheelbase},tire=#{tire},factory_time=#{factory_time},buy_time=#{buy_time},buy_price=#{buy_price},limited_time=#{limited_time},tow_maintain_time=#{tow_maintain_time},insurance_policy=#{insurance_policy},insurance_company=#{insurance_company},insurance_time=#{insurance_time},front_img=#{front_img},tail_img=#{tail_img},remark=#{remark},time=#{time},visible=#{visible} WHERE id=#{id}")
	int update(Car car);

	@Select("SELECT * FROM tb_car WHERE id=#{id}")
	Car selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_car WHERE visible = 1 ORDER BY time DESC limit #{offset},#{pageSize}")
	List<Car> selectPage(@Param(value = "offset") Integer offset,
                         @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_car WHERE visible = 1")
	int count();

	@Delete("DELETE FROM tb_car WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
