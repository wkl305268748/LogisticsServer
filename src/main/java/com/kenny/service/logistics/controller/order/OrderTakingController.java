package com.kenny.service.logistics.controller.order;

import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.order.OrderCustomerService;
import com.kenny.service.logistics.service.order.OrderStatusService;
import com.kenny.service.logistics.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.order.OrderTaking;
import com.kenny.service.logistics.service.order.OrderTakingService;

@Api(value = "/v1/order/taking", description = "订单处理表")
@RequestMapping(value = " /v1/order/taking")
@RestController
public class OrderTakingController{
	@Autowired
	private OrderTakingService orderTakingService;
	@Autowired
	private OrderCustomerService orderCustomerService;
	@Autowired
	UserService userService;
	@Autowired
	OrderStatusService orderStatusService;

	@ApiOperation(value = "增加OrderTaking")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<OrderTaking> insert(@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
										@ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                                    @ApiParam(value = "车辆外键",required = false)@RequestParam(value = "fk_car_id",required = false)Integer fk_car_id,
	                                    @ApiParam(value = "司机外键",required = false)@RequestParam(value = "fk_driver_id",required = false)Integer fk_driver_id,
	                                    @ApiParam(value = "应付账款",required = false)@RequestParam(value = "recive",required = false)Integer recive,
	                                    @ApiParam(value = "应收账款",required = false)@RequestParam(value = "pay",required = false)Integer pay){
		try {
			User user = userService.getUser(token);
			OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(fk_order_customer_id);
			orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_TAKING", user.getId());
			orderCustomerService.updateStatus(orderCustomer.getId(),"ORDER_TAKING");
			return new JsonBean(ErrorCode.SUCCESS, orderTakingService.taking(fk_order_customer_id,fk_car_id,fk_driver_id,recive,pay));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的OrderTaking")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<OrderTaking> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
										@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
	                                    @ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                                    @ApiParam(value = "车辆外键",required = false)@RequestParam(value = "fk_car_id",required = false)Integer fk_car_id,
	                                    @ApiParam(value = "司机外键",required = false)@RequestParam(value = "fk_driver_id",required = false)Integer fk_driver_id,
	                                    @ApiParam(value = "应付账款",required = false)@RequestParam(value = "recive",required = false)Integer recive,
	                                    @ApiParam(value = "应收账款",required = false)@RequestParam(value = "pay",required = false)Integer pay){
		try {
			User user = userService.getUser(token);
			OrderCustomer orderCustomer = orderCustomerService.selectByPrimaryKey(fk_order_customer_id);
			orderStatusService.insert(orderCustomer.getOrder_number(), "ORDER_EDIT_TAKING", user.getId());
			return new JsonBean(ErrorCode.SUCCESS, orderTakingService.update(id,fk_order_customer_id,fk_car_id,fk_driver_id,recive,pay));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的OrderTaking")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<OrderTaking> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, orderTakingService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的OrderTaking")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<OrderTaking>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                      @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, orderTakingService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的OrderTaking")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, orderTakingService.deleteByPrimaryKey(id));
	}

}
