package com.kenny.service.logistics.service.fleet;

import com.kenny.service.logistics.mapper.fleet.DriverLicenseMapper;
import com.kenny.service.logistics.mapper.fleet.DriverMapper;
import com.kenny.service.logistics.mapper.fleet.LicenseMapper;
import com.kenny.service.logistics.model.fleet.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class DriverService{
	@Autowired
	private DriverMapper driverMapper;

	public Driver insert(String name,String sex,String phone,Integer fk_user_id,Boolean is_sms,String idcard,String email,String hometown,String remark){
		Driver driver = new Driver();
		driver.setName(name);
		driver.setSex(sex);
		driver.setPhone(phone);
		driver.setFk_user_id(fk_user_id);
		driver.setIs_sms(is_sms);
		driver.setIdcard(idcard);
		driver.setEmail(email);
		driver.setHometown(hometown);
		driver.setRemark(remark);
		driver.setVisible(true);
		driverMapper.insert(driver);
		return driver;
	}

	public Driver update(Integer id,String name,String sex,String phone,Integer fk_user_id,Boolean is_sms,String idcard,String email,String hometown,String remark) throws ErrorCodeException{
		Driver driver = driverMapper.selectByPrimaryKey(id);
		if(driver == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		driver.setName(name);
		driver.setSex(sex);
		driver.setPhone(phone);
		driver.setFk_user_id(fk_user_id);
		driver.setIs_sms(is_sms);
		driver.setIdcard(idcard);
		driver.setEmail(email);
		driver.setHometown(hometown);
		driver.setRemark(remark);
		driverMapper.update(driver);
		return driver;
	}

	public Driver selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Driver driver = driverMapper.selectByPrimaryKey(id);
		if(driver == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return driver;
	}

	public PageResponse<Driver> selectPage(Integer offset,Integer pageSize){
		PageResponse<Driver> response = new PageResponse();
		response.setItem(driverMapper.selectPageByVisible(offset,pageSize,true));
		response.setTotal(driverMapper.countByVisible((true)));
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	/**
	 * 删除车辆
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Integer id){
		Driver driver = driverMapper.selectByPrimaryKey(id);
		if(driver == null)
			return 0;
		driver.setVisible(true);
		return driverMapper.update(driver);
	}
}
