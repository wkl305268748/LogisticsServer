package com.kenny.service.logistics.model;

/**
 * Created by WKL on 2017-7-2.
 */
public class Profit {
    int id;
    String order_number;
    int fk_order_taking_id;
    int recive;
    int pay;
    boolean is_recive;
    boolean is_pay;
    int profit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public int getFk_order_taking_id() {
        return fk_order_taking_id;
    }

    public void setFk_order_taking_id(int fk_order_taking_id) {
        this.fk_order_taking_id = fk_order_taking_id;
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

    public boolean getIs_recive() {
        return is_recive;
    }

    public void setIs_recive(boolean is_recive) {
        this.is_recive = is_recive;
    }

    public boolean getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(boolean is_pay) {
        this.is_pay = is_pay;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}
