package com.kenny.service.logistics.model;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("货物信息，用户下单时需要提供货物信息")
public class OrderGoods{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("订单表ID")
	private Integer fk_order_customer_id;
	@ApiModelProperty("货物名称")
	private String name;
	@ApiModelProperty("货物的体积")
	private String size;
	@ApiModelProperty("货物总重量，单位吨")
	private Integer weight;
	@ApiModelProperty("货物备注")
	private String remark;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_order_customer_id(){
		return fk_order_customer_id;
	}

	public void setFk_order_customer_id(Integer fk_order_customer_id){
		this.fk_order_customer_id = fk_order_customer_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSize(){
		return size;
	}

	public void setSize(String size){
		this.size = size;
	}

	public Integer getWeight(){
		return weight;
	}

	public void setWeight(Integer weight){
		this.weight = weight;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
