package com.kenny.service.logistics.model.po.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class UserToken{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String token;
	@ApiModelProperty("")
	private Integer user_id;
	@ApiModelProperty("")
	private Date time;
	@ApiModelProperty("")
	private Boolean is_valid;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getToken(){
		return token;
	}

	public void setToken(String token){
		this.token = token;
	}

	public Integer getUser_id(){
		return user_id;
	}

	public void setUser_id(Integer user_id){
		this.user_id = user_id;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

	public Boolean getIs_valid(){
		return is_valid;
	}

	public void setIs_valid(Boolean is_valid){
		this.is_valid = is_valid;
	}

}
