package com.kenny.service.logistics.model.order;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("订单处理表")
public class OrderTaking{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("订单外键")
	private Integer fk_order_customer_id;
	@ApiModelProperty("车辆外键")
	private Integer fk_car_id;
	@ApiModelProperty("司机外键")
	private Integer fk_driver_id;
	@ApiModelProperty("应付账款")
	private Float recive;
	@ApiModelProperty("应收账款")
	private Float pay;
	@ApiModelProperty("关联status表")
	private String status;
	@ApiModelProperty("")
	private Date time;

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

	public Integer getFk_car_id(){
		return fk_car_id;
	}

	public void setFk_car_id(Integer fk_car_id){
		this.fk_car_id = fk_car_id;
	}

	public Integer getFk_driver_id(){
		return fk_driver_id;
	}

	public void setFk_driver_id(Integer fk_driver_id){
		this.fk_driver_id = fk_driver_id;
	}

	public Float getRecive(){
		return recive;
	}

	public void setRecive(Float recive){
		this.recive = recive;
	}

	public Float getPay(){
		return pay;
	}

	public void setPay(Float pay){
		this.pay = pay;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
