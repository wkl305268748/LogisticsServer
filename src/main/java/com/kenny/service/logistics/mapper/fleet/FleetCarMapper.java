package com.kenny.service.logistics.mapper.fleet;

import com.kenny.service.logistics.model.po.fleet.FleetCar;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface FleetCarMapper {

	@Insert("INSERT INTO tb_fleet_car(plate,type,resource,two_plate,driver_phone,driver_name,energy,length,weight,vin,brand,engine,axle,wheelbase,tire,factory_time,buy_time,buy_price,limited_time,tow_maintain_time,insurance_policy,insurance_company,insurance_time,front_img,tail_img,remark,time,belong_user_id,visible) VALUES(#{plate},#{type},#{resource},#{two_plate},#{driver_phone},#{driver_name},#{energy},#{length},#{weight},#{vin},#{brand},#{engine},#{axle},#{wheelbase},#{tire},#{factory_time},#{buy_time},#{buy_price},#{limited_time},#{tow_maintain_time},#{insurance_policy},#{insurance_company},#{insurance_time},#{front_img},#{tail_img},#{remark},#{time},#{belong_user_id},#{visible})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(FleetCar fleetCar);

	@Update("UPDATE tb_fleet_car SET plate=#{plate},type=#{type},resource=#{resource},two_plate=#{two_plate},driver_phone=#{driver_phone},driver_name=#{driver_name},energy=#{energy},length=#{length},weight=#{weight},vin=#{vin},brand=#{brand},engine=#{engine},axle=#{axle},wheelbase=#{wheelbase},tire=#{tire},factory_time=#{factory_time},buy_time=#{buy_time},buy_price=#{buy_price},limited_time=#{limited_time},tow_maintain_time=#{tow_maintain_time},insurance_policy=#{insurance_policy},insurance_company=#{insurance_company},insurance_time=#{insurance_time},front_img=#{front_img},tail_img=#{tail_img},remark=#{remark},time=#{time},belong_user_id=#{belong_user_id},visible=#{visible} WHERE id=#{id}")
	int update(FleetCar fleetCar);

	@Select("SELECT * FROM tb_fleet_car WHERE id=#{id}")
    FleetCar selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_fleet_car WHERE visible = 1 ORDER BY time DESC limit #{offset},#{pageSize}")
	List<FleetCar> selectPage(@Param(value = "offset") Integer offset,
                              @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_fleet_car WHERE visible = 1")
	int count();

	@Delete("DELETE FROM tb_fleet_car WHERE id = #{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	//-------------额外----------
	@Select("SELECT * FROM tb_fleet_car WHERE visible = 1 AND belong_user_id = #{belong_user_id} ORDER BY time DESC limit #{offset},#{pageSize}")
	List<FleetCar> selectPageByBelongUser(@Param(value = "offset") Integer offset,
										  @Param(value = "pageSize") Integer pageSize,
										  @Param(value = "belong_user_id") Integer belong_user_id);

	@Select("SELECT COUNT(*) FROM tb_fleet_car WHERE visible = 1 AND belong_user_id = #{belong_user_id}")
	int countByBelongUser(@Param(value = "belong_user_id") Integer belong_user_id);
}
