package com.kenny.service.logistics.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.po.user.UserMoney;
import com.kenny.service.logistics.mapper.user.UserMoneyMapper;

@Service
public class UserMoneyService{
	@Autowired
	private UserMoneyMapper userMoneyMapper;

	public UserMoney insert(Integer fk_user_id,Float money,String type,String remark){
		UserMoney userMoney = new UserMoney();
		userMoney.setFk_user_id(fk_user_id);
		userMoney.setMoney(money);
		userMoney.setType(type);
		userMoney.setRemark(remark);
		userMoney.setTime(new Date());
		userMoneyMapper.insert(userMoney);
		return userMoney;
	}

	public UserMoney update(Integer id,Integer fk_user_id,Float money,String type,String remark) throws ErrorCodeException{
		UserMoney userMoney = userMoneyMapper.selectByPrimaryKey(id);
		if(userMoney == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		userMoney.setFk_user_id(fk_user_id);
		userMoney.setMoney(money);
		userMoney.setType(type);
		userMoney.setRemark(remark);
		userMoneyMapper.update(userMoney);
		return userMoney;
	}

	public UserMoney selectByPrimaryKey(Integer id) throws ErrorCodeException{
		UserMoney userMoney = userMoneyMapper.selectByPrimaryKey(id);
		if(userMoney == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return userMoney;
	}

	public PageResponse<UserMoney> selectPage(Integer offset,Integer pageSize){
		PageResponse<UserMoney> response = new PageResponse();
		response.setItem(userMoneyMapper.selectPage(offset,pageSize));
		response.setTotal(userMoneyMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return userMoneyMapper.deleteByPrimaryKey(id);
	}

}
