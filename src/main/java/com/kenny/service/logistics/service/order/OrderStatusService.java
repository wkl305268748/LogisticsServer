package com.kenny.service.logistics.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.order.OrderStatus;
import com.kenny.service.logistics.mapper.order.OrderStatusMapper;

@Service
public class OrderStatusService{
	@Autowired
	private OrderStatusMapper orderStatusMapper;

	public OrderStatus insert(String order_number,String status,int fk_user_id){
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder_number(order_number);
		orderStatus.setStatus(status);
		orderStatus.setFk_user_id(fk_user_id);
		orderStatus.setTime(new Date());
		orderStatusMapper.insert(orderStatus);
		return orderStatus;
	}

	public OrderStatus selectByPrimaryKey(Integer id) throws ErrorCodeException{
		OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(id);
		if(orderStatus == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return orderStatus;
	}

	public List<OrderStatus> selectByOrderNumber(String order_number) throws ErrorCodeException{
		List<OrderStatus> orderStatus = orderStatusMapper.selectByOrderNumber(order_number);
		return orderStatus;
	}

	public PageResponse<OrderStatus> selectPage(Integer offset,Integer pageSize){
		PageResponse<OrderStatus> response = new PageResponse();
		response.setItem(orderStatusMapper.selectPage(offset,pageSize));
		response.setTotal(orderStatusMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return orderStatusMapper.deleteByPrimaryKey(id);
	}

}
