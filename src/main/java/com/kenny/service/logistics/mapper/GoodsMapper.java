package com.kenny.service.logistics.mapper;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.Goods;

@Mapper
public interface GoodsMapper{

	@Insert("INSERT INTO goods(name,size,weight,remark) VALUES(#{name},#{size},#{weight},#{remark})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(Goods goods);

	@Update("UPDATE goods SET name=#{name},size=#{size},weight=#{weight},remark=#{remark} WHERE id=#{id}")
	int update(Goods goods);

	@Select("SELECT * FROM goods WHERE id=#{id}")
	Goods selectByPrimaryKey(@Param(value = "id")Integer id);

	@Select("SELECT * FROM goods limit #{offset},#{pageSize}")
	List<Goods> selectPage(@Param(value = "offset")Integer offset,
	                       @Param(value = "pageSize")Integer pageSize);

	@Select("SELECT COUNT(*) FROM goods")
	int count();

	@Delete("DELETE FROM goods WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id")Integer id);

}
