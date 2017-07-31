package com.kenny.service.logistics.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.system.SystemConfig;
import com.kenny.service.logistics.mapper.system.SystemConfigMapper;

@Service
public class SystemConfigService{
	@Autowired
	private SystemConfigMapper systemConfigMapper;

	public SystemConfig insert(String name,String code,String value){
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setName(name);
		systemConfig.setCode(code);
		systemConfig.setValue(value);
		systemConfigMapper.insert(systemConfig);
		return systemConfig;
	}

	public SystemConfig update(Integer id,String value) throws ErrorCodeException{
		SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey(id);
		if(systemConfig == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		systemConfig.setValue(value);
		systemConfigMapper.update(systemConfig);
		return systemConfig;
	}

	public SystemConfig selectByPrimaryKey(Integer id) throws ErrorCodeException{
		SystemConfig systemConfig = systemConfigMapper.selectByPrimaryKey(id);
		if(systemConfig == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return systemConfig;
	}

	public PageResponse<SystemConfig> selectPage(Integer offset,Integer pageSize){
		PageResponse<SystemConfig> response = new PageResponse();
		response.setItem(systemConfigMapper.selectPage(offset,pageSize));
		response.setTotal(systemConfigMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return systemConfigMapper.deleteByPrimaryKey(id);
	}

	public String getValueByCode(String code){
		SystemConfig systemConfig = systemConfigMapper.selectByCode(code);
		if(systemConfig == null){
			return "";
		}
		return systemConfig.getValue();
	}
}
