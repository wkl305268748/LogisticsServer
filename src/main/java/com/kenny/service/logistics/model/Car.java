package com.kenny.service.logistics.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

/**
 * Created by WKL on 2017-6-30.
 */
public class Car {
    int id;
    String car_plate;
    String car_type;
    String car_resource;
    String remark;
    Date time;
    Boolean visible;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getCar_resource() {
        return car_resource;
    }

    public void setCar_resource(String car_resource) {
        this.car_resource = car_resource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
