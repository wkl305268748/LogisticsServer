package com.kenny.service.logistics.model.order;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("定单签收表")
public class OrderSign{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("订单表id")
	private Integer fk_order_id;
	@ApiModelProperty("签收照片")
	private String order_img;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_order_id() {
		return fk_order_id;
	}

	public void setFk_order_id(Integer fk_order_id) {
		this.fk_order_id = fk_order_id;
	}

	public String getOrder_img(){
		return order_img;
	}

	public void setOrder_img(String order_img){
		this.order_img = order_img;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
