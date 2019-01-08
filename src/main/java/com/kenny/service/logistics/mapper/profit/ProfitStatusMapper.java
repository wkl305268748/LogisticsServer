package com.kenny.service.logistics.mapper.profit;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.po.profit.ProfitStatus;

@Mapper
public interface ProfitStatusMapper{

	@Insert("INSERT INTO tb_profit_status(fk_profit_id,type,value,time) VALUES(#{fk_profit_id},#{type},#{value},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(ProfitStatus profitStatus);

	@Update("UPDATE tb_profit_status SET fk_profit_id=#{fk_profit_id},type=#{type},value=#{value},time=#{time} WHERE id=#{id}")
	int update(ProfitStatus profitStatus);

	@Select("SELECT * FROM tb_profit_status WHERE id=#{id}")
	ProfitStatus selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_profit_status limit #{offset},#{pageSize}")
	List<ProfitStatus> selectPage(@Param(value = "offset") Integer offset,
								  @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_profit_status")
	int count();

	@Delete("DELETE FROM tb_profit_status WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_profit_status WHERE fk_profit_id=#{fk_profit_id} limit #{offset},#{pageSize}")
	List<ProfitStatus> selectPageByProfitId(@Param(value = "offset") Integer offset,
								  			@Param(value = "pageSize") Integer pageSize,
											@Param(value = "fk_profit_id") Integer fk_profit_id);

	@Select("SELECT COUNT(*) FROM tb_profit_status WHERE fk_profit_id=#{fk_profit_id}")
	int countByProfitId(@Param(value = "fk_profit_id") Integer fk_profit_id);
}
