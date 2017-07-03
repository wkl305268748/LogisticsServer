package com.kenny.service.logistics.service;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.CarMapper;
import com.kenny.service.logistics.mapper.DriverMapper;
import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by WKL on 2017-6-30.
 */
@Service
public class CarService {
    @Autowired
    CarMapper carMapper;

    public List<Car> getCars(){
        return carMapper.select();
    }


    //获取所有的
    public PageResponse<Car> getCarsAll(int pageSize, int offset){
        List<Car> drivers = carMapper.selectPage(offset,pageSize);
        int count = carMapper.count();
        PageResponse<Car> response = new PageResponse();
        response.setItem(drivers);
        response.setTotal(count);
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public Car getCar(int id){
        return carMapper.selectById(id);
    }

    public void addCar(String car_plate,String car_resource,String car_type,String remark){
        Car car = new Car();
        car.setCar_plate(car_plate);
        car.setCar_resource(car_resource);
        car.setCar_type(car_type);
        car.setRemark(remark);
        car.setTime(new Date());
        car.setVisible(true);
        carMapper.insert(car);
    }

    public void editCar(int id,String car_plate,String car_resource,String car_type,String remark){
        Car car = carMapper.selectById(id);
        car.setCar_plate(car_plate);
        car.setCar_resource(car_resource);
        car.setCar_type(car_type);
        car.setRemark(remark);
        carMapper.update(car);
    }

    public void deleteCar(int id){
        Car car = carMapper.selectById(id);
        car.setVisible(false);
        carMapper.update(car);
    }

}
