package com.kenny.service.logistics.model.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("用户权限")
public class UserAuth{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("用户ID")
	private String user;
	@ApiModelProperty("权限编码json列表")
	private String auth_codes;
	@ApiModelProperty("角色ID，可为空")
	private Integer role_id;
	@ApiModelProperty("时间")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getUser(){
		return user;
	}

	public void setUser(String user){
		this.user = user;
	}

	public String getAuth_codes(){
		return auth_codes;
	}

	public void setAuth_codes(String auth_codes){
		this.auth_codes = auth_codes;
	}

	public Integer getRole_id(){
		return role_id;
	}

	public void setRole_id(Integer role_id){
		this.role_id = role_id;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
