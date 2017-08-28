package com.kenny.service.logistics.controller.order;

import com.kenny.service.logistics.model.fleet.FleetDriver;
import com.kenny.service.logistics.model.order.Order;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderSet;
import com.kenny.service.logistics.model.system.Defind;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.service.fleet.FleetDriverService;
import com.kenny.service.logistics.service.order.*;
import com.kenny.service.logistics.service.profit.ProfitService;
import com.kenny.service.logistics.service.user.UserBaseService;
import com.kenny.service.logistics.service.user.UserManagerService;
import com.kenny.service.logistics.service.user.UserService;
import com.kenny.service.logistics.service.util.SmsSendService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.order.OrderTaking;

@Api(value = "/v1/order/taking", description = "订单处理表")
@RequestMapping(value = "/v1/order/taking")
@RestController
public class OrderTakingController{
	@Autowired
	private OrderTakingService orderTakingService;
	@Autowired
	private OrderCustomerService orderCustomerService;
	@Autowired
	UserBaseService userBaseService;
	@Autowired
	OrderStatusService orderStatusService;
	@Autowired
	ProfitService profitService;
	@Autowired
    FleetDriverService fleetDriverService;
	@Autowired
	SmsSendService smsSendService;
	@Autowired
	OrderContractService orderContractService;
	@Autowired
	UserManagerService userManagerService;
	@Autowired
	OrderService orderService;


	@ApiOperation(value = "增加OrderTaking")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public JsonBean<OrderTaking> insert(@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
										@ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_id",required = false)Integer fk_order_id,
	                                    @ApiParam(value = "车辆外键",required = false)@RequestParam(value = "fk_car_id",required = false)Integer fk_car_id,
	                                    @ApiParam(value = "司机外键",required = false)@RequestParam(value = "fk_driver_id",required = false)Integer fk_driver_id,
										@ApiParam(value = "运费", required = true) @RequestParam(value = "freight", required = true) Float freight,
										@ApiParam(value = "保险", required = true) @RequestParam(value = "safes", required = true) Float safes,
										@ApiParam(value = "应付账款",required = false)@RequestParam(value = "recive",required = false)Float recive,
	                                    @ApiParam(value = "应收账款",required = false)@RequestParam(value = "pay",required = false)Float pay,
										@ApiParam(value = "货物列表json", required = false) @RequestParam(value = "goods[]", required = false) String goods){
		try {
			User user = userBaseService.getUserByToken(token);
			OrderSet orderSet = orderService.selectByPrimaryKeyEx(fk_order_id);
			orderService.updateStatus(orderSet.getOrder().getId(),user.getId(), Defind.ORDER_TAKING);
			//增加财务信息
			profitService.insert(orderSet.getOrder().getId(),orderSet.getOrder().getOrder_number(),recive,pay,user.getId());
			//增加合同信息
			orderContractService.create(orderSet.getOrder().getId(),orderSet.getOrder().getOrder_number(),orderSet.getOrderCustomer().getSend_name(),user.getId());
			OrderTaking orderTaking = orderTakingService.taking(orderSet.getOrder().getId(),fk_car_id,fk_driver_id,freight,safes,recive,pay);
			//向司机发送短信
			FleetDriver fleetDriver = fleetDriverService.selectByPrimaryKey(fk_driver_id);
			if(fleetDriver.getIs_sms())
				smsSendService.OrderToDriver(fleetDriver.getPhone(),orderSet.getOrder().getOrder_number(),orderSet.getOrderCustomer().getSend_addr(),orderSet.getOrderCustomer().getRecive_addr());

			return new JsonBean(ErrorCode.SUCCESS, orderTaking);
		} catch (ErrorCodeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "修改指定的OrderTaking")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<OrderTaking> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
										@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
	                                    @ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_id",required = false)Integer fk_order_id,
	                                    @ApiParam(value = "车辆外键",required = false)@RequestParam(value = "fk_car_id",required = false)Integer fk_car_id,
	                                    @ApiParam(value = "司机外键",required = false)@RequestParam(value = "fk_driver_id",required = false)Integer fk_driver_id,
	                                    @ApiParam(value = "应付账款",required = false)@RequestParam(value = "recive",required = false)Float recive,
	                                    @ApiParam(value = "应收账款",required = false)@RequestParam(value = "pay",required = false)Float pay){
		try {
			User user = userBaseService.getUserByToken(token);
			Order order = orderService.selectByPrimaryKey(fk_order_id);
			orderService.addController(order.getId(),user.getId(),Defind.ORDER_EDIT_TAKING);
			return new JsonBean(ErrorCode.SUCCESS, orderTakingService.update(id,fk_order_id,fk_car_id,fk_driver_id,recive,pay));
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
