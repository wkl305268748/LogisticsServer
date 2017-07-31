package com.kenny.service.logistics.model.profit;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class ProfitStatus{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_profit_id;
	@ApiModelProperty("pay或者recive")
	private String type;
	@ApiModelProperty("收款或者付款的钱")
	private Integer value;
	@ApiModelProperty("操作的时间")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_profit_id(){
		return fk_profit_id;
	}

	public void setFk_profit_id(Integer fk_profit_id){
		this.fk_profit_id = fk_profit_id;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public Integer getValue(){
		return value;
	}

	public void setValue(Integer value){
		this.value = value;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
