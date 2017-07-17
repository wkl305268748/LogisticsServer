package com.kenny.service.logistics.service.fleet;

import com.kenny.service.logistics.mapper.fleet.DriverLicenseMapper;
import com.kenny.service.logistics.model.fleet.DriverLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class DriverLicenseService{
	@Autowired
	private DriverLicenseMapper driverLicenseMapper;

	public DriverLicense insert(Integer fk_driver_id, String number, String level, Date valid_time, Date unvalid_time, Date pass_time, String work_license, String ic_number, String files, Date time){
		DriverLicense driverLicense = new DriverLicense();
		driverLicense.setFk_driver_id(fk_driver_id);
		driverLicense.setNumber(number);
		driverLicense.setLevel(level);
		driverLicense.setValid_time(valid_time);
		driverLicense.setUnvalid_time(unvalid_time);
		driverLicense.setPass_time(pass_time);
		driverLicense.setWork_license(work_license);
		driverLicense.setIc_number(ic_number);
		driverLicense.setFiles(files);
		driverLicense.setTime(time);
		driverLicenseMapper.insert(driverLicense);
		return driverLicense;
	}

	public DriverLicense update(Integer id,Integer fk_driver_id,String number,String level,Date valid_time,Date unvalid_time,Date pass_time,String work_license,String ic_number,String files,Date time) throws ErrorCodeException{
		DriverLicense driverLicense = driverLicenseMapper.selectByPrimaryKey(id);
		if(driverLicense == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		driverLicense.setFk_driver_id(fk_driver_id);
		driverLicense.setNumber(number);
		driverLicense.setLevel(level);
		driverLicense.setValid_time(valid_time);
		driverLicense.setUnvalid_time(unvalid_time);
		driverLicense.setPass_time(pass_time);
		driverLicense.setWork_license(work_license);
		driverLicense.setIc_number(ic_number);
		driverLicense.setFiles(files);
		driverLicense.setTime(time);
		driverLicenseMapper.update(driverLicense);
		return driverLicense;
	}

	public DriverLicense selectByPrimaryKey(Integer id) throws ErrorCodeException{
		DriverLicense driverLicense = driverLicenseMapper.selectByPrimaryKey(id);
		if(driverLicense == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return driverLicense;
	}

	public PageResponse<DriverLicense> selectPage(Integer offset,Integer pageSize){
		PageResponse<DriverLicense> response = new PageResponse();
		response.setItem(driverLicenseMapper.selectPage(offset,pageSize));
		response.setTotal(driverLicenseMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return driverLicenseMapper.deleteByPrimaryKey(id);
	}

}
