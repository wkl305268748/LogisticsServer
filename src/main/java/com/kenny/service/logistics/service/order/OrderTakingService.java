package com.kenny.service.logistics.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.order.OrderTaking;
import com.kenny.service.logistics.mapper.order.OrderTakingMapper;

@Service
public class OrderTakingService{
	@Autowired
	private OrderTakingMapper orderTakingMapper;

	public OrderTaking insert(Integer fk_order_customer_id,Integer fk_car_id,Integer fk_driver_id,Integer recive,Integer pay,String status,Date time){
		OrderTaking orderTaking = new OrderTaking();
		orderTaking.setFk_order_customer_id(fk_order_customer_id);
		orderTaking.setFk_car_id(fk_car_id);
		orderTaking.setFk_driver_id(fk_driver_id);
		orderTaking.setRecive(recive);
		orderTaking.setPay(pay);
		orderTaking.setStatus(status);
		orderTaking.setTime(time);
		orderTakingMapper.insert(orderTaking);
		return orderTaking;
	}

	public OrderTaking update(Integer id,Integer fk_order_customer_id,Integer fk_car_id,Integer fk_driver_id,Integer recive,Integer pay,String status,Date time) throws ErrorCodeException{
		OrderTaking orderTaking = orderTakingMapper.selectByPrimaryKey(id);
		if(orderTaking == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		orderTaking.setFk_order_customer_id(fk_order_customer_id);
		orderTaking.setFk_car_id(fk_car_id);
		orderTaking.setFk_driver_id(fk_driver_id);
		orderTaking.setRecive(recive);
		orderTaking.setPay(pay);
		orderTaking.setStatus(status);
		orderTaking.setTime(time);
		orderTakingMapper.update(orderTaking);
		return orderTaking;
	}

	public OrderTaking selectByPrimaryKey(Integer id) throws ErrorCodeException{
		OrderTaking orderTaking = orderTakingMapper.selectByPrimaryKey(id);
		if(orderTaking == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return orderTaking;
	}

	public PageResponse<OrderTaking> selectPage(Integer offset,Integer pageSize){
		PageResponse<OrderTaking> response = new PageResponse();
		response.setItem(orderTakingMapper.selectPage(offset,pageSize));
		response.setTotal(orderTakingMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return orderTakingMapper.deleteByPrimaryKey(id);
	}

}
