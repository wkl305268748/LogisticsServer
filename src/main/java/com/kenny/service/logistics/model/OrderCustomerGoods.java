package com.kenny.service.logistics.model;

/**
 * Created by Kenny on 2017/6/29.
 */
public class OrderCustomerGoods {
    int id;
    int fk_goods_id;
    int fk_order_customer_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_goods_id() {
        return fk_goods_id;
    }

    public void setFk_goods_id(int fk_goods_id) {
        this.fk_goods_id = fk_goods_id;
    }

    public int getFk_order_customer_id() {
        return fk_order_customer_id;
    }

    public void setFk_order_customer_id(int fk_order_customer_id) {
        this.fk_order_customer_id = fk_order_customer_id;
    }
}
