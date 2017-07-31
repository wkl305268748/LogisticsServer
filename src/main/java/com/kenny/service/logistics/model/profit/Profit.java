package com.kenny.service.logistics.model.profit;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class Profit{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_order_customer_id;
	@ApiModelProperty("")
	private String order_number;
	@ApiModelProperty("")
	private Integer recive;
	@ApiModelProperty("")
	private Integer pay;
	@ApiModelProperty("")
	private Integer recive_now;
	@ApiModelProperty("")
	private Integer pay_now;
	@ApiModelProperty("")
	private Boolean is_recive;
	@ApiModelProperty("")
	private Boolean is_pay;
	@ApiModelProperty("")
	private Integer profit;
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

	public String getOrder_number(){
		return order_number;
	}

	public void setOrder_number(String order_number){
		this.order_number = order_number;
	}

	public Integer getRecive(){
		return recive;
	}

	public void setRecive(Integer recive){
		this.recive = recive;
	}

	public Integer getPay(){
		return pay;
	}

	public void setPay(Integer pay){
		this.pay = pay;
	}

	public Integer getRecive_now(){
		return recive_now;
	}

	public void setRecive_now(Integer recive_now){
		this.recive_now = recive_now;
	}

	public Integer getPay_now(){
		return pay_now;
	}

	public void setPay_now(Integer pay_now){
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

	public Integer getProfit(){
		return profit;
	}

	public void setProfit(Integer profit){
		this.profit = profit;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
