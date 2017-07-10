package com.kenny.service.logistics.service;

import com.kenny.service.logistics.json.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.OrderCustomerGoods;
import com.kenny.service.logistics.mapper.OrderCustomerGoodsMapper;

@Service
public class OrderCustomerGoodsService{
	@Autowired
	private OrderCustomerGoodsMapper orderCustomerGoodsMapper;

	public OrderCustomerGoods insert(Integer fk_goods_id,Integer fk_order_customer_id){
		OrderCustomerGoods orderCustomerGoods = new OrderCustomerGoods();
		orderCustomerGoods.setFk_goods_id(fk_goods_id);
		orderCustomerGoods.setFk_order_customer_id(fk_order_customer_id);
		orderCustomerGoodsMapper.insert(orderCustomerGoods);
		return orderCustomerGoods;
	}

	public OrderCustomerGoods update(Integer id,Integer fk_goods_id,Integer fk_order_customer_id) throws ErrorCodeException{
		OrderCustomerGoods orderCustomerGoods = orderCustomerGoodsMapper.selectByPrimaryKey(id);
		if(orderCustomerGoods == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		orderCustomerGoods.setFk_goods_id(fk_goods_id);
		orderCustomerGoods.setFk_order_customer_id(fk_order_customer_id);
		orderCustomerGoodsMapper.update(orderCustomerGoods);
		return orderCustomerGoods;
	}

	public OrderCustomerGoods selectByPrimaryKey(Integer id) throws ErrorCodeException{
		OrderCustomerGoods orderCustomerGoods = orderCustomerGoodsMapper.selectByPrimaryKey(id);
		if(orderCustomerGoods == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return orderCustomerGoods;
	}

	public PageResponse<OrderCustomerGoods> selectPage(Integer offset, Integer pageSize){
		PageResponse<OrderCustomerGoods> response = new PageResponse();
		response.setItem(orderCustomerGoodsMapper.selectPage(offset,pageSize));
		response.setTotal(orderCustomerGoodsMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return orderCustomerGoodsMapper.deleteByPrimaryKey(id);
	}

}
