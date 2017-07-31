package com.kenny.service.logistics.model.system;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("系统配置表")
public class SystemConfig{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("编码")
	private String code;
	@ApiModelProperty("值")
	private String value;

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

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getValue(){
		return value;
	}

	public void setValue(String value){
		this.value = value;
	}

}
