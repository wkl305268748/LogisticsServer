package com.kenny.service.logistics.service;

import com.kenny.service.logistics.mapper.CarMapper;
import com.kenny.service.logistics.mapper.DriverMapper;
import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
