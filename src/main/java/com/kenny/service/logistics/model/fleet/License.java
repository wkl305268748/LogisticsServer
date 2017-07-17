package com.kenny.service.logistics.model.fleet;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class License{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("证件编号")
	private String number;
	@ApiModelProperty("证件类型")
	private String type;
	@ApiModelProperty("办证日期")
	private Date pass_time;
	@ApiModelProperty("有效日期")
	private Date valid_date;
	@ApiModelProperty("有效日期止")
	private Date unvalide_date;
	@ApiModelProperty("附件")
	private String files;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	public Date getPass_time(){
		return pass_time;
	}

	public void setPass_time(Date pass_time){
		this.pass_time = pass_time;
	}

	public Date getValid_date(){
		return valid_date;
	}

	public void setValid_date(Date valid_date){
		this.valid_date = valid_date;
	}

	public Date getUnvalide_date(){
		return unvalide_date;
	}

	public void setUnvalide_date(Date unvalide_date){
		this.unvalide_date = unvalide_date;
	}

	public String getFiles(){
		return files;
	}

	public void setFiles(String files){
		this.files = files;
	}

	public String getRemark(){
		return remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
