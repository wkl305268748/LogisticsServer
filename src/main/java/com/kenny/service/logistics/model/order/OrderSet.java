package com.kenny.service.logistics.model.order;

import com.kenny.service.logistics.model.fleet.Car;
import com.kenny.service.logistics.model.fleet.Driver;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderSign;
import com.kenny.service.logistics.model.order.OrderStatus;
import com.kenny.service.logistics.model.order.OrderTaking;

import java.util.List;

/**
 * Created by WKL on 2017-7-16.
 */
public class OrderSet {
    OrderCustomer orderCustomer;
    OrderTaking orderTaking;
    OrderSign orderSign;
    List<OrderStatus> orderStatuses;
    Driver driver;
    Car car;

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
}
