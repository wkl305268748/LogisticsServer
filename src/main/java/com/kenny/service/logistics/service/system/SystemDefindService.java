package com.kenny.service.logistics.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.system.SystemDefind;
import com.kenny.service.logistics.mapper.system.SystemDefindMapper;

@Service
public class SystemDefindService{
	@Autowired
	private SystemDefindMapper systemDefindMapper;

	public SystemDefind insert(String name,String type_code){
		SystemDefind systemDefind = new SystemDefind();
		systemDefind.setName(name);
		systemDefind.setType_code(type_code);
		systemDefindMapper.insert(systemDefind);
		return systemDefind;
	}

	public SystemDefind update(Integer id,String name) throws ErrorCodeException{
		SystemDefind systemDefind = systemDefindMapper.selectByPrimaryKey(id);
		if(systemDefind == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		systemDefind.setName(name);
		systemDefindMapper.update(systemDefind);
		return systemDefind;
	}

	public List<SystemDefind> select(String type_code){
		return systemDefindMapper.selectPageByType(0,100, type_code);
	}

	public PageResponse<SystemDefind> selectPageByType(Integer offset,Integer pageSize,String type_code){
		PageResponse<SystemDefind> response = new PageResponse();
		response.setItem(systemDefindMapper.selectPageByType(offset,pageSize,type_code));
		response.setTotal(systemDefindMapper.countByType(type_code));
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return systemDefindMapper.deleteByPrimaryKey(id);
	}

}
