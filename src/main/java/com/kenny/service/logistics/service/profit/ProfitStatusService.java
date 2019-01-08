package com.kenny.service.logistics.service.profit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.po.profit.ProfitStatus;
import com.kenny.service.logistics.mapper.profit.ProfitStatusMapper;

@Service
public class ProfitStatusService{
	@Autowired
	private ProfitStatusMapper profitStatusMapper;

	public ProfitStatus insert(Integer fk_profit_id,String type,Float value){
		ProfitStatus profitStatus = new ProfitStatus();
		profitStatus.setFk_profit_id(fk_profit_id);
		profitStatus.setType(type);
		profitStatus.setValue(value);
		profitStatus.setTime(new Date());
		profitStatusMapper.insert(profitStatus);
		return profitStatus;
	}

	public ProfitStatus update(Integer id,Integer fk_profit_id,String type,Float value,Date time) throws ErrorCodeException{
		ProfitStatus profitStatus = profitStatusMapper.selectByPrimaryKey(id);
		if(profitStatus == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		profitStatus.setFk_profit_id(fk_profit_id);
		profitStatus.setType(type);
		profitStatus.setValue(value);
		profitStatus.setTime(time);
		profitStatusMapper.update(profitStatus);
		return profitStatus;
	}

	public ProfitStatus selectByPrimaryKey(Integer id) throws ErrorCodeException{
		ProfitStatus profitStatus = profitStatusMapper.selectByPrimaryKey(id);
		if(profitStatus == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return profitStatus;
	}

	public PageResponse<ProfitStatus> selectPage(Integer offset,Integer pageSize){
		PageResponse<ProfitStatus> response = new PageResponse();
		response.setItem(profitStatusMapper.selectPage(offset,pageSize));
		response.setTotal(profitStatusMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public PageResponse<ProfitStatus> selectPageByProfitId(Integer offset,Integer pageSize,Integer profit_id){
		PageResponse<ProfitStatus> response = new PageResponse();
		response.setItem(profitStatusMapper.selectPageByProfitId(offset,pageSize,profit_id));
		response.setTotal(profitStatusMapper.countByProfitId(profit_id));
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return profitStatusMapper.deleteByPrimaryKey(id);
	}

}
