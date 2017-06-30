package com.kenny.service.logistics.json.response;

import com.kenny.service.logistics.model.Car;
import com.kenny.service.logistics.model.Driver;
import com.kenny.service.logistics.model.OrderCustomer;
import com.mysql.cj.mysqlx.protobuf.MysqlxCrud;

import java.util.Date;

/**
 * Created by WKL on 2017-6-30.
 */
public class OrderTakingResponse {
    int id;
    OrderCustomer orderCustomer;
    Car car;
    Driver driver;
    int recive;
    int pay;
    String status; //已发货 send、已完成 finish。
    Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderCustomer getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(OrderCustomer orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getRecive() {
        return recive;
    }

    public void setRecive(int recive) {
        this.recive = recive;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
