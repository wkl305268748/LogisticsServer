package com.kenny.service.logistics.model.order;

import com.kenny.service.logistics.model.fleet.Car;
import com.kenny.service.logistics.model.fleet.Driver;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderSign;
import com.kenny.service.logistics.model.order.OrderStatus;
import com.kenny.service.logistics.model.order.OrderTaking;
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
    Driver driver;
    //订单司机
    Car car;
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

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
