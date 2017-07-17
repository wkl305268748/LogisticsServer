package com.kenny.service.logistics.model.fleet;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class Driver{
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

	public Boolean getVisible(){
		return visible;
	}

	public void setVisible(Boolean visible){
		this.visible = visible;
	}

}
