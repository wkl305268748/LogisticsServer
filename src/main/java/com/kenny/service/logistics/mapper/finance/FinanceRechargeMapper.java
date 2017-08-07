package com.kenny.service.logistics.mapper.finance;

import org.apache.ibatis.annotations.*;
import java.util.List;
import com.kenny.service.logistics.model.finance.FinanceRecharge;

@Mapper
public interface FinanceRechargeMapper{

	@Insert("INSERT INTO tb_finance_recharge(fk_user_id,company_out,bank_name,bank_number,bank,bank_addr,money,money_code,money_for,status,fk_admin_id,time) VALUES(#{fk_user_id},#{company_out},#{bank_name},#{bank_number},#{bank},#{bank_addr},#{money},#{money_code},#{money_for},#{status},#{fk_admin_id},#{time})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(FinanceRecharge financeRecharge);

	@Update("UPDATE tb_finance_recharge SET fk_user_id=#{fk_user_id},company_out=#{company_out},bank_name=#{bank_name},bank_number=#{bank_number},bank=#{bank},bank_addr=#{bank_addr},money=#{money},money_code=#{money_code},money_for=#{money_for},status=#{status},fk_admin_id=#{fk_admin_id},time=#{time} WHERE id=#{id}")
	int update(FinanceRecharge financeRecharge);

	@Select("SELECT * FROM tb_finance_recharge WHERE id=#{id}")
	FinanceRecharge selectByPrimaryKey(@Param(value = "id") Integer id);

	@Select("SELECT * FROM tb_finance_recharge limit #{offset},#{pageSize}")
	List<FinanceRecharge> selectPage(@Param(value = "offset") Integer offset,
                                     @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_finance_recharge")
	int count();

	@Select("SELECT * FROM tb_finance_recharge WHERE fk_user_id = #{fk_user_id} limit #{offset},#{pageSize}")
	List<FinanceRecharge> selectPageByUserId(@Param(value = "fk_user_id") Integer fk_user_id,
											 @Param(value = "offset") Integer offset,
									 		 @Param(value = "pageSize") Integer pageSize);

	@Select("SELECT COUNT(*) FROM tb_finance_recharge WHERE fk_user_id = #{fk_user_id}")
	int countByUserId(@Param(value = "fk_user_id") Integer fk_user_id);

	@Delete("DELETE FROM tb_finance_recharge WHERE id=#{id}")
	int deleteByPrimaryKey(@Param(value = "id") Integer id);

}
