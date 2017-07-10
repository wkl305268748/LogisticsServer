package com.kenny.service.logistics.service;

import com.kenny.service.logistics.mapper.DriverMapper;
import com.kenny.service.logistics.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by WKL on 2017-6-30.
 */
@Service
public class DriverService {
    @Autowired
    DriverMapper driverMapper;

    public List<Driver> getDrivers(){
        return driverMapper.select();
    }

    //获取所有的
    public PageResponse<Driver> getDriversAll(int pageSize, int offset){
        List<Driver> drivers = driverMapper.selectPage(offset,pageSize);
        int count = driverMapper.count();
        PageResponse<Driver> response = new PageResponse();
        response.setItem(drivers);
        response.setTotal(count);
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public Driver getDriver(int id){
        return driverMapper.selectById(id);
    }

    public void addDriver(String name,String phone,String sex,String driver_license,String remark){
        Driver driver = new Driver();
        driver.setName(name);
        driver.setPhone(phone);
        driver.setSex(sex);
        driver.setDriver_license(driver_license);
        driver.setRemark(remark);
        driver.setTime(new Date());
        driver.setVisible(true);
        driverMapper.insert(driver);
    }

    public void editDriver(int id,String name,String phone,String sex,String driver_license,String remark){
        Driver driver = driverMapper.selectById(id);
        driver.setName(name);
        driver.setPhone(phone);
        driver.setSex(sex);
        driver.setDriver_license(driver_license);
        driver.setRemark(remark);
        driverMapper.update(driver);
    }

    public void deleteDriver(int id){
        Driver driver = driverMapper.selectById(id);
        driver.setVisible(false);
        driverMapper.update(driver);
    }

}
