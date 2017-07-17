package com.kenny.service.logistics.service.fleet;

import com.kenny.service.logistics.mapper.fleet.CarMapper;
import com.kenny.service.logistics.model.fleet.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;

@Service
public class CarService{
	@Autowired
	private CarMapper carMapper;

	public Car insert(String plate, String type, String resource, String two_plate, String driver_phone, String driver_name, String energy, String length, Float weight, String vin, String brand, String engine, String axle, String wheelbase, String tire, Date factory_time, Date buy_time, Integer buy_price, Date limited_time, Date tow_maintain_time, String insurance_policy, String insurance_company, Date insurance_time, String front_img, String tail_img, String remark){
		Car car = new Car();
		car.setPlate(plate);
		car.setType(type);
		car.setResource(resource);
		car.setTwo_plate(two_plate);
		car.setDriver_phone(driver_phone);
		car.setDriver_name(driver_name);
		car.setEnergy(energy);
		car.setLength(length);
		car.setWeight(weight);
		car.setVin(vin);
		car.setBrand(brand);
		car.setEngine(engine);
		car.setAxle(axle);
		car.setWheelbase(wheelbase);
		car.setTire(tire);
		car.setFactory_time(factory_time);
		car.setBuy_time(buy_time);
		car.setBuy_price(buy_price);
		car.setLimited_time(limited_time);
		car.setTow_maintain_time(tow_maintain_time);
		car.setInsurance_policy(insurance_policy);
		car.setInsurance_company(insurance_company);
		car.setInsurance_time(insurance_time);
		car.setFront_img(front_img);
		car.setTail_img(tail_img);
		car.setRemark(remark);
		car.setTime(new Date());
		carMapper.insert(car);
		return car;
	}

	public Car update(Integer id,String plate,String type,String resource,String two_plate,String driver_phone,String driver_name,String energy,String length,Float weight,String vin,String brand,String engine,String axle,String wheelbase,String tire,Date factory_time,Date buy_time,Integer buy_price,Date limited_time,Date tow_maintain_time,String insurance_policy,String insurance_company,Date insurance_time,String front_img,String tail_img,String remark) throws ErrorCodeException{
		Car car = carMapper.selectByPrimaryKey(id);
		if(car == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		car.setPlate(plate);
		car.setType(type);
		car.setResource(resource);
		car.setTwo_plate(two_plate);
		car.setDriver_phone(driver_phone);
		car.setDriver_name(driver_name);
		car.setEnergy(energy);
		car.setLength(length);
		car.setWeight(weight);
		car.setVin(vin);
		car.setBrand(brand);
		car.setEngine(engine);
		car.setAxle(axle);
		car.setWheelbase(wheelbase);
		car.setTire(tire);
		car.setFactory_time(factory_time);
		car.setBuy_time(buy_time);
		car.setBuy_price(buy_price);
		car.setLimited_time(limited_time);
		car.setTow_maintain_time(tow_maintain_time);
		car.setInsurance_policy(insurance_policy);
		car.setInsurance_company(insurance_company);
		car.setInsurance_time(insurance_time);
		car.setFront_img(front_img);
		car.setTail_img(tail_img);
		car.setRemark(remark);
		carMapper.update(car);
		return car;
	}

	public Car selectByPrimaryKey(Integer id) throws ErrorCodeException{
		Car car = carMapper.selectByPrimaryKey(id);
		if(car == null){
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		return car;
	}

	public PageResponse<Car> selectPage(Integer offset,Integer pageSize){
		PageResponse<Car> response = new PageResponse();
		response.setItem(carMapper.selectPage(offset,pageSize));
		response.setTotal(carMapper.count());
		response.setOffset(offset);
		response.setPageSize(pageSize);
		return response;
	}

	public int deleteByPrimaryKey(Integer id){
		return carMapper.deleteByPrimaryKey(id);
	}

}
