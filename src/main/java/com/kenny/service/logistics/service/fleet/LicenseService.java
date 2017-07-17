package com.kenny.service.logistics.service.fleet;

import com.kenny.service.logistics.mapper.fleet.LicenseMapper;
import com.kenny.service.logistics.model.fleet.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class LicenseService{
	@Autowired
	private LicenseMapper licenseMapper;

	public License insert(String number, String type, Date pass_time, Date valid_date, Date unvalide_date, String files, String remark, Date time){
		License license = new License();
		license.setNumber(number);
		license.setType(type);
		license.setPass_time(pass_time);
		license.setValid_date(valid_date);
		license.setUnvalide_date(unvalide_date);
		license.setFiles(files);
		license.setRemark(remark);
		license.setTime(time);
		licenseMapper.insert(license);
		return license;
	}

	public License update(Integer id,String number,String type,Date pass_time,Date valid_date,Date unvalide_date,String files,String remark,Date time) throws ErrorCodeException{
		License license = licenseMapper.selectByPrimaryKey(id);
		if(license == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		license.setNumber(number);
		license.setType(type);
		license.setPass_time(pass_time);
		license.setValid_date(valid_date);
		license.setUnvalide_date(unvalide_date);
		license.setFiles(files);
		license.setRemark(remark);
		license.setTime(time);
		licenseMapper.update(license);
		return license;
	}

	public License selectByPrimaryKey(Integer id) throws ErrorCodeException{
		License license = licenseMapper.selectByPrimaryKey(id);
		if(license == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return license;
	}

	public PageResponse<License> selectPage(Integer offset,Integer pageSize){
		PageResponse<License> response = new PageResponse();
		response.setItem(licenseMapper.selectPage(offset,pageSize));
		response.setTotal(licenseMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return licenseMapper.deleteByPrimaryKey(id);
	}

}
