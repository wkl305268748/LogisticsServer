package com.kenny.service.logistics.controller.order;

import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.order.OrderCustomerService;
import com.kenny.service.logistics.service.order.OrderStatusService;
import com.kenny.service.logistics.service.order.OrderTakingService;
import com.kenny.service.logistics.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.order.OrderSign;
import com.kenny.service.logistics.service.order.OrderSignService;

@Api(value = "/v1/order/sign", description = "定单签收表")
@RequestMapping(value = " /v1/order/sign")
@RestController
public class OrderSignController{
	@Autowired
	private OrderSignService orderSignService;
	@Autowired
	private OrderTakingService orderTakingService;
	@Autowired
	private OrderCustomerService orderCustomerService;
	@Autowired
	UserService userService;
	@Autowired
	OrderStatusService orderStatusService;

	@ApiOperation(value = "增加OrderSign")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<OrderSign> Insert(@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
									  @ApiParam(value = "订单处理表id",required = false)@RequestParam(value = "fk_order_taking_id",required = false)Integer fk_order_taking_id,
	                                  @ApiParam(value = "订单表id",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                                  @ApiParam(value = "签收照片",required = false)@RequestParam(value = "order_img",required = false)String order_img){
		try {
			User user = userService.getUser(token);
			OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(fk_order_customer_id);
			orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_SIGN", user.getId());
			orderCustomerService.updateStatus(orderCustomer.getId(),"ORDER_SIGN");
			return new JsonBean(ErrorCode.SUCCESS, orderSignService.insert(fk_order_taking_id,fk_order_customer_id,order_img));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}

	}

	@ApiOperation(value = "修改指定的OrderSign")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<OrderSign> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
									  @ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
									  @ApiParam(value = "订单处理表id",required = false)@RequestParam(value = "fk_order_taking_id",required = false)Integer fk_order_taking_id,
	                                  @ApiParam(value = "订单表id",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                                  @ApiParam(value = "签收照片",required = false)@RequestParam(value = "order_img",required = false)String order_img){
		try {
			User user = userService.getUser(token);
			OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(fk_order_customer_id);
			orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_EDIT_SIGN", user.getId());
			return new JsonBean(ErrorCode.SUCCESS, orderSignService.update(id,fk_order_taking_id,fk_order_customer_id,order_img));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的OrderSign")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<OrderSign> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, orderSignService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的OrderSign")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<OrderSign>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                    @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, orderSignService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的OrderSign")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, orderSignService.deleteByPrimaryKey(id));
	}

}
