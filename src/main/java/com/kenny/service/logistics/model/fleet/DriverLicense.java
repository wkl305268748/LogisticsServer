package com.kenny.service.logistics.model.fleet;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("")
public class DriverLicense{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("司机表外键")
	private Integer fk_driver_id;
	@ApiModelProperty("驾驶证号码")
	private String number;
	@ApiModelProperty("驾驶证级别")
	private String level;
	@ApiModelProperty("有效日期")
	private Date valid_time;
	@ApiModelProperty("有效日期止")
	private Date unvalid_time;
	@ApiModelProperty("驾照考取日")
	private Date pass_time;
	@ApiModelProperty("从业资格证号码")
	private String work_license;
	@ApiModelProperty("IC卡号")
	private String ic_number;
	@ApiModelProperty("附件列表")
	private String files;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_driver_id(){
		return fk_driver_id;
	}

	public void setFk_driver_id(Integer fk_driver_id){
		this.fk_driver_id = fk_driver_id;
	}

	public String getNumber(){
		return number;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getLevel(){
		return level;
	}

	public void setLevel(String level){
		this.level = level;
	}

	public Date getValid_time(){
		return valid_time;
	}

	public void setValid_time(Date valid_time){
		this.valid_time = valid_time;
	}

	public Date getUnvalid_time(){
		return unvalid_time;
	}

	public void setUnvalid_time(Date unvalid_time){
		this.unvalid_time = unvalid_time;
	}

	public Date getPass_time(){
		return pass_time;
	}

	public void setPass_time(Date pass_time){
		this.pass_time = pass_time;
	}

	public String getWork_license(){
		return work_license;
	}

	public void setWork_license(String work_license){
		this.work_license = work_license;
	}

	public String getIc_number(){
		return ic_number;
	}

	public void setIc_number(String ic_number){
		this.ic_number = ic_number;
	}

	public String getFiles(){
		return files;
	}

	public void setFiles(String files){
		this.files = files;
	}

	public Date getTime(){
		return time;
	}

	public void setTime(Date time){
		this.time = time;
	}

}
