package com.kenny.service.logistics.service.order;

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

	public OrderSign insert(Integer fk_order_taking_id,Integer fk_order_customer_id,String order_img,Date time){
		OrderSign orderSign = new OrderSign();
		orderSign.setFk_order_taking_id(fk_order_taking_id);
		orderSign.setFk_order_customer_id(fk_order_customer_id);
		orderSign.setOrder_img(order_img);
		orderSign.setTime(time);
		orderSignMapper.insert(orderSign);
		return orderSign;
	}

	public OrderSign update(Integer id,Integer fk_order_taking_id,Integer fk_order_customer_id,String order_img,Date time) throws ErrorCodeException{
		OrderSign orderSign = orderSignMapper.selectByPrimaryKey(id);
		if(orderSign == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		orderSign.setFk_order_taking_id(fk_order_taking_id);
		orderSign.setFk_order_customer_id(fk_order_customer_id);
		orderSign.setOrder_img(order_img);
		orderSign.setTime(time);
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
