package com.kenny.service.logistics.model.po.profit;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class Profit{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_order_id;
	@ApiModelProperty("")
	private String order_number;
	@ApiModelProperty("")
	private Float recive;
	@ApiModelProperty("")
	private Float pay;
	@ApiModelProperty("")
	private Float recive_now;
	@ApiModelProperty("")
	private Float pay_now;
	@ApiModelProperty("")
	private Boolean is_recive;
	@ApiModelProperty("")
	private Boolean is_pay;
	@ApiModelProperty("")
	private Float profit;
	@ApiModelProperty("")
	private Integer belong_user_id;
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

	public String getOrder_number(){
		return order_number;
	}

	public void setOrder_number(String order_number){
		this.order_number = order_number;
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

	public Float getRecive_now(){
		return recive_now;
	}

	public void setRecive_now(Float recive_now){
		this.recive_now = recive_now;
	}

	public Float getPay_now(){
		return pay_now;
	}

	public void setPay_now(Float pay_now){
		this.pay_now = pay_now;
	}

	public Boolean getIs_recive(){
		return is_recive;
	}

	public void setIs_recive(Boolean is_recive){
		this.is_recive = is_recive;
	}

	public Boolean getIs_pay(){
		return is_pay;
	}

	public void setIs_pay(Boolean is_pay){
		this.is_pay = is_pay;
	}

	public Float getProfit(){
		return profit;
	}

	public void setProfit(Float profit){
		this.profit = profit;
	}

	public Integer getBelong_user_id(){
		return belong_user_id;
	}

	public void setBelong_user_id(Integer belong_user_id){
		this.belong_user_id = belong_user_id;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
