package com.kenny.service.logistics.mapper.system;

import org.apache.ibatis.annotations.*;

import java.util.List;

import com.kenny.service.logistics.model.po.system.SystemDefind;

@Mapper
public interface SystemDefindMapper {

    @Insert("INSERT INTO tb_system_defind(name,type_code) VALUES(#{name},#{type_code})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SystemDefind systemDefind);

    @Update("UPDATE tb_system_defind SET name=#{name},type_code=#{type_code} WHERE id=#{id}")
    int update(SystemDefind systemDefind);

    @Select("SELECT * FROM tb_system_defind WHERE id=#{id}")
    SystemDefind selectByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_system_defind limit #{offset},#{pageSize}")
    List<SystemDefind> selectPage(@Param(value = "offset") Integer offset,
                                  @Param(value = "pageSize") Integer pageSize);

    @Select("SELECT COUNT(*) FROM tb_system_defind")
    int count();

    @Delete("DELETE FROM tb_system_defind WHERE id=#{id}")
    int deleteByPrimaryKey(@Param(value = "id") Integer id);

    @Select("SELECT * FROM tb_system_defind WHERE type_code=#{type_code} limit #{offset},#{pageSize}")
    List<SystemDefind> selectPageByType(@Param(value = "offset") Integer offset,
                                        @Param(value = "pageSize") Integer pageSize,
                                        @Param(value = "type_code") String type_code);

    @Select("SELECT COUNT(*) FROM tb_system_defind WHERE type_code=#{type_code}")
    int countByType(@Param(value = "type_code") String type_code);

}
