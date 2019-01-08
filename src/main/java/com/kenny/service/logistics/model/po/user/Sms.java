package com.kenny.service.logistics.model.po.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class Sms{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String code;
	@ApiModelProperty("")
	private String phone;
	@ApiModelProperty("")
	private String cookie;
	@ApiModelProperty("")
	private Integer code_type_id;
	@ApiModelProperty("")
	private Date sendtime;
	@ApiModelProperty("")
	private Date subtime;
	@ApiModelProperty("")
	private Boolean is_submit;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getCookie(){
		return cookie;
	}

	public void setCookie(String cookie){
		this.cookie = cookie;
	}

	public Integer getCode_type_id() {
		return code_type_id;
	}

	public void setCode_type_id(Integer code_type_id) {
		this.code_type_id = code_type_id;
	}

	public Date getSendtime(){
		return sendtime;
	}

	public void setSendtime(Date sendtime){
		this.sendtime = sendtime;
	}

	public Date getSubtime(){
		return subtime;
	}

	public void setSubtime(Date subtime){
		this.subtime = subtime;
	}

	public Boolean getIs_submit(){
		return is_submit;
	}

	public void setIs_submit(Boolean is_submit){
		this.is_submit = is_submit;
	}

}
