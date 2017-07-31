package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.mapper.system.SystemConfigMapper;
import com.kenny.service.logistics.model.system.SystemConfig;
import com.kenny.service.logistics.service.system.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.order.OrderContract;
import com.kenny.service.logistics.mapper.order.OrderContractMapper;

@Service
public class OrderContractService{
	@Autowired
	private OrderContractMapper orderContractMapper;
	@Autowired
	private SystemConfigService systemConfigService;

	public OrderContract insert(Integer fk_order_customer_id,String order_number,String contract_number,String aname,String bname,String bbank_name,String bbank_number,String bbank){
		OrderContract orderContract = new OrderContract();
		orderContract.setFk_order_customer_id(fk_order_customer_id);
		orderContract.setOrder_number(order_number);
		orderContract.setContract_number(contract_number);
		orderContract.setAname(aname);
		orderContract.setBname(bname);
		orderContract.setBbank_name(bbank_name);
		orderContract.setBbank_number(bbank_number);
		orderContract.setBbank(bbank);
		orderContract.setTime(new Date());
		orderContractMapper.insert(orderContract);
		return orderContract;
	}

	/**
	 * 生成合同
	 * @param fk_order_customer_id
	 * @param order_number
	 * @return
	 */
	public OrderContract create(Integer fk_order_customer_id,String order_number,String aname){
		OrderContract orderContract = new OrderContract();
		orderContract.setFk_order_customer_id(fk_order_customer_id);
		orderContract.setOrder_number(order_number);
		orderContract.setContract_number("HT-"+order_number);
		orderContract.setAname(aname);
		orderContract.setBname(systemConfigService.getValueByCode("company"));
		orderContract.setBbank_name(systemConfigService.getValueByCode("company_bank_name"));
		orderContract.setBbank_number(systemConfigService.getValueByCode("company_bank_number"));
		orderContract.setBbank(systemConfigService.getValueByCode("company_bank"));
		orderContract.setTime(new Date());
		orderContractMapper.insert(orderContract);
		return orderContract;
	}

	public OrderContract update(Integer id,String aname,String bname,String bbank_name,String bbank_number,String bbank) throws ErrorCodeException{
		OrderContract orderContract = orderContractMapper.selectByPrimaryKey(id);
		if(orderContract == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		orderContract.setAname(aname);
		orderContract.setBname(bname);
		orderContract.setBbank_name(bbank_name);
		orderContract.setBbank_number(bbank_number);
		orderContract.setBbank(bbank);
		orderContractMapper.update(orderContract);
		return orderContract;
	}

	public OrderContract selectByPrimaryKey(Integer id) throws ErrorCodeException{
		OrderContract orderContract = orderContractMapper.selectByPrimaryKey(id);
		if(orderContract == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return orderContract;
	}

	public PageResponse<OrderContract> selectPage(Integer offset,Integer pageSize){
		PageResponse<OrderContract> response = new PageResponse();
		response.setItem(orderContractMapper.selectPage(offset,pageSize));
		response.setTotal(orderContractMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return orderContractMapper.deleteByPrimaryKey(id);
	}

}
