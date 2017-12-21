package com.kenny.service.logistics.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.system.SystemVersion;
import com.kenny.service.logistics.mapper.system.SystemVersionMapper;

@Service
public class SystemVersionService{
	@Autowired
	private SystemVersionMapper systemVersionMapper;

	public SystemVersion insert(String type,String version,Integer version_number,String changelog,String url){
		SystemVersion systemVersion = new SystemVersion();
		systemVersion.setType(type);
		systemVersion.setVersion(version);
		systemVersion.setVersion_number(version_number);
		systemVersion.setChangelog(changelog);
		systemVersion.setUrl(url);
		systemVersion.setTime(new Date());
		systemVersionMapper.insert(systemVersion);
		return systemVersion;
	}

	public SystemVersion update(Integer id,String type,String version,Integer version_number,String changelog,String url) throws ErrorCodeException{
		SystemVersion systemVersion = systemVersionMapper.selectByPrimaryKey(id);
		if(systemVersion == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		systemVersion.setType(type);
		systemVersion.setVersion(version);
		systemVersion.setVersion_number(version_number);
		systemVersion.setChangelog(changelog);
		systemVersion.setUrl(url);
		systemVersionMapper.update(systemVersion);
		return systemVersion;
	}

	public SystemVersion selectByPrimaryKey(Integer id) throws ErrorCodeException{
		SystemVersion systemVersion = systemVersionMapper.selectByPrimaryKey(id);
		if(systemVersion == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return systemVersion;
	}

	public SystemVersion selectTopByType(String type) throws ErrorCodeException{
		SystemVersion systemVersion = systemVersionMapper.selectTopByType(type);
		if(systemVersion == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return systemVersion;
	}

	public PageResponse<SystemVersion> selectPage(Integer offset,Integer pageSize){
		PageResponse<SystemVersion> response = new PageResponse();
		response.setItem(systemVersionMapper.selectPage(offset,pageSize));
		response.setTotal(systemVersionMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return systemVersionMapper.deleteByPrimaryKey(id);
	}

}
