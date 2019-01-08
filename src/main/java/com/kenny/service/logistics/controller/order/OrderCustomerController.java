package com.kenny.service.logistics.controller.order;


import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.po.order.Order;
import com.kenny.service.logistics.model.po.order.OrderCustomer;
import com.kenny.service.logistics.model.po.system.Defind;
import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.service.order.*;
import com.kenny.service.logistics.service.user.UserBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Api(value = "/v1/order/customer", description = "客户订单接口模块")
@RequestMapping(value = "/v1/order/customer")
@RestController
public class OrderCustomerController {
    @Autowired
    OrderCustomerService orderCustomerService;
    @Autowired
    OrderStatusService orderStatusService;
    @Autowired
    OrderService orderService;
    @Autowired
    UserBaseService userBaseService;

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
                                          @ApiParam(value = "是否指定物流公司接单", required = true) @RequestParam(value = "is_company", required = true) Boolean is_company,
                                          @ApiParam(value = "指定物流公司", required = true) @RequestParam(value = "fk_want_company_id", required = false) Integer fk_want_company_id,
                                          @ApiParam(value = "货物列表json", required = false) @RequestParam(value = "goods[]", required = false) String goods) {

        try {
            User user = userBaseService.getUserByToken(token);
            //创建订单
            Order order = orderService.insert(user.getId(),is_company,fk_want_company_id);
            OrderCustomer orderCustomer = orderCustomerService.insert(order.getId(),order.getOrder_number(),send_name, send_phone, send_addr, send_addr_info, recive_name, recive_phone, recive_addr, recive_addr_info, dispatching_type, send_time, recive_time,goods);
            orderService.updateStatus(order.getId(),user.getId(), Defind.ORDER_PLACE);
            return new JsonBean(UserErrorCode.SUCCESS, orderCustomer);
        } catch (ErrorCodeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            User user = userBaseService.getUserByToken(token);
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
                           @ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_id",required = false)Integer order_id){

        try {
            User user = userBaseService.getUserByToken(token);
            Order order = orderService.selectByPrimaryKey(order_id);
            orderService.updateStatus(order.getId(),user.getId(),Defind.ORDER_REFUSE);
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
