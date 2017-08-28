package com.kenny.service.logistics.model.order;

import com.kenny.service.logistics.model.fleet.FleetCar;
import com.kenny.service.logistics.model.fleet.FleetDriver;
import com.kenny.service.logistics.model.profit.Profit;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.model.user.UserSet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by WKL on 2017-7-16.
 */
@ApiModel("订单综合信息")
public class OrderSet {
    @ApiModelProperty("订单信息")
    Order order;
    @ApiModelProperty("下单信息")
    OrderCustomer orderCustomer;
    @ApiModelProperty("货物信息")
    List<OrderGoods> orderGoods;
    @ApiModelProperty("派单信息")
    OrderTaking orderTaking;
    @ApiModelProperty("签收信息")
    OrderSign orderSign;
    @ApiModelProperty("合同信息")
    OrderContract orderContract;
    @ApiModelProperty("订单操作状态")
    List<OrderStatus> orderStatuses;
    @ApiModelProperty("订单司机")
    FleetDriver fleetDriver;
    @ApiModelProperty("订单车辆")
    FleetCar fleetCar;
    @ApiModelProperty("下单用户信息")
    UserSet customer;
    @ApiModelProperty("指定接单物流公司信息")
    UserSet wantCompany;
    @ApiModelProperty("接单物流公司信息")
    UserSet company;
    @ApiModelProperty("财务信息")
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

    public FleetDriver getFleetDriver() {
        return fleetDriver;
    }

    public void setFleetDriver(FleetDriver fleetDriver) {
        this.fleetDriver = fleetDriver;
    }

    public FleetCar getFleetCar() {
        return fleetCar;
    }

    public void setFleetCar(FleetCar fleetCar) {
        this.fleetCar = fleetCar;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UserSet getCustomer() {
        return customer;
    }

    public void setCustomer(UserSet customer) {
        this.customer = customer;
    }

    public UserSet getWantCompany() {
        return wantCompany;
    }

    public void setWantCompany(UserSet wantCompany) {
        this.wantCompany = wantCompany;
    }

    public UserSet getCompany() {
        return company;
    }

    public void setCompany(UserSet company) {
        this.company = company;
    }
}
