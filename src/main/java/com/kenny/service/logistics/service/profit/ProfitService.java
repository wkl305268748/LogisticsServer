package com.kenny.service.logistics.service.profit;

import com.kenny.service.logistics.mapper.profit.ProfitStatusMapper;
import com.kenny.service.logistics.model.profit.ProfitSet;
import com.kenny.service.logistics.model.profit.ProfitStatus;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.profit.Profit;
import com.kenny.service.logistics.mapper.profit.ProfitMapper;

@Service
public class ProfitService{
	@Autowired
	private ProfitMapper profitMapper;
	@Autowired
	private ProfitStatusMapper profitStatusMapper;

	public Profit insert(Integer fk_order_customer_id,String order_number,Integer recive,Integer pay){
		Profit profit = new Profit();
		profit.setFk_order_customer_id(fk_order_customer_id);
		profit.setOrder_number(order_number);
		profit.setRecive(recive);
		profit.setPay(pay);
		profit.setRecive_now(0);
		profit.setPay_now(0);
		profit.setIs_recive(false);
		profit.setIs_pay(false);
		profit.setProfit(recive - pay);
		profit.setTime(new Date());
		profitMapper.insert(profit);
		return profit;
	}

	public Profit update(Integer id,Integer fk_order_customer_id,String order_number,Integer recive,Integer pay,Integer recive_now,Integer pay_now,Boolean is_recive,Boolean is_pay) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		profit.setFk_order_customer_id(fk_order_customer_id);
		profit.setOrder_number(order_number);
		profit.setRecive(recive);
		profit.setPay(pay);
		profit.setRecive_now(recive_now);
		profit.setPay_now(pay_now);
		profit.setIs_recive(is_recive);
		profit.setIs_pay(is_pay);
		profitMapper.update(profit);
		return profit;
	}

	public Profit pay(Integer id,Integer pay) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}

		int pay_now = profit.getPay_now() + pay;
		if(pay_now >= profit.getPay()) {
			profit.setPay_now(profit.getPay());
			profit.setIs_pay(true);
		}else{
			profit.setPay_now(pay_now);
			profit.setIs_pay(false);
		}
		profitMapper.update(profit);

		//增加记录
		ProfitStatus profitStatus = new ProfitStatus();
		profitStatus.setFk_profit_id(id);
		profitStatus.setType("pay");
		profitStatus.setValue(pay);
		profitStatus.setTime(new Date());
		return profit;
	}

	public Profit recive(Integer id,Integer recive) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}

		int recive_now = profit.getRecive_now() + recive;
		if(recive_now >= profit.getRecive()) {
			profit.setRecive_now(profit.getRecive());
			profit.setIs_recive(true);
		}else{
			profit.setRecive_now(recive_now);
			profit.setIs_recive(false);
		}
		profitMapper.update(profit);

		//增加记录
		ProfitStatus profitStatus = new ProfitStatus();
		profitStatus.setFk_profit_id(id);
		profitStatus.setType("recive");
		profitStatus.setValue(recive);
		profitStatus.setTime(new Date());
		return profit;
	}

	public Profit selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return profit;
	}

	public PageResponse<Profit> selectPage(Integer offset,Integer pageSize){
		PageResponse<Profit> response = new PageResponse();
		response.setItem(profitMapper.selectPage(offset,pageSize));
		response.setTotal(profitMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return profitMapper.deleteByPrimaryKey(id);
	}

	/*
	public PageResponse<ProfitSet> selectPageByOrderCustomer(Integer offset, Integer pageSize, Integer order_customer_id){
		PageResponse<ProfitSet> response = new PageResponse();
		List<ProfitSet> profitSets = new ArrayList<>();
		List<Profit> profits = profitMapper.selectPageByOrderCustomer(offset,pageSize,order_customer_id);
		for(Profit profit : profits){
			ProfitSet profitSet = new ProfitSet();
			profitSet.setProfit(profit);
			profitSet.setProfitStatuses(profitStatusMapper.selectPageByProfitId(0,100,profit.getId()));
		}
		response.setItem(profitSets);
		response.setTotal(profitMapper.countByOrderCustomer(order_customer_id));
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}*/

}
