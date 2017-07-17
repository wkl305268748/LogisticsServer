package com.kenny.service.logistics.model.system;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("系统所有标准菜单定义")
public class SystemDefind{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("系统配置的类型")
	private String type;

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

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

}
