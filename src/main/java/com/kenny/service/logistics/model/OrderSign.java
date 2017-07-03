package com.kenny.service.logistics.model;


import java.util.Date;

/**
 * Created by WKL on 2017-7-1.
 */
public class OrderSign {
    int id;
    int fk_order_taking_id;
    String order_img;
    Date time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_order_taking_id() {
        return fk_order_taking_id;
    }

    public void setFk_order_taking_id(int fk_order_taking_id) {
        this.fk_order_taking_id = fk_order_taking_id;
    }

    public String getOrder_img() {
        return order_img;
    }

    public void setOrder_img(String order_img) {
        this.order_img = order_img;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
