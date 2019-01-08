package com.kenny.service.logistics.model.po.order;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("订单状态码信息")
public class OrderStatusValue{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String value;
	@ApiModelProperty("")
	private String remark;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

}
