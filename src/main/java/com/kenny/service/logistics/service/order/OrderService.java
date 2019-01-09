package com.kenny.service.logistics.service.order;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.mapper.fleet.FleetCarMapper;
import com.kenny.service.logistics.mapper.fleet.FleetDriverMapper;
import com.kenny.service.logistics.mapper.order.*;
import com.kenny.service.logistics.mapper.profit.ProfitMapper;
import com.kenny.service.logistics.model.po.order.Order;
import com.kenny.service.logistics.model.po.order.OrderSet;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.model.po.order.OrderStatus;
import com.kenny.service.logistics.model.po.system.Defind;
import com.kenny.service.logistics.service.user.UserManagerService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
	@Autowired
	private OrderSignMapper orderSignMapper;
	@Autowired
	private OrderCustomerMapper orderCustomerMapper;
	@Autowired
	private OrderTakingMapper orderTakingMapper;
	@Autowired
	private FleetDriverMapper fleetDriverMapper;
	@Autowired
	private FleetCarMapper fleetCarMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	@Autowired
	private ProfitMapper profitMapper;
	@Autowired
	private OrderContractMapper orderContractMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderPlusMapper orderPlusMapper;
	@Autowired
	private OrderStatusMapper orderStatusMapper;
	@Autowired
	private UserManagerService userManagerService;

	public Order insert(Integer fk_customer_id,Boolean is_company,Integer fk_want_company_id){
		Order order = new Order();
		order.setOrder_number(createOrderNumber());
		order.setSerial_number(createSerialNumber());
		order.setFk_customer_id(fk_customer_id);
		order.setIs_company(is_company);
		order.setFk_want_company_id(fk_want_company_id);
		order.setStatus(Defind.ORDER_PLACE);
		order.setTime(new Date());
		orderMapper.insert(order);
		return order;
	}

	//修改订单状态
	public Order updateStatus(Integer id,Integer user_id, String status) throws ErrorCodeException {
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order == null) {
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		order.setStatus(status);
		switch (status){
			case Defind.ORDER_TAKING:
				order.setFk_company_id(user_id);
				break;
		}
		orderMapper.update(order);
		//更新状态信息
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder_number(order.getOrder_number());
		orderStatus.setStatus(status);
		orderStatus.setFk_user_id(user_id);
		orderStatus.setTime(new Date());
		orderStatusMapper.insert(orderStatus);
		return order;
	}

	//增加操作信息
	public Order addController(Integer id,Integer user_id, String status) throws ErrorCodeException {
		Order order = orderMapper.selectByPrimaryKey(id);
		if (order == null) {
			throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
		}
		//更新状态信息
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setOrder_number(order.getOrder_number());
		orderStatus.setStatus(status);
		orderStatus.setFk_user_id(user_id);
		orderStatus.setTime(new Date());
		orderStatusMapper.insert(orderStatus);
		return order;
	}

	/**
	 * 随机创建订单流水号
	 *
	 * @return
	 */
	private String createSerialNumber() {
		Random random = new Random(System.currentTimeMillis());
		String number = new Date().getTime() + "";
		for (int i = 0; i < 5; i++)
			number = number + random.nextInt(9);
		return number;
	}

	/**
	 * 随机创建订单号
	 *
	 * @return
	 */
	private String createOrderNumber() {
		Random random = new Random(System.currentTimeMillis());
		SimpleDateFormat myFmt = new SimpleDateFormat("yyyyMMddHHmm");
		String number = myFmt.format(new Date());
		for (int i = 0; i < 5; i++)
			number = number + random.nextInt(9);
		return number;
	}

	/**
	 * 根据状态查询单据
	 * @param offset
	 * @param pageSize
	 * @param status
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByStatus(int offset, int pageSize, String status) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPageByStatus(offset,pageSize,status);
		int count = orderMapper.countByStatus(status);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询用户下单列表
	 * 多条件查询
	 * 1.根据订单号模糊查询
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByCustomer(int offset,
													   int pageSize,
													   Integer user_id,
													   String keyword) throws ErrorCodeException {
		QueryWrapper<Order> wrapper = new QueryWrapper();
		Page<Order> page = new Page<>(offset, pageSize);
		wrapper.eq("fk_customer_id",user_id)
				.orderByDesc("time");
		if(StringUtils.isNotEmpty(keyword)){
			wrapper.like("order_number",keyword);
		}

		IPage<Order> orders = orderPlusMapper.selectPage(page,wrapper);

		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders.getRecords()){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal((int)orders.getTotal());
		response.setItem(orderSets);
		response.setPageSize((int)orders.getPages());
		response.setOffset((int)orders.getCurrent());
		return response;
	}

	/**
	 * 查询物流公司订单列表
	 * 包括已经接单的和客户意向的
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByCompany(int offset, int pageSize, Integer user_id,String keyword) throws ErrorCodeException {
		QueryWrapper<Order> wrapper = new QueryWrapper();

		wrapper.eq("fk_company_id",user_id)
				.or()
				.eq("fk_want_company_id",user_id)
				.orderByDesc("time");
		if(StringUtils.isNotEmpty(keyword)){
			wrapper.like("order_number",keyword);
		}


		IPage<Order> orders = orderPlusMapper.selectPage(new Page(offset/pageSize+1, pageSize, true),wrapper);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders.getRecords()){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal((int)orders.getTotal());
		response.setItem(orderSets);
		response.setPageSize((int)orders.getPages());
		response.setOffset((int)orders.getCurrent());
		return response;
	}

	/**
	 * 查询物流公司能够接单列表
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByWantCompany(int offset, int pageSize, Integer user_id) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPageByCompanyAndStatus(offset,pageSize,user_id,Defind.ORDER_PLACE);
		int count = orderMapper.countByCompanyAndStatus(user_id,Defind.ORDER_PLACE);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询司机的订单列表
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByDriver(int offset, int pageSize, Integer user_id) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPageByDriver(offset,pageSize,user_id);
		int count = orderMapper.countByDriver(user_id);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询司机的订单列表
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByDriverAndStatus(int offset, int pageSize, Integer user_id,String status) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPageByDriverAndStatus(offset,pageSize,user_id,status);
		int count = orderMapper.countByDriverAndStatus(user_id,status);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询未结单的开放订单
	 * @param offset
	 * @param pageSize
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByOpenCompany(int offset, int pageSize) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPageByOpenCompany(offset,pageSize,Defind.ORDER_PLACE);
		int count = orderMapper.countByOpenCompany(Defind.ORDER_PLACE);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询用户下单列表，根据状态查询
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByCustomerAndStatus(int offset, int pageSize, Integer user_id,String status) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPageByCustomerAndStatus(offset,pageSize,user_id,status);
		int count = orderMapper.countByCustomerAndStatus(user_id,status);
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}


	/**
	 * 查询所有的单据
	 * @param offset
	 * @param pageSize
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPage(int offset, int pageSize) throws ErrorCodeException {
		List<Order> orders = orderMapper.selectPage(offset,pageSize);
		int count = orderMapper.count();
		List<OrderSet> orderSets = new ArrayList<>();
		for(Order order : orders){
			orderSets.add(selectByOrder(order));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	public OrderSet selectByPrimaryKeyEx(int order_id) throws ErrorCodeException {
		Order order = orderMapper.selectByPrimaryKey(order_id);
		return selectByOrder(order);
	}

	public Order selectByPrimaryKey(int order_id) throws ErrorCodeException {
		Order order = orderMapper.selectByPrimaryKey(order_id);
		return order;
	}

	public void deleteByPrimaryKey(int order_id){
		orderMapper.deleteByPrimaryKey(order_id);
		orderCustomerMapper.deleteByOrderId(order_id);
		orderTakingMapper.deleteByOrderId(order_id);
		orderSignMapper.deleteByOrderId(order_id);
		orderContractMapper.deleteByOrderId(order_id);
		orderGoodsMapper.deleteByOrderId(order_id);
		orderStatusMapper.deleteByOrderId(order_id);
		profitMapper.deleteByOrderId(order_id);
	}

	//核心操作，通过订单查询所有东西
	private OrderSet selectByOrder(Order order) throws ErrorCodeException {
		OrderSet orderSet = new OrderSet();
		orderSet.setOrder(order);
		orderSet.setOrderCustomer(orderCustomerMapper.selectByOrderId(order.getId()));
		orderSet.setOrderStatuses(orderStatusMapper.selectByOrderId(order.getId()));
		orderSet.setOrderTaking(orderTakingMapper.selectByOrderId(order.getId()));
		orderSet.setOrderSign(orderSignMapper.selectByOrderId(order.getId()));
		orderSet.setCustomer(userManagerService.selectByPrimaryKeyEx(order.getFk_customer_id()));
		if(order.getIs_company())
			orderSet.setWantCompany(userManagerService.selectByPrimaryKeyEx(order.getFk_want_company_id()));
		orderSet.setOrderGoods(orderGoodsMapper.selectByOrderId(order.getId()));
		if(orderSet.getOrderTaking() != null) {
			orderSet.setCompany(userManagerService.selectByPrimaryKeyEx(order.getFk_company_id()));
			orderSet.setOrderContract(orderContractMapper.selectByOrderId(order.getId()));
			orderSet.setFleetCar(fleetCarMapper.selectByPrimaryKey(orderSet.getOrderTaking().getFk_car_id()));
			orderSet.setFleetDriver(fleetDriverMapper.selectByPrimaryKey(orderSet.getOrderTaking().getFk_driver_id()));
			orderSet.setProfit(profitMapper.selectByOrderId(order.getId()));
		}
		return orderSet;
	}
}
