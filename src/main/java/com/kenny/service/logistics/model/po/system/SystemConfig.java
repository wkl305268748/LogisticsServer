package com.kenny.service.logistics.model.po.system;

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
	@ApiModelProperty("")
	private Integer belong_user_id;

	public SystemConfig(){}
	public SystemConfig(String name, String code, String value, Integer belong_user_id) {
		this.name = name;
		this.code = code;
		this.value = value;
		this.belong_user_id = belong_user_id;
	}

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

	public Integer getBelong_user_id(){
		return belong_user_id;
	}

	public void setBelong_user_id(Integer belong_user_id){
		this.belong_user_id = belong_user_id;
	}

}
