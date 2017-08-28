package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.mapper.order.OrderCustomerMapper;
import com.kenny.service.logistics.mapper.order.OrderStatusMapper;
import com.kenny.service.logistics.mapper.order.OrderTakingMapper;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.order.OrderSign;
import com.kenny.service.logistics.mapper.order.OrderSignMapper;

@Service
public class OrderSignService{
	@Autowired
	private OrderSignMapper orderSignMapper;

	public OrderSign insert(Integer fk_order_id,
							String order_img){
		OrderSign orderSign = new OrderSign();
		orderSign.setFk_order_id(fk_order_id);
		orderSign.setOrder_img(order_img);
		orderSign.setTime(new Date());
		orderSignMapper.insert(orderSign);

		return orderSign;
	}

	public OrderSign update(Integer id,
							String order_img) throws ErrorCodeException{
		OrderSign orderSign = orderSignMapper.selectByPrimaryKey(id);
		if(orderSign == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		orderSign.setOrder_img(order_img);
		orderSign.setTime(new Date());
		orderSignMapper.update(orderSign);
		return orderSign;
	}

	public OrderSign selectByPrimaryKey(Integer id) throws ErrorCodeException{
		OrderSign orderSign = orderSignMapper.selectByPrimaryKey(id);
		if(orderSign == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return orderSign;
	}

	public PageResponse<OrderSign> selectPage(Integer offset,Integer pageSize){
		PageResponse<OrderSign> response = new PageResponse();
		response.setItem(orderSignMapper.selectPage(offset,pageSize));
		response.setTotal(orderSignMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return orderSignMapper.deleteByPrimaryKey(id);
	}
}
