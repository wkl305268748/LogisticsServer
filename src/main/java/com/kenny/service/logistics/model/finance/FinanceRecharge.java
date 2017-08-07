package com.kenny.service.logistics.model.finance;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("用户充值表，记录用户充值信息")
public class FinanceRecharge{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private Integer fk_user_id;
	@ApiModelProperty("进行充值的公司名")
	private String company_out;
	@ApiModelProperty("账户名")
	private String bank_name;
	@ApiModelProperty("银行账号")
	private String bank_number;
	@ApiModelProperty("银行名称")
	private String bank;
	@ApiModelProperty("支行地址")
	private String bank_addr;
	@ApiModelProperty("充值金额")
	private Float money;
	@ApiModelProperty("汇款识别码")
	private String money_code;
	@ApiModelProperty("用途")
	private String money_for;
	@ApiModelProperty("充值状态")
	private String status;
	@ApiModelProperty("操作的管理员")
	private Integer fk_admin_id;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_user_id(){
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id){
		this.fk_user_id = fk_user_id;
	}

	public String getCompany_out(){
		return company_out;
	}

	public void setCompany_out(String company_out){
		this.company_out = company_out;
	}

	public String getBank_name(){
		return bank_name;
	}

	public void setBank_name(String bank_name){
		this.bank_name = bank_name;
	}

	public String getBank_number(){
		return bank_number;
	}

	public void setBank_number(String bank_number){
		this.bank_number = bank_number;
	}

	public String getBank(){
		return bank;
	}

	public void setBank(String bank){
		this.bank = bank;
	}

	public String getBank_addr(){
		return bank_addr;
	}

	public void setBank_addr(String bank_addr){
		this.bank_addr = bank_addr;
	}

	public Float getMoney(){
		return money;
	}

	public void setMoney(Float money){
		this.money = money;
	}

	public String getMoney_code(){
		return money_code;
	}

	public void setMoney_code(String money_code){
		this.money_code = money_code;
	}

	public String getMoney_for(){
		return money_for;
	}

	public void setMoney_for(String money_for){
		this.money_for = money_for;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public Integer getFk_admin_id(){
		return fk_admin_id;
	}

	public void setFk_admin_id(Integer fk_admin_id){
		this.fk_admin_id = fk_admin_id;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
