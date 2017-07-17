package com.kenny.service.logistics.controller_web;


import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderTaking;
import com.kenny.service.logistics.model.system.Defind;
import com.kenny.service.logistics.service.*;
import com.kenny.service.logistics.service.fleet.CarService;
import com.kenny.service.logistics.service.fleet.DriverService;
import com.kenny.service.logistics.service.order.*;
import com.kenny.service.logistics.util.FileUtil;
import com.kenny.service.logistics.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    @Autowired
    OrderSignService orderSignService;
    @Autowired
    ProfitService profitService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderStatusService orderStatusService;

    private static final int PAGESIZE = 10;

    @RequestMapping("/all")
    public String all(ModelMap map,
                      @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) {
        map.addAttribute("data", orderCustomerService.selectPage((page - 1)*PAGESIZE,PAGESIZE));
        if(MessageUtil.is_message) {
            map.addAttribute("message", MessageUtil.message);
            MessageUtil.is_message = false;
        }
        return "order/all";
    }

    @RequestMapping("/search")
    public String search(ModelMap map,
                         @RequestParam(value = "order_number",required = false) String order_number,
                         @RequestParam(value = "status",required = false) String[] status){

        return "redirect:all";
    }

    @RequestMapping("/create")
    public String create() {
        return "order/create";
    }


    @RequestMapping("/edit")
    public String edit(ModelMap map,@RequestParam(value = "id") Integer id) throws ErrorCodeException {
        map.addAttribute("data",orderCustomerService.selectByPrimaryKey(id));
        return "order/edit";
    }

    @RequestMapping("edit_submit")
    public String edit_submit(ModelMap map ,
                                @RequestParam(value = "id") Integer id,
                                @RequestParam(value = "send_name")String send_name,
                                @RequestParam(value = "send_phone")String send_phone,
                                @RequestParam(value = "send_addr")String send_addr,
                                @RequestParam(value = "recive_name")String recive_name,
                                @RequestParam(value = "recive_phone")String recive_phone,
                                @RequestParam(value = "recive_addr")String recive_addr,
                                @RequestParam(value = "dispatching_type")String dispatching_type,
                                @RequestParam(value = "send_time")Date send_time,
                                @RequestParam(value = "recive_time")Date recive_time,
                                @RequestParam(value = "remark",required = false)String remark) throws ErrorCodeException {
        orderCustomerService.update(id,send_name,send_phone,send_addr,"",recive_name,recive_phone,recive_addr,"",dispatching_type,send_time,recive_time,remark);

        MessageUtil.is_message = true;
        MessageUtil.message = "修改成功！";
        return "redirect:all";
    }


    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "id") Integer id) {
        orderCustomerService.deleteByPrimaryKey(id);
        MessageUtil.is_message = true;
        MessageUtil.message = "删除成功！";
        return "redirect:all";
    }

    @RequestMapping("create_submit")
    public String create_submit(ModelMap map ,
                                /*
                                @RequestParam(value = "send_name")String send_name,
                                @RequestParam(value = "send_phone")String send_phone,
                                @RequestParam(value = "send_addr")String send_addr,
                                @RequestParam(value = "recive_name")String recive_name,
                                @RequestParam(value = "recive_phone")String recive_phone,
                                @RequestParam(value = "recive_addr")String recive_addr,
                                @RequestParam(value = "dispatching_type")String dispatching_type,
                                @RequestParam(value = "send_time")Date send_time,
                                @RequestParam(value = "recive_time")Date recive_time,*/
                                @RequestParam(value = "goods[][name]")List<String> goods){

        System.out.println(goods.size());

        //orderCustomerService.createOrderCustomer(send_name,send_phone,send_addr,recive_name,recive_phone,recive_addr,dispatching_type,send_time,recive_time,"");

        MessageUtil.is_message = true;
        MessageUtil.message = "下单成功！";
        return "redirect:all";
    }

    @RequestMapping("/pending")
    public String pending(ModelMap map,
                          @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) throws ErrorCodeException {
        map.addAttribute("data",orderService.selectPageByStatus((page - 1)*PAGESIZE,PAGESIZE, Defind.ORDER_PLACE));
        return "order/pending";
    }

    @RequestMapping("/pending_info")
    public String pending_info(ModelMap map,
                               @RequestParam(value = "id") Integer id) throws ErrorCodeException {
        map.addAttribute("data",orderCustomerService.selectByPrimaryKey(id));
        return "order/pending_info";
    }

    @RequestMapping("/pending_success")
    public String pending_success(ModelMap map,
                                  @RequestParam(value = "id") Integer id) throws ErrorCodeException {
        map.addAttribute("pending",orderCustomerService.selectByPrimaryKey(id));
        map.addAttribute("car",carService.selectPage(0,100).getItem());
        map.addAttribute("driver",driverService.selectPage(0,100).getItem());
        return "order/pending_success";
    }

    @RequestMapping("/pending_success_submit")
    public String pending_success_submit(ModelMap map,
                                         @RequestParam(value = "order_customer_id") Integer order_customer_id,
                                         @RequestParam(value = "car_id") Integer car_id,
                                         @RequestParam(value = "driver_id") Integer driver_id,
                                         @RequestParam(value = "pay") Integer pay,
                                         @RequestParam(value = "recive") Integer recive) throws ErrorCodeException {
        OrderTaking orderTaking = orderTakingService.taking(order_customer_id,car_id,driver_id,pay,recive);
        OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(order_customer_id);
        orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_TAKING", 1);
        orderCustomerService.updateStatus(order_customer_id,"ORDER_TAKING");
        profitService.addProfit(orderCustomer.getOrder_number(),orderTaking.getId(),pay,recive);

        MessageUtil.is_message = true;
        MessageUtil.message = "派车成功！";
        return "redirect:taking_all";
    }

    @RequestMapping("/pending_ignor")
    public String pending_ignor(ModelMap map,
                                @RequestParam(value = "id") Integer id) throws ErrorCodeException {
        OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(id);
        orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_REFUSE", 1);
        orderCustomerService.updateStatus(orderCustomer.getId(),"ORDER_REFUSE");
        return "redirect:pending";
    }

    @RequestMapping("/taking_all")
    public String taking_all(ModelMap map,
                              @RequestParam(value = "page",required = false,defaultValue = "1") Integer page) throws ErrorCodeException {
        map.addAttribute("data",orderService.selectPageByStatus((page - 1)*PAGESIZE,PAGESIZE,Defind.ORDER_TAKING));
        if(MessageUtil.is_message) {
            map.addAttribute("message", MessageUtil.message);
            MessageUtil.is_message = false;
        }
        return "order/taking_all";
    }

    @RequestMapping("/taking_info")
    public String taking_info(ModelMap map,
                              @RequestParam(value = "id") Integer id) throws ErrorCodeException {
        OrderTaking orderTaking = orderTakingService.selectByPrimaryKey(id);

        map.addAttribute("data",orderTakingService.selectByPrimaryKey(id));
        map.addAttribute("order_sign",orderSignService.selectByOrderCustomer(orderTaking.getFk_order_customer_id()));

        return "order/taking_info";
    }

    @RequestMapping("/taking_finish")
    public String taking_finish(@RequestParam(value = "order_taking_id") Integer order_taking_id,
                                @RequestParam(value = "order_img") MultipartFile order_img,
                                HttpServletRequest request) throws IOException, ErrorCodeException {
        //增加sign表
        OrderTaking orderTaking = orderTakingService.selectByPrimaryKey(order_taking_id);
        //
        String name = FileUtil.saveImg(order_img);
        orderSignService.insert(order_taking_id,orderTaking.getFk_order_customer_id(),request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort()+"/web/img/"+name);

        OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(orderTaking.getFk_order_customer_id());
        orderStatusService.insert(orderCustomer.getOrder_number(), Defind.ORDER_SIGN, 1);
        orderCustomerService.updateStatus(orderCustomer.getId(),Defind.ORDER_SIGN);
        //
        MessageUtil.is_message = true;
        MessageUtil.message = "签收成功！";
        return "redirect:taking_all";
    }
}
