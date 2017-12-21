package com.kenny.service.logistics.mapper.fleet;

import com.kenny.service.logistics.model.fleet.FleetDriver;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FleetDriverMapper {

    @Insert("INSERT INTO tb_fleet_driver(name,sex,phone,fk_user_id,is_sms,idcard,email,hometown,remark,time,belong_user_id,bank_number,bank_addr,visible) VALUES(#{name},#{sex},#{phone},#{fk_user_id},#{is_sms},#{idcard},#{email},#{hometown},#{remark},#{time},#{belong_user_id},#{bank_number},#{bank_addr},#{visible})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FleetDriver fleetDriver);

    @Update("UPDATE tb_fleet_driver SET name=#{name},sex=#{sex},phone=#{phone},fk_user_id=#{fk_user_id},is_sms=#{is_sms},idcard=#{idcard},email=#{email},hometown=#{hometown},remark=#{remark},time=#{time},belong_user_id=#{belong_user_id},bank_number=#{bank_number},bank_addr=#{bank_addr},visible=#{visible} WHERE id=#{id}")
    int update(FleetDriver fleetDriver);

    @Select("SELECT * FROM tb_fleet_driver WHERE id=#{id}")
    FleetDriver selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_fleet_driver WHERE fk_user_id=#{fk_user_id}")
    FleetDriver selectByUserId(@Param(value = "fk_user_id") Integer fk_user_id);

    @Select("SELECT * FROM tb_fleet_driver WHERE visible = 1 ORDER BY time DESC limit #{offset},#{pageSize}")
    List<FleetDriver> selectPage(@Param(value = "offset") Integer offset,
                                 @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_fleet_driver WHERE visible = 1 ORDER BY time DESC")
    int count();

    @Delete("DELETE FROM tb_fleet_driver WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);


    @Select("SELECT * FROM tb_fleet_driver WHERE visible = 1 AND belong_user_id = #{belong_user_id} ORDER BY time DESC limit #{offset},#{pageSize}")
    List<FleetDriver> selectPageBelongUser(@Param(value = "offset") Integer offset,
                                           @Param(value = "pageSize") Integer pageSize,
                                           @Param(value = "belong_user_id") Integer belong_user_id);

    @Select("SELECT COUNT(*) FROM tb_fleet_driver WHERE visible = 1 AND belong_user_id = #{belong_user_id} ORDER BY time DESC")
    int countByBelongUser(@Param(value = "belong_user_id") Integer belong_user_id);

}
