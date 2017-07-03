package com.kenny.service.logistics.service;

import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.OrderCustomerMapper;
import com.kenny.service.logistics.model.OrderCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by WKL on 2017-6-30.
 */
@Service
public class OrderCustomerService {
    @Autowired
    OrderCustomerMapper orderCustomerMapper;

    //创建订单
    public void createOrderCustomer(String send_name,
                                    String send_phone,
                                    String send_addr,
                                    String recive_name,
                                    String recive_phone,
                                    String recive_addr,
                                    String dispatching_type,
                                    Date send_time,
                                    Date recive_time,
                                    String remark) {
        OrderCustomer orderCustomer = new OrderCustomer();
        //流水号
        orderCustomer.setSerial_number(createSerialNumber());
        //单号
        orderCustomer.setOrder_number(createOrderNumber());
        orderCustomer.setTime(new Date());
        orderCustomer.setSend_name(send_name);
        orderCustomer.setSend_addr(send_addr);
        orderCustomer.setSend_phone(send_phone);
        orderCustomer.setSend_time(send_time);
        orderCustomer.setRecive_name(recive_name);
        orderCustomer.setRecive_phone(recive_phone);
        orderCustomer.setRecive_addr(recive_addr);
        orderCustomer.setRecive_time(recive_time);
        orderCustomer.setRemark(remark);
        orderCustomer.setDispatching_type(dispatching_type);
        orderCustomer.setStatus("wait");
        orderCustomerMapper.insert(orderCustomer);
    }

    //更新订单
    public void updateOrderCustomer(int id,
                                    String send_name,
                                    String send_phone,
                                    String send_addr,
                                    String recive_name,
                                    String recive_phone,
                                    String recive_addr,
                                    String dispatching_type,
                                    Date send_time,
                                    Date recive_time,
                                    String remark) {
        OrderCustomer orderCustomer = orderCustomerMapper.selectById(id);
        orderCustomer.setSend_name(send_name);
        orderCustomer.setSend_addr(send_addr);
        orderCustomer.setSend_phone(send_phone);
        orderCustomer.setSend_time(send_time);
        orderCustomer.setRecive_name(recive_name);
        orderCustomer.setRecive_phone(recive_phone);
        orderCustomer.setRecive_addr(recive_addr);
        orderCustomer.setRecive_time(recive_time);
        orderCustomer.setRemark(remark);
        orderCustomer.setDispatching_type(dispatching_type);
        orderCustomerMapper.update(orderCustomer);
    }

    //获取待处理的
    public PageResponse<OrderCustomer> getOrderCustomerWait(int pageSize, int offset){
        List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByStatus(offset,pageSize,"wait");
        int count = orderCustomerMapper.countByStatus("wait");
        PageResponse<OrderCustomer> response = new PageResponse();
        response.setItem(orderCustomers);
        response.setTotal(count);
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    //获取所有的
    public PageResponse<OrderCustomer> getOrderCustomerAll(int pageSize, int offset){
        List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPage(offset,pageSize);
        int count = orderCustomerMapper.count();
        PageResponse<OrderCustomer> response = new PageResponse();
        response.setItem(orderCustomers);
        response.setTotal(count);
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public void IgnorOrderCustomer(int id){
        OrderCustomer orderCustomer = orderCustomerMapper.selectById(id);
        orderCustomer.setStatus("ignor");
        orderCustomerMapper.update(orderCustomer);
    }

    public OrderCustomer SuccessOrderCustomer(int id){
        OrderCustomer orderCustomer = orderCustomerMapper.selectById(id);
        orderCustomer.setStatus("success");
        orderCustomerMapper.update(orderCustomer);
        return orderCustomer;
    }

    public OrderCustomer getOrderCustomerById(int id){
        return orderCustomerMapper.selectById(id);
    }

    public void deleteOrderCustomerById(int id){
         orderCustomerMapper.deleteById(id);
    }

    private String createSerialNumber(){
        Random random = new Random(System.currentTimeMillis());
        String number = new Date().getTime()+"";
        for(int i=0;i<5;i++)
            number = number + random.nextInt(9);
        return number;
    }

    private String createOrderNumber(){
        Random random = new Random(System.currentTimeMillis());
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyyMMddHHmm");
        String number = myFmt.format(new Date());
        for(int i=0;i<5;i++)
            number = number + random.nextInt(9);
        return number;
    }
}
