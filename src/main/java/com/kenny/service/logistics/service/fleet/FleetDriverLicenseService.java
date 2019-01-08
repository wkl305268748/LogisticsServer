package com.kenny.service.logistics.service.fleet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenny.service.logistics.mapper.fleet.FleetDriverLicenseMapper;
import com.kenny.service.logistics.model.po.fleet.FleetDriverLicense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class FleetDriverLicenseService {
	@Autowired
	private FleetDriverLicenseMapper fleetDriverLicenseMapper;

	public FleetDriverLicense insertByDriver(String json, Integer fk_driver_id){
		FleetDriverLicense fleetDriverLicense = null;
		if(json == null)
			return null;
		if (!json.equals("")) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
			fleetDriverLicense = gson.fromJson(json,FleetDriverLicense.class);
			fleetDriverLicense.setFk_driver_id(fk_driver_id);
			fleetDriverLicense.setTime(new Date());
			fleetDriverLicense.setFiles(fleetDriverLicense.getFiles().toString());
			fleetDriverLicenseMapper.insert(fleetDriverLicense);
		}
		return fleetDriverLicense;
	}

	public FleetDriverLicense insert(Integer fk_driver_id, String number, String level, Date valid_time, Date unvalid_time, Date pass_time, String work_license, String ic_number, String files, Date time){
		FleetDriverLicense fleetDriverLicense = new FleetDriverLicense();
		fleetDriverLicense.setFk_driver_id(fk_driver_id);
		fleetDriverLicense.setNumber(number);
		fleetDriverLicense.setLevel(level);
		fleetDriverLicense.setValid_time(valid_time);
		fleetDriverLicense.setUnvalid_time(unvalid_time);
		fleetDriverLicense.setPass_time(pass_time);
		fleetDriverLicense.setWork_license(work_license);
		fleetDriverLicense.setIc_number(ic_number);
		fleetDriverLicense.setFiles(files);
		fleetDriverLicense.setTime(time);
		fleetDriverLicenseMapper.insert(fleetDriverLicense);
		return fleetDriverLicense;
	}

	public FleetDriverLicense update(Integer id, Integer fk_driver_id, String number, String level, Date valid_time, Date unvalid_time, Date pass_time, String work_license, String ic_number, String files, Date time) throws ErrorCodeException{
		FleetDriverLicense fleetDriverLicense = fleetDriverLicenseMapper.selectByPrimaryKey(id);
		if(fleetDriverLicense == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		fleetDriverLicense.setFk_driver_id(fk_driver_id);
		fleetDriverLicense.setNumber(number);
		fleetDriverLicense.setLevel(level);
		fleetDriverLicense.setValid_time(valid_time);
		fleetDriverLicense.setUnvalid_time(unvalid_time);
		fleetDriverLicense.setPass_time(pass_time);
		fleetDriverLicense.setWork_license(work_license);
		fleetDriverLicense.setIc_number(ic_number);
		fleetDriverLicense.setFiles(files);
		fleetDriverLicense.setTime(time);
		fleetDriverLicenseMapper.update(fleetDriverLicense);
		return fleetDriverLicense;
	}

	public FleetDriverLicense selectByPrimaryKey(Integer id) throws ErrorCodeException{
		FleetDriverLicense fleetDriverLicense = fleetDriverLicenseMapper.selectByPrimaryKey(id);
		if(fleetDriverLicense == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return fleetDriverLicense;
	}

	public PageResponse<FleetDriverLicense> selectPage(Integer offset, Integer pageSize){
		PageResponse<FleetDriverLicense> response = new PageResponse();
		response.setItem(fleetDriverLicenseMapper.selectPage(offset,pageSize));
		response.setTotal(fleetDriverLicenseMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return fleetDriverLicenseMapper.deleteByPrimaryKey(id);
	}

}
