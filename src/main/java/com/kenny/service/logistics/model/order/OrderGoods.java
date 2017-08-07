package com.kenny.service.logistics.model.order;

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
	private Float weight;
	@ApiModelProperty("件数")
	private Integer number;
	@ApiModelProperty("运费")
	private Float freight;
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

	public Float getWeight(){
		return weight;
	}

	public void setWeight(Float weight){
		this.weight = weight;
	}

	public Integer getNumber(){
		return number;
	}

	public void setNumber(Integer number){
		this.number = number;
	}

	public Float getFreight(){
		return freight;
	}

	public void setFreight(Float freight){
		this.freight = freight;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
