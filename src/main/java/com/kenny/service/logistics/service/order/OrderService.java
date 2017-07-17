package com.kenny.service.logistics.service.order;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderSet;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.order.OrderCustomerMapper;
import com.kenny.service.logistics.mapper.order.OrderSignMapper;
import com.kenny.service.logistics.mapper.order.OrderStatusMapper;
import com.kenny.service.logistics.mapper.order.OrderTakingMapper;
import com.kenny.service.logistics.model.order.OrderStatus;
import com.kenny.service.logistics.service.fleet.CarService;
import com.kenny.service.logistics.service.fleet.DriverService;
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
	private DriverService driverService;
	@Autowired
	private CarService carService;

	public PageResponse<OrderSet> selectPageByStatus(int offset, int pageSize, String status) throws ErrorCodeException {
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPageByStatus(offset,pageSize,status);
		int count = orderCustomerMapper.countByStatus(status);
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			OrderSet orderSet = new OrderSet();
			orderSet.setOrderCustomer(orderCustomer);
			orderSet.setOrderStatuses(orderStatusMapper.selectByOrderNumber(orderCustomer.getOrder_number()));
			orderSet.setOrderTaking(orderTakingMapper.selectByOrderCustomer(orderCustomer.getId()));
			orderSet.setOrderSign(orderSignMapper.selectByOrderCustomer(orderCustomer.getId()));
			if(orderSet.getOrderTaking()!=null) {
				orderSet.setCar(carService.selectByPrimaryKey(orderSet.getOrderTaking().getFk_car_id()));
				orderSet.setDriver(driverService.selectByPrimaryKey(orderSet.getOrderTaking().getFk_driver_id()));
			}
			orderSets.add(orderSet);
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}


	public PageResponse<OrderSet> selectPage(int offset, int pageSize) throws ErrorCodeException {
		List<OrderCustomer> orderCustomers = orderCustomerMapper.selectPage(offset,pageSize);
		int count = orderCustomerMapper.count();
		List<OrderSet> orderSets = new ArrayList<>();
		for(OrderCustomer orderCustomer : orderCustomers){
			OrderSet orderSet = new OrderSet();
			orderSet.setOrderCustomer(orderCustomer);
			orderSet.setOrderStatuses(orderStatusMapper.selectByOrderNumber(orderCustomer.getOrder_number()));
			orderSet.setOrderTaking(orderTakingMapper.selectByOrderCustomer(orderCustomer.getId()));
			orderSet.setOrderSign(orderSignMapper.selectByOrderCustomer(orderCustomer.getId()));
			if(orderSet.getOrderTaking()!=null) {
				orderSet.setCar(carService.selectByPrimaryKey(orderSet.getOrderTaking().getFk_car_id()));
				orderSet.setDriver(driverService.selectByPrimaryKey(orderSet.getOrderTaking().getFk_driver_id()));
			}
			orderSets.add(orderSet);
		}
		PageResponse<OrderSet> response = new PageResponse<>();
		response.setTotal(count);
		response.setItem(orderSets);
		response.setPageSize(pageSize);
		response.setOffset(offset);
		return response;
	}
}
