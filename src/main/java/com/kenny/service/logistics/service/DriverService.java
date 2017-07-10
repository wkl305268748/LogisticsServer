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
}
