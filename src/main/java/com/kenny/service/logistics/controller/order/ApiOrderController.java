package com.kenny.service.logistics.controller.order;


import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Api(value = "/v1/order/api", description = "用户接口模块")
@RequestMapping(value = "/v1/order/api")
@RestController
public class ApiOrderController {
    @Autowired
    OrderCustomerService orderCustomerService;

    @ApiOperation(value = "提交订单")
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean Customer(@RequestParam(value = "send_name") String send_name,
                             @RequestParam(value = "send_phone") String send_phone,
                             @RequestParam(value = "send_addr") String send_addr,
                             @RequestParam(value = "send_addr_info") String send_addr_info,
                             @RequestParam(value = "recive_name") String recive_name,
                             @RequestParam(value = "recive_phone") String recive_phone,
                             @RequestParam(value = "recive_addr") String recive_addr,
                             @RequestParam(value = "recive_addr_info") String recive_addr_info,
                             @RequestParam(value = "dispatching_type") String dispatching_type,
                             @RequestParam(value = "send_time") Date send_time,
                             @RequestParam(value = "recive_time") Date recive_time,
                             @RequestParam(value = "dispatching_type") String goods) {

        orderCustomerService.createOrderCustomer(send_name, send_phone, send_addr,send_addr_info, recive_name, recive_phone, recive_addr,recive_addr_info, dispatching_type, send_time, recive_time, goods);
        return new JsonBean(UserErrorCode.SUCCESS);
    }

    @ApiOperation(value = "提交订单")
    @RequestMapping(value = "/customer_user", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean CustomerUser(@RequestParam(value = "send_name") String send_name,
                                 @RequestParam(value = "send_phone") String send_phone,
                                 @RequestParam(value = "send_addr") String send_addr,
                                 @RequestParam(value = "recive_name") String recive_name,
                                 @RequestParam(value = "recive_phone") String recive_phone,
                                 @RequestParam(value = "recive_addr") String recive_addr,
                                 @RequestParam(value = "dispatching_type") String dispatching_type,
                                 @RequestParam(value = "send_time") Date send_time,
                                 @RequestParam(value = "recive_time") Date recive_time,
                                 @RequestParam(value = "goods", required = false) String goods) {

        //orderCustomerService.createOrderCustomer(send_name, send_phone, send_addr, recive_name, recive_phone, recive_addr, dispatching_type, send_time, recive_time, goods);
        return new JsonBean(UserErrorCode.SUCCESS);
    }
}
