package com.kenny.service.logistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.Goods;
import com.kenny.service.logistics.mapper.GoodsMapper;

@Service
public class GoodsService{
	@Autowired
	private GoodsMapper goodsMapper;

	public Goods insert(String name,String size,Integer weight,String remark){
		Goods goods = new Goods();
		goods.setName(name);
		goods.setSize(size);
		goods.setWeight(weight);
		goods.setRemark(remark);
		goodsMapper.insert(goods);
		return goods;
	}

	public Goods update(Integer id,String name,String size,Integer weight,String remark) throws ErrorCodeException{
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		if(goods == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		goods.setName(name);
		goods.setSize(size);
		goods.setWeight(weight);
		goods.setRemark(remark);
		goodsMapper.update(goods);
		return goods;
	}

	public Goods selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Goods goods = goodsMapper.selectByPrimaryKey(id);
		if(goods == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return goods;
	}

	public PageResponse<Goods> selectPage(Integer offset,Integer pageSize){
		PageResponse<Goods> response = new PageResponse();
		response.setItem(goodsMapper.selectPage(offset,pageSize));
		response.setTotal(goodsMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return goodsMapper.deleteByPrimaryKey(id);
	}

}
