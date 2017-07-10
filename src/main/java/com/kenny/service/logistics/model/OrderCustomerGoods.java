package com.kenny.service.logistics.model;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class OrderCustomerGoods{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("货物表外键")
	private Integer fk_goods_id;
	@ApiModelProperty("客户订单表外键")
	private Integer fk_order_customer_id;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_goods_id(){
		return fk_goods_id;
	}

	public void setFk_goods_id(Integer fk_goods_id){
		this.fk_goods_id = fk_goods_id;
	}

	public Integer getFk_order_customer_id(){
		return fk_order_customer_id;
	}

	public void setFk_order_customer_id(Integer fk_order_customer_id){
		this.fk_order_customer_id = fk_order_customer_id;
	}

}
