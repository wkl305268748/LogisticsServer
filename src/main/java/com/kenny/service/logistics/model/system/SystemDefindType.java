package com.kenny.service.logistics.model.system;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("系统所有标准菜单的类型")
public class SystemDefindType{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String name;

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

}
