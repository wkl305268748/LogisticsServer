package com.kenny.service.logistics.model.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class UserType{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String code;
	@ApiModelProperty("")
	private String name;

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

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

}
