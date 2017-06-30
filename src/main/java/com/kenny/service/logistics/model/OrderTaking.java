package com.kenny.service.logistics.model;

import java.util.Date;

/**
 * Created by WKL on 2017-6-30.
 */
public class OrderTaking {
    int id;
    int fk_order_customer_id;
    int fk_car_id;
    int fk_driver_id;
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

    public int getFk_order_customer_id() {
        return fk_order_customer_id;
    }

    public void setFk_order_customer_id(int fk_order_customer_id) {
        this.fk_order_customer_id = fk_order_customer_id;
    }

    public int getFk_car_id() {
        return fk_car_id;
    }

    public void setFk_car_id(int fk_car_id) {
        this.fk_car_id = fk_car_id;
    }

    public int getFk_driver_id() {
        return fk_driver_id;
    }

    public void setFk_driver_id(int fk_driver_id) {
        this.fk_driver_id = fk_driver_id;
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
