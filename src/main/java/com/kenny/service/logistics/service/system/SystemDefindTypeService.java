package com.kenny.service.logistics.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.po.system.SystemDefindType;
import com.kenny.service.logistics.mapper.system.SystemDefindTypeMapper;

@Service
public class SystemDefindTypeService{
	@Autowired
	private SystemDefindTypeMapper systemDefindTypeMapper;

	public SystemDefindType insert(String name){
		SystemDefindType systemDefindType = new SystemDefindType();
		systemDefindType.setName(name);
		systemDefindTypeMapper.insert(systemDefindType);
		return systemDefindType;
	}

	public SystemDefindType update(Integer id,String name) throws ErrorCodeException{
		SystemDefindType systemDefindType = systemDefindTypeMapper.selectByPrimaryKey(id);
		if(systemDefindType == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		systemDefindType.setName(name);
		systemDefindTypeMapper.update(systemDefindType);
		return systemDefindType;
	}

	public SystemDefindType selectByPrimaryKey(Integer id) throws ErrorCodeException{
		SystemDefindType systemDefindType = systemDefindTypeMapper.selectByPrimaryKey(id);
		if(systemDefindType == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return systemDefindType;
	}

	public List<SystemDefindType> select(){
		return systemDefindTypeMapper.selectPage(0,100);
	}

	public PageResponse<SystemDefindType> selectPage(Integer offset,Integer pageSize){
		PageResponse<SystemDefindType> response = new PageResponse();
		response.setItem(systemDefindTypeMapper.selectPage(offset,pageSize));
		response.setTotal(systemDefindTypeMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return systemDefindTypeMapper.deleteByPrimaryKey(id);
	}

}
