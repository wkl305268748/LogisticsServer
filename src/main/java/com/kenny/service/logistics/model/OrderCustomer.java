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
    String recive_name;
    String recive_phone;
    String recive_addr;
    String recive_addr_info;
    Date send_time;
    Date recive_time;
    String dispatching_type;//dispatching / since
    String status;//wait待派车、ignor已拒绝、success已派车
    String remark;
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

    public String getSend_addr_info() {
        return send_addr_info;
    }

    public void setSend_addr_info(String send_addr_info) {
        this.send_addr_info = send_addr_info;
    }

    public String getRecive_name() {
        return recive_name;
    }

    public void setRecive_name(String recive_name) {
        this.recive_name = recive_name;
    }

    public String getRecive_phone() {
        return recive_phone;
    }

    public void setRecive_phone(String recive_phone) {
        this.recive_phone = recive_phone;
    }

    public String getRecive_addr() {
        return recive_addr;
    }

    public void setRecive_addr(String recive_addr) {
        this.recive_addr = recive_addr;
    }

    public String getRecive_addr_info() {
        return recive_addr_info;
    }

    public void setRecive_addr_info(String recive_addr_info) {
        this.recive_addr_info = recive_addr_info;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Date getRecive_time() {
        return recive_time;
    }

    public void setRecive_time(Date recive_time) {
        this.recive_time = recive_time;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
