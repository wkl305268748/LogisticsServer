package com.kenny.service.logistics.model.order;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("记录订单状态变更")
public class OrderStatus{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("订单编号，随机生成")
	private String order_number;
	@ApiModelProperty("订单当前状态信息")
	private String status;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getOrder_number(){
		return order_number;
	}

	public void setOrder_number(String order_number){
		this.order_number = order_number;
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
