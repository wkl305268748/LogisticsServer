package com.kenny.service.logistics.controller.order;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderGoods;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.order.*;
import com.kenny.service.logistics.service.user.UserCustomerService;
import com.kenny.service.logistics.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Api(value = "/v1/order/customer", description = "客户订单接口模块")
@RequestMapping(value = "/v1/order/customer")
@RestController
public class OrderCustomerController {
    @Autowired
    OrderCustomerService orderCustomerService;
    @Autowired
    UserService userService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    UserCustomerService userCustomerService;

    @ApiOperation(value = "增加OrderCustomer")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public JsonBean<OrderCustomer> insert(@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
                                          @ApiParam(value = "发件人姓名", required = true) @RequestParam(value = "send_name", required = true) String send_name,
                                          @ApiParam(value = "发件人手机", required = true) @RequestParam(value = "send_phone", required = true) String send_phone,
                                          @ApiParam(value = "发件人省市区地址，用/隔开", required = true) @RequestParam(value = "send_addr", required = true) String send_addr,
                                          @ApiParam(value = "发件人详细地址", required = true) @RequestParam(value = "send_addr_info", required = true) String send_addr_info,
                                          @ApiParam(value = "收件人姓名", required = true) @RequestParam(value = "recive_name", required = true) String recive_name,
                                          @ApiParam(value = "收件人电话", required = true) @RequestParam(value = "recive_phone", required = true) String recive_phone,
                                          @ApiParam(value = "收件人地址", required = true) @RequestParam(value = "recive_addr", required = true) String recive_addr,
                                          @ApiParam(value = "收件人详细地址", required = true) @RequestParam(value = "recive_addr_info", required = true) String recive_addr_info,
                                          @ApiParam(value = "预计发送时间", required = true) @RequestParam(value = "send_time", required = true) Date send_time,
                                          @ApiParam(value = "限时到达时间", required = true) @RequestParam(value = "recive_time", required = true) Date recive_time,
                                          @ApiParam(value = "配送还是自提", required = true) @RequestParam(value = "dispatching_type", required = true) String dispatching_type,
                                          @ApiParam(value = "运费", required = true) @RequestParam(value = "freight", required = true) Float freight,
                                          @ApiParam(value = "保险", required = true) @RequestParam(value = "safes", required = true) Float safes,
                                          @ApiParam(value = "货物列表json", required = false) @RequestParam(value = "goods[]", required = false) String goods) {

        try {
            User user = userService.getUser(token);
            if(user.getType().equals(UserCustomerService.type))
                userCustomerService.checkMoney(token,(int)(freight + safes));
            OrderCustomer orderCustomer = orderCustomerService.insert(send_name, send_phone, send_addr, send_addr_info, recive_name, recive_phone, recive_addr, recive_addr_info, dispatching_type, send_time, recive_time, user.getId(),freight,safes,goods);
            orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_PLACE", user.getId());
            orderCustomerService.updateStatus(orderCustomer.getId(),"ORDER_PLACE");

            if(user.getType().equals(UserCustomerService.type))
                userCustomerService.reduceMoney(token,(int)(freight + safes),orderCustomer.getOrder_number());
            return new JsonBean(UserErrorCode.SUCCESS, orderCustomer);
        } catch (ErrorCodeException e){
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "修改指定的OrderCustomer")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<OrderCustomer> update(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id,
                                          @ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
                                          @ApiParam(value = "下单用户来源", required = false) @RequestParam(value = "order_users_from", required = false) String order_users_from,
                                          @ApiParam(value = "发件人姓名", required = false) @RequestParam(value = "send_name", required = false) String send_name,
                                          @ApiParam(value = "发件人手机", required = false) @RequestParam(value = "send_phone", required = false) String send_phone,
                                          @ApiParam(value = "发件人省市区地址，用/隔开", required = false) @RequestParam(value = "send_addr", required = false) String send_addr,
                                          @ApiParam(value = "发件人详细地址", required = false) @RequestParam(value = "send_addr_info", required = false) String send_addr_info,
                                          @ApiParam(value = "收件人姓名", required = false) @RequestParam(value = "recive_name", required = false) String recive_name,
                                          @ApiParam(value = "收件人电话", required = false) @RequestParam(value = "recive_phone", required = false) String recive_phone,
                                          @ApiParam(value = "收件人地址", required = false) @RequestParam(value = "recive_addr", required = false) String recive_addr,
                                          @ApiParam(value = "收件人详细地址", required = false) @RequestParam(value = "recive_addr_info", required = false) String recive_addr_info,
                                          @ApiParam(value = "预计发送时间", required = false) @RequestParam(value = "send_time", required = false) Date send_time,
                                          @ApiParam(value = "限时到达时间", required = false) @RequestParam(value = "recive_time", required = false) Date recive_time,
                                          @ApiParam(value = "配送还是自提", required = false) @RequestParam(value = "dispatching_type", required = false) String dispatching_type,
                                          @ApiParam(value = "货物列表json", required = false) @RequestParam(value = "goods", required = false) String goods) {
        try {
            User user = userService.getUser(token);
            OrderCustomer orderCustomer = orderCustomerService.update(id, send_name, send_phone, send_addr, send_addr_info, recive_name, recive_phone, recive_addr, recive_addr_info, dispatching_type, send_time, recive_time, goods);
            orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_EDIT", user.getId());
            return new JsonBean(ErrorCode.SUCCESS, orderCustomer);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "拒绝OrderCustomer")
    @RequestMapping(value = "refuse",method = RequestMethod.POST)
    @ResponseBody
    public JsonBean refuse(@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
                           @ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id){

        try {
            User user = userService.getUser(token);
            OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(fk_order_customer_id);
            orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_REFUSE", user.getId());
            orderCustomerService.updateStatus(orderCustomer.getId(),"ORDER_REFUSE");
            return new JsonBean(ErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "获取指定的OrderCustomer")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<OrderCustomer> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, orderCustomerService.selectByPrimaryKey(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "删除指定的OrderCustomer")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean<OrderCustomer> deleteByPrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id){
        return new JsonBean(ErrorCode.SUCCESS, orderCustomerService.deleteByPrimaryKey(id));
    }
}
