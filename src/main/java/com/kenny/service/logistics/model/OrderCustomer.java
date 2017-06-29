package com.kenny.service.logistics.model;

import java.util.Date;

/**
 * Created by Kenny on 2017/6/29.
 */
public class OrderCustomer {
    int id;
    String serial_number;
    String order_number;
    String goods_type;
    String send_name;
    String send_phone;
    String send_addr;
    String send_addr_info;
    String revice_name;
    String recive_phone;
    String revice_addr;
    String revice_addr_info;
    Date send_time;
    Date revice_time;
    String dispatching_type;
    Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getSend_phone() {
        return send_phone;
    }

    public void setSend_phone(String send_phone) {
        this.send_phone = send_phone;
    }

    public String getSend_addr() {
        return send_addr;
    }

    public void setSend_addr(String send_addr) {
        this.send_addr = send_addr;
    }

    public String getRevice_name() {
        return revice_name;
    }

    public void setRevice_name(String revice_name) {
        this.revice_name = revice_name;
    }

    public String getRecive_phone() {
        return recive_phone;
    }

    public void setRecive_phone(String recive_phone) {
        this.recive_phone = recive_phone;
    }

    public String getRevice_addr() {
        return revice_addr;
    }

    public void setRevice_addr(String revice_addr) {
        this.revice_addr = revice_addr;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getRevice_time() {
        return revice_time;
    }

    public void setRevice_time(Date revice_time) {
        this.revice_time = revice_time;
    }

    public String getSend_addr_info() {
        return send_addr_info;
    }

    public void setSend_addr_info(String send_addr_info) {
        this.send_addr_info = send_addr_info;
    }

    public String getRevice_addr_info() {
        return revice_addr_info;
    }

    public void setRevice_addr_info(String revice_addr_info) {
        this.revice_addr_info = revice_addr_info;
    }

    public String getDispatching_type() {
        return dispatching_type;
    }

    public void setDispatching_type(String dispatching_type) {
        this.dispatching_type = dispatching_type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
