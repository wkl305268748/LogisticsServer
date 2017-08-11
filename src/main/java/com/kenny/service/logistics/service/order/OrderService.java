package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.mapper.fleet.FleetCarMapper;
import com.kenny.service.logistics.mapper.fleet.FleetDriverMapper;
import com.kenny.service.logistics.mapper.order.*;
import com.kenny.service.logistics.mapper.profit.ProfitMapper;
import com.kenny.service.logistics.mapper.user.UserInfoMapper;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderSet;
import com.kenny.service.logistics.json.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderSignMapper orderSignMapper;
	@Autowired
	private OrderCustomerMapper orderCustomerMapper;
	@Autowired
	private OrderTakingMapper orderTakingMapper;
	@Autowired
	private OrderStatusMapper orderStatusMapper;
	@Autowired
	private FleetDriverMapper fleetDriverMapper;
	@Autowired
	private FleetCarMapper fleetCarMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OrderGoodsMapper orderGoodsMapper;
	@Autowired
	private ProfitMapper profitMapper;
	@Autowired
	private OrderContractMapper orderContractMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;


	/**
	 * 根据状态查询单据
	 * @param offset
	 * @param pageSize
	 * @param status
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByStatus(int offset, int pageSize, String status) throws ErrorCodeException {
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByStatus(offset,pageSize,status);
		int count = orderCustomerMapper.countByStatus(status);
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			orderSets.add(selectByCustomer(orderCustomer));
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
	 * @param offset
	 * @param pageSize
	 * @param user_id
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByCustomer(int offset, int pageSize, Integer user_id) throws ErrorCodeException {
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByUser(offset,pageSize,user_id);
		int count = orderCustomerMapper.countByUser(user_id);
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			orderSets.add(selectByCustomer(orderCustomer));
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
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByUserAndStatus(offset,pageSize,user_id,status);
		int count = orderCustomerMapper.countByUserAndStatus(user_id,status);
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			orderSets.add(selectByCustomer(orderCustomer));
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
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPage(offset,pageSize);
		int count = orderCustomerMapper.count();
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			orderSets.add(selectByCustomer(orderCustomer));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询指定用户所有的单据
	 * @param offset
	 * @param pageSize
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByUserId(int offset, int pageSize,int user_id) throws ErrorCodeException {
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByUser(offset,pageSize,user_id);
		int count = orderCustomerMapper.countByUser(user_id);
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			orderSets.add(selectByCustomer(orderCustomer));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	/**
	 * 查询客户用户类型所有的单据
	 * @param offset
	 * @param pageSize
	 * @return
	 * @throws ErrorCodeException
	 */
	public PageResponse<OrderSet> selectPageByCustomer(int offset, int pageSize) throws ErrorCodeException {
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByUserType(offset,pageSize,"customer");
		int count = orderCustomerMapper.countByUserType("customer");
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			orderSets.add(selectByCustomer(orderCustomer));
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}

	public OrderSet selectByPrimaryKey(int order_id) throws ErrorCodeException {
		OrderCustomer orderCustomer = orderCustomerMapper.selectByPrimaryKey(order_id);
		return selectByCustomer(orderCustomer);
	}

	public void deleteByPrimaryKey(int order_id){
		orderCustomerMapper.deleteByPrimaryKey(order_id);
		orderTakingMapper.deleteByOrderCustomer(order_id);
		orderSignMapper.deleteByOrderCustomer(order_id);
		orderContractMapper.deleteByOrderCustomer(order_id);
		orderGoodsMapper.deleteByOrderCustomer(order_id);
		orderStatusMapper.deleteByOrderCustomer(order_id);
	}

	//核心操作，通过订单查询所有东西
	private OrderSet selectByCustomer(OrderCustomer orderCustomer) throws ErrorCodeException {
		OrderSet orderSet = new OrderSet();
		orderSet.setOrderCustomer(orderCustomer);
		orderSet.setOrderStatuses(orderStatusMapper.selectByOrderId(orderCustomer.getId()));
		orderSet.setOrderTaking(orderTakingMapper.selectByOrderCustomer(orderCustomer.getId()));
		orderSet.setOrderSign(orderSignMapper.selectByOrderCustomer(orderCustomer.getId()));
		orderSet.setUser(userMapper.selectByPrimaryKey(orderCustomer.getFk_user_id()));
		orderSet.setUserInfo(userInfoMapper.selectByUserId(orderCustomer.getFk_user_id()));
		orderSet.setOrderGoods(orderGoodsMapper.selectByOrderCustomerId(orderCustomer.getId()));
		if(orderSet.getOrderTaking() != null) {
			orderSet.setOrderContract(orderContractMapper.selectByOrderCustomerId(orderCustomer.getId()));
			orderSet.setFleetCar(fleetCarMapper.selectByPrimaryKey(orderSet.getOrderTaking().getFk_car_id()));
			orderSet.setFleetDriver(fleetDriverMapper.selectByPrimaryKey(orderSet.getOrderTaking().getFk_driver_id()));
			orderSet.setProfit(profitMapper.selectPageByOrderCustomer(orderCustomer.getId()));
		}
		return orderSet;
	}
}
