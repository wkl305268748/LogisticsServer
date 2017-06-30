package com.kenny.service.logistics.controller;


import com.kenny.service.logistics.json.response.WebMenuResponse;
import com.kenny.service.logistics.service.CarService;
import com.kenny.service.logistics.service.DriverService;
import com.kenny.service.logistics.service.OrderCustomerService;
import com.kenny.service.logistics.service.OrderTakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by WKL on 2016-12-20.
 */
@RequestMapping("/web/order")
@Controller
public class WebOrderController {
    @Autowired
    OrderCustomerService orderCustomerService;
    @Autowired
    DriverService driverService;
    @Autowired
    CarService carService;
    @Autowired
    OrderTakingService orderTakingService;

    private static final int PAGESIZE = 10;

    @RequestMapping("/all")
    public String all() {
        return "order/all";
    }

    @RequestMapping("/create")
    public String create(ModelMap map) {
        return "order/create";
    }

    @RequestMapping("create_submit")
    public String create_submit(ModelMap map ,
                                @RequestParam(value = "send_name")String send_name,
                                @RequestParam(value = "send_phone")String send_phone,
                                @RequestParam(value = "send_addr")String send_addr,
                                @RequestParam(value = "recive_name")String recive_name,
                                @RequestParam(value = "recive_phone")String recive_phone,
                                @RequestParam(value = "recive_addr")String recive_addr,
                                @RequestParam(value = "dispatching_type")String dispatching_type,
                                @RequestParam(value = "send_time")Date send_time,
                                @RequestParam(value = "recive_time")Date recive_time){
        orderCustomerService.createOrderCustomer(send_name,send_phone,send_addr,recive_name,recive_phone,recive_addr,dispatching_type,send_time,recive_time);
        map.addAttribute("alert","success");
        return "order/create";
    }

    @RequestMapping("/pending")
    public String pending(ModelMap map,
                          @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",orderCustomerService.getOrderCustomerWait(PAGESIZE,(page - 1)*PAGESIZE));
        return "order/pending";
    }

    @RequestMapping("/pending_info")
    public String pending_info(ModelMap map,
                               @RequestParam(value = "id") Integer id) {
        map.addAttribute("data",orderCustomerService.getOrderCustomerById(id));
        return "order/pending_info";
    }

    @RequestMapping("/pending_success")
    public String pending_success(ModelMap map,
                                  @RequestParam(value = "id") Integer id) {
        map.addAttribute("pending",orderCustomerService.getOrderCustomerById(id));
        map.addAttribute("car",carService.getCars());
        map.addAttribute("driver",driverService.getDrivers());
        return "order/pending_success";
    }

    @RequestMapping("/pending_success_submit")
    public String pending_success_submit(ModelMap map,
                                         @RequestParam(value = "order_customer_id") Integer order_customer_id,
                                         @RequestParam(value = "car_id") Integer car_id,
                                         @RequestParam(value = "driver_id") Integer driver_id,
                                         @RequestParam(value = "pay") Integer pay,
                                         @RequestParam(value = "recive") Integer recive) {
        orderTakingService.createOrderTaking(order_customer_id,car_id,driver_id,pay,recive);
        orderCustomerService.SuccessOrderCustomer(order_customer_id);
        return "redirect:pending";
    }

    @RequestMapping("/taking_all")
    public String taking_all(ModelMap map,
                              @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data",orderTakingService.getOrderTakings(PAGESIZE,(page - 1)*PAGESIZE));
        return "order/taking_all";
    }
}
