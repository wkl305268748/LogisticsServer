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
	private String type_code;

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

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
}
