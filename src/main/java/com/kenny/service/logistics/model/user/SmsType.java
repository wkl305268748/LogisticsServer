package com.kenny.service.logistics.model.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class SmsType{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String name;
	@ApiModelProperty("")
	private String type;
	@ApiModelProperty("")
	private String template_id;
	@ApiModelProperty("")
	private String message;
	@ApiModelProperty("")
	private String values;
	@ApiModelProperty("")
	private String signame;

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

	public String getTemplate_id(){
		return template_id;
	}

	public void setTemplate_id(String template_id){
		this.template_id = template_id;
	}

	public String getMessage(){
		return message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getValues(){
		return values;
	}

	public void setValues(String values){
		this.values = values;
	}

	public String getSigname(){
		return signame;
	}

	public void setSigname(String signame){
		this.signame = signame;
	}

}
