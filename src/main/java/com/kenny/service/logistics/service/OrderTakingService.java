package com.kenny.service.logistics.service;

import com.kenny.service.logistics.json.response.OrderTakingResponse;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.CarMapper;
import com.kenny.service.logistics.mapper.DriverMapper;
import com.kenny.service.logistics.mapper.OrderCustomerMapper;
import com.kenny.service.logistics.mapper.OrderTakingMapper;
import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.OrderCustomer;
import com.kenny.service.logistics.model.OrderTaking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by WKL on 2017-6-30.
 */
@Service
public class OrderTakingService {
    @Autowired
    OrderTakingMapper orderTakingMapper;
    @Autowired
    OrderCustomerMapper orderCustomerMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    DriverMapper driverMapper;

    //创建订单
    public void createOrderTaking(int order_customer_id,int car_id,int driver_id,int pay,int recive){
        OrderTaking orderTaking = new OrderTaking();
        orderTaking.setFk_order_customer_id(order_customer_id);
        orderTaking.setFk_car_id(car_id);
        orderTaking.setFk_driver_id(driver_id);
        orderTaking.setPay(pay);
        orderTaking.setRecive(recive);
        orderTaking.setTime(new Date());
        orderTaking.setStatus("send");
        orderTakingMapper.insert(orderTaking);
    }

    //获取列表
    public PageResponse<OrderTakingResponse> getOrderTakings(int pageSize, int offset){
        PageResponse<OrderTakingResponse> response = new PageResponse<>();
        response.setItem(new ArrayList<>());
        for(OrderTaking orderTaking : orderTakingMapper.selectPage(offset,pageSize)){
            OrderTakingResponse orderTakingResponse = new OrderTakingResponse();
            orderTakingResponse.setId(orderTaking.getId());
            orderTakingResponse.setStatus(orderTaking.getStatus());
            orderTakingResponse.setPay(orderTaking.getPay());
            orderTakingResponse.setTime(orderTaking.getTime());
            orderTakingResponse.setRecive(orderTaking.getRecive());
            orderTakingResponse.setCar(carMapper.selectById(orderTaking.getFk_car_id()));
            orderTakingResponse.setDriver(driverMapper.selectById(orderTaking.getFk_driver_id()));
            orderTakingResponse.setOrderCustomer(orderCustomerMapper.selectById(orderTaking.getFk_order_customer_id()));
            response.getItem().add(orderTakingResponse);
        }
        response.setPageSize(pageSize);
        response.setOffset(offset);
        response.setTotal(orderTakingMapper.count());
        return response;
    }

    public OrderTakingResponse getOrderTakingById(int id){
        OrderTaking orderTaking = orderTakingMapper.selectById(id);
        OrderTakingResponse orderTakingResponse = new OrderTakingResponse();
        orderTakingResponse.setId(orderTaking.getId());
        orderTakingResponse.setStatus(orderTaking.getStatus());
        orderTakingResponse.setPay(orderTaking.getPay());
        orderTakingResponse.setTime(orderTaking.getTime());
        orderTakingResponse.setRecive(orderTaking.getRecive());
        orderTakingResponse.setCar(carMapper.selectById(orderTaking.getFk_car_id()));
        orderTakingResponse.setDriver(driverMapper.selectById(orderTaking.getFk_driver_id()));
        orderTakingResponse.setOrderCustomer(orderCustomerMapper.selectById(orderTaking.getFk_order_customer_id()));
        return orderTakingResponse;
    }
}
