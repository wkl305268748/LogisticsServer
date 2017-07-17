package com.kenny.service.logistics.model.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class User{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String username;
	@ApiModelProperty("")
	private String phone;
	@ApiModelProperty("")
	private String password;
	@ApiModelProperty("账户类型admin/customer/driver")
	private String type;
	@ApiModelProperty("")
	private Date regtime;
	@ApiModelProperty("是否被禁用")
	private Boolean is_disable;
	@ApiModelProperty("是否存在")
	private Boolean is_valid;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public Date getRegtime(){
		return regtime;
	}

	public void setRegtime(Date regtime){
		this.regtime = regtime;
	}

	public Boolean getIs_disable(){
		return is_disable;
	}

	public void setIs_disable(Boolean is_disable){
		this.is_disable = is_disable;
	}

	public Boolean getIs_valid(){
		return is_valid;
	}

	public void setIs_valid(Boolean is_valid){
		this.is_valid = is_valid;
	}

}
