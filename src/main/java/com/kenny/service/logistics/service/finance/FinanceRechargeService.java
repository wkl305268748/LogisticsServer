package com.kenny.service.logistics.service.finance;

import com.kenny.service.logistics.service.system.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.po.finance.FinanceRecharge;
import com.kenny.service.logistics.mapper.finance.FinanceRechargeMapper;

@Service
public class FinanceRechargeService{
	@Autowired
	private FinanceRechargeMapper financeRechargeMapper;
	@Autowired
	private SystemConfigService systemConfigService;

	//随机生成一个申请
	public FinanceRecharge create(int user_id,String company_out){
		FinanceRecharge financeRecharge = new FinanceRecharge();
		financeRecharge.setFk_user_id(user_id);
		financeRecharge.setCompany_out(company_out);

		financeRecharge.setBank_name(systemConfigService.getValueByCode(user_id,"company_bank_name"));
		financeRecharge.setBank_number(systemConfigService.getValueByCode(user_id,"company_bank_number"));
		financeRecharge.setBank(systemConfigService.getValueByCode(user_id,"company_bank"));
		financeRecharge.setBank_addr(systemConfigService.getValueByCode(user_id,"company_bank_addr"));
		financeRecharge.setMoney(0f);
		financeRecharge.setMoney_code(randomMoneyCode());
		financeRecharge.setMoney_for("账户充值");
		financeRecharge.setStatus("WAIT");
		financeRecharge.setFk_admin_id(-1);
		financeRecharge.setTime(new Date());
		return financeRecharge;
	}

	private String randomMoneyCode(){
		Random random = new Random(System.currentTimeMillis());
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMddHHmm");
		String number = "LY56" + myFmt.format(new Date());
		for (int i = 0; i < 5; i++)
			number = number + random.nextInt(9);
		return number;
	}

	public FinanceRecharge insert(Integer fk_user_id,String company_out,String bank_name,String bank_number,String bank,String bank_addr,Float money,String money_code,String money_for,String status,Integer fk_admin_id){
		FinanceRecharge financeRecharge = new FinanceRecharge();
		financeRecharge.setFk_user_id(fk_user_id);
		financeRecharge.setCompany_out(company_out);
		financeRecharge.setBank_name(bank_name);
		financeRecharge.setBank_number(bank_number);
		financeRecharge.setBank(bank);
		financeRecharge.setBank_addr(bank_addr);
		financeRecharge.setMoney(money);
		financeRecharge.setMoney_code(money_code);
		financeRecharge.setMoney_for(money_for);
		financeRecharge.setStatus(status);
		financeRecharge.setFk_admin_id(fk_admin_id);
		financeRecharge.setTime(new Date());
		financeRechargeMapper.insert(financeRecharge);
		return financeRecharge;
	}

	public FinanceRecharge update(Integer id,Integer fk_user_id,String company_out,String bank_name,String bank_number,String bank,String bank_addr,Float money,String money_code,String money_for,String status,Integer fk_admin_id,Date time) throws ErrorCodeException{
		FinanceRecharge financeRecharge = financeRechargeMapper.selectByPrimaryKey(id);
		if(financeRecharge == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		financeRecharge.setFk_user_id(fk_user_id);
		financeRecharge.setCompany_out(company_out);
		financeRecharge.setBank_name(bank_name);
		financeRecharge.setBank_number(bank_number);
		financeRecharge.setBank(bank);
		financeRecharge.setBank_addr(bank_addr);
		financeRecharge.setMoney(money);
		financeRecharge.setMoney_code(money_code);
		financeRecharge.setMoney_for(money_for);
		financeRecharge.setStatus(status);
		financeRecharge.setFk_admin_id(fk_admin_id);
		financeRecharge.setTime(time);
		financeRechargeMapper.update(financeRecharge);
		return financeRecharge;
	}

	public FinanceRecharge selectByPrimaryKey(Integer id) throws ErrorCodeException{
		FinanceRecharge financeRecharge = financeRechargeMapper.selectByPrimaryKey(id);
		if(financeRecharge == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return financeRecharge;
	}

	public PageResponse<FinanceRecharge> selectPage(Integer offset,Integer pageSize){
		PageResponse<FinanceRecharge> response = new PageResponse();
		response.setItem(financeRechargeMapper.selectPage(offset,pageSize));
		response.setTotal(financeRechargeMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public PageResponse<FinanceRecharge> selectPageByUserId(Integer user_id,Integer offset,Integer pageSize){
		PageResponse<FinanceRecharge> response = new PageResponse();
		response.setItem(financeRechargeMapper.selectPageByUserId(user_id,offset,pageSize));
		response.setTotal(financeRechargeMapper.countByUserId(user_id));
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return financeRechargeMapper.deleteByPrimaryKey(id);
	}

}
