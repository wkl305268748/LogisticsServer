package com.kenny.service.logistics.service.profit;

import com.kenny.service.logistics.json.response.PayCardResponse;
import com.kenny.service.logistics.mapper.profit.ProfitStatusMapper;
import com.kenny.service.logistics.model.order.OrderSet;
import com.kenny.service.logistics.model.profit.ProfitSet;
import com.kenny.service.logistics.model.profit.ProfitStatus;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.pay.PayUtils;
import com.kenny.service.logistics.service.order.OrderService;
import com.kenny.service.logistics.service.user.UserCompanyService;
import com.kenny.service.logistics.service.util.PayService;
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
	@Autowired
	private UserCompanyService userCompanyService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private PayService payService;

	public Profit insert(Integer fk_order_id,String order_number,Float recive,Float pay, Integer belong_user_id){
		Profit profit = new Profit();
		profit.setFk_order_id(fk_order_id);
		profit.setOrder_number(order_number);
		profit.setRecive(recive);
		profit.setPay(pay);
		profit.setRecive_now(0f);
		profit.setPay_now(0f);
		profit.setIs_recive(false);
		profit.setIs_pay(false);
		profit.setProfit(recive - pay);
		profit.setTime(new Date());
		profit.setBelong_user_id(belong_user_id);
		profitMapper.insert(profit);
		return profit;
	}

	public Profit update(Integer id,Integer fk_order_id,String order_number,Float recive,Float pay,Float recive_now,Float pay_now,Boolean is_recive,Boolean is_pay) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		profit.setFk_order_id(fk_order_id);
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

	public Profit pay(int id,Float pay) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}

		Float pay_now = profit.getPay_now() + pay;
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

	public Profit payToCard(String token,int id,String name,String card,String bank,String phone,String idcard,Float pay) throws ErrorCodeException{
		//校验余额是否充足
		userCompanyService.checkMoney(token,pay);

		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		//开始转账到指定银行
		payService.payToBankCard(profit.getOrder_number(),pay.toString(),name,card,bank,phone,idcard);
		//修改存款
		userCompanyService.reduceMoney(token,pay,"to_card_"+profit.getOrder_number());
		//修改记录
		profit.setIs_pay(true);
		profit.setPay_now(pay);
		profitMapper.update(profit);

		//增加记录
		ProfitStatus profitStatus = new ProfitStatus();
		profitStatus.setFk_profit_id(id);
		profitStatus.setType("pay");
		profitStatus.setValue(pay);
		profitStatus.setTime(new Date());
		return profit;
	}

	public PayCardResponse getCard(String token, int id) throws ErrorCodeException{
		PayCardResponse payCardResponse = new PayCardResponse();

		//查询余额
		UserSet userSet = userCompanyService.getUserByTokenEx(token);
		if(userSet == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		payCardResponse.setMoney(userSet.getUserInfo().getMoney());

		//查询订单
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		payCardResponse.setPay(profit.getPay());

		OrderSet orderSet = orderService.selectByPrimaryKeyEx(profit.getFk_order_id());
		if(orderSet == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}

		//司机信息
		payCardResponse.setBank(orderSet.getFleetDriver().getBank_addr());
		payCardResponse.setCard(orderSet.getFleetDriver().getBank_number());
		payCardResponse.setIdcard(orderSet.getFleetDriver().getIdcard());
		payCardResponse.setName(orderSet.getFleetDriver().getName());
		payCardResponse.setPhone(orderSet.getFleetDriver().getPhone());

		return payCardResponse;
	}

	public Profit recive(Integer id,Float recive) throws ErrorCodeException{
		Profit profit = profitMapper.selectByPrimaryKey(id);
		if(profit == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}

		Float recive_now = profit.getRecive_now() + recive;
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

	public PageResponse<Profit> selectPageByBelongUser(Integer offset,Integer pageSize,Integer belong_user_id){
		PageResponse<Profit> response = new PageResponse();
		response.setItem(profitMapper.selectPageByBelongUser(offset,pageSize,belong_user_id));
		response.setTotal(profitMapper.countByBelongUser(belong_user_id));
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
