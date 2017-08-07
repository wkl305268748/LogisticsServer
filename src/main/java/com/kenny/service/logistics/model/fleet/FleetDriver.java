package com.kenny.service.logistics.model.fleet;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class FleetDriver{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("姓名")
	private String name;
	@ApiModelProperty("")
	private String sex;
	@ApiModelProperty("手机号")
	private String phone;
	@ApiModelProperty("司机用户表外键")
	private Integer fk_user_id;
	@ApiModelProperty("是否短信通知司机")
	private Boolean is_sms;
	@ApiModelProperty("身份证号码")
	private String idcard;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("籍贯")
	private String hometown;
	@ApiModelProperty("")
	private String remark;
	@ApiModelProperty("")
	private Date time;
	@ApiModelProperty("所属用户的ID")
	private Integer belong_user_id;
	@ApiModelProperty("银行卡号")
	private String bank_number;
	@ApiModelProperty("开户行")
	private String bank_addr;
	@ApiModelProperty("")
	private Boolean visible;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getSex(){
		return sex;
	}

	public void setSex(String sex){
		this.sex = sex;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public Integer getFk_user_id(){
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id){
		this.fk_user_id = fk_user_id;
	}

	public Boolean getIs_sms(){
		return is_sms;
	}

	public void setIs_sms(Boolean is_sms){
		this.is_sms = is_sms;
	}

	public String getIdcard(){
		return idcard;
	}

	public void setIdcard(String idcard){
		this.idcard = idcard;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getHometown(){
		return hometown;
	}

	public void setHometown(String hometown){
		this.hometown = hometown;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public Integer getBelong_user_id(){
		return belong_user_id;
	}

	public void setBelong_user_id(Integer belong_user_id){
		this.belong_user_id = belong_user_id;
	}

	public String getBank_number(){
		return bank_number;
	}

	public void setBank_number(String bank_number){
		this.bank_number = bank_number;
	}

	public String getBank_addr(){
		return bank_addr;
	}

	public void setBank_addr(String bank_addr){
		this.bank_addr = bank_addr;
	}

	public Boolean getVisible(){
		return visible;
	}

	public void setVisible(Boolean visible){
		this.visible = visible;
	}

}
