package com.kenny.service.logistics.model.order;

import com.kenny.service.logistics.model.fleet.FleetCar;
import com.kenny.service.logistics.model.fleet.FleetDriver;
import com.kenny.service.logistics.model.profit.Profit;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;

import java.util.List;

/**
 * Created by WKL on 2017-7-16.
 */
public class OrderSet {
    //订单信息
    OrderCustomer orderCustomer;
    //货物信息
    List<OrderGoods> orderGoods;
    //派单信息
    OrderTaking orderTaking;
    //签收信息
    OrderSign orderSign;
    //合同信息
    OrderContract orderContract;
    //订单操作状态
    List<OrderStatus> orderStatuses;
    //订单车辆
    FleetDriver fleetDriver;
    //订单司机
    FleetCar fleetCar;
    //订单用户
    User user;
    //订单用户
    UserInfo userInfo;
    //财务信息
    Profit profit;

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public OrderTaking getOrderTaking() {
        return orderTaking;
    }

    public void setOrderTaking(OrderTaking orderTaking) {
        this.orderTaking = orderTaking;
    }

    public OrderSign getOrderSign() {
        return orderSign;
    }

    public void setOrderSign(OrderSign orderSign) {
        this.orderSign = orderSign;
    }

    public List<OrderStatus> getOrderStatuses() {
        return orderStatuses;
    }

    public void setOrderStatuses(List<OrderStatus> orderStatuses) {
        this.orderStatuses = orderStatuses;
    }

    public FleetDriver getFleetDriver() {
        return fleetDriver;
    }

    public void setFleetDriver(FleetDriver fleetDriver) {
        this.fleetDriver = fleetDriver;
    }

    public FleetCar getFleetCar() {
        return fleetCar;
    }

    public void setFleetCar(FleetCar fleetCar) {
        this.fleetCar = fleetCar;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public Profit getProfit() {
        return profit;
    }

    public void setProfit(Profit profit) {
        this.profit = profit;
    }

    public OrderContract getOrderContract() {
        return orderContract;
    }

    public void setOrderContract(OrderContract orderContract) {
        this.orderContract = orderContract;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
