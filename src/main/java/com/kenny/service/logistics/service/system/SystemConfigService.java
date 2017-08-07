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

	public void init(Integer belong_user_id){
		systemConfigMapper.insert(new SystemConfig("公司名称","company","",belong_user_id));
		systemConfigMapper.insert(new SystemConfig("公司银行户名","company_bank_name","",belong_user_id));
		systemConfigMapper.insert(new SystemConfig("公司银行账号","company_bank_number","",belong_user_id));
		systemConfigMapper.insert(new SystemConfig("公司银行","company_bank","",belong_user_id));
		systemConfigMapper.insert(new SystemConfig("公司银行行点","company_bank_addr","",belong_user_id));
	}

	public SystemConfig insert(String name,String code,String value,Integer belong_user_id){
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.setName(name);
		systemConfig.setCode(code);
		systemConfig.setValue(value);
		systemConfig.setBelong_user_id(belong_user_id);
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

	public PageResponse<SystemConfig> selectPageByBelong(Integer offset,Integer pageSize,Integer belong_user_id){
		PageResponse<SystemConfig> response = new PageResponse();
		response.setItem(systemConfigMapper.selectPageByBelongUser(offset,pageSize,belong_user_id));
		response.setTotal(systemConfigMapper.countByBelongUser(belong_user_id));
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return systemConfigMapper.deleteByPrimaryKey(id);
	}

	public String getValueByCode(Integer belong_user_id,String code){
		SystemConfig systemConfig = systemConfigMapper.selectByCodeAndBelong(code,belong_user_id);
		if(systemConfig == null){
			return "";
		}
		return systemConfig.getValue();
	}
}
