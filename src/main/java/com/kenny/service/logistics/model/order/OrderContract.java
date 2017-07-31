package com.kenny.service.logistics.model.order;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("合同信息表")
public class OrderContract{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("订单外键")
	private Integer fk_order_customer_id;
	@ApiModelProperty("订单号")
	private String order_number;
	@ApiModelProperty("合同编号")
	private String contract_number;
	@ApiModelProperty("甲方")
	private String aname;
	@ApiModelProperty("乙方")
	private String bname;
	@ApiModelProperty("账户名")
	private String bbank_name;
	@ApiModelProperty("账号")
	private String bbank_number;
	@ApiModelProperty("开户行")
	private String bbank;
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

	public String getContract_number(){
		return contract_number;
	}

	public void setContract_number(String contract_number){
		this.contract_number = contract_number;
	}

	public String getAname(){
		return aname;
	}

	public void setAname(String aname){
		this.aname = aname;
	}

	public String getBname(){
		return bname;
	}

	public void setBname(String bname){
		this.bname = bname;
	}

	public String getBbank_name(){
		return bbank_name;
	}

	public void setBbank_name(String bbank_name){
		this.bbank_name = bbank_name;
	}

	public String getBbank_number(){
		return bbank_number;
	}

	public void setBbank_number(String bbank_number){
		this.bbank_number = bbank_number;
	}

	public String getBbank(){
		return bbank;
	}

	public void setBbank(String bbank){
		this.bbank = bbank;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
