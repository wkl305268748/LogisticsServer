package com.kenny.service.logistics.model.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

@ApiModel("用户金额表，用来记录用户消费充值记录")
public class UserMoney{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("用户外键")
	private Integer fk_user_id;
	@ApiModelProperty("")
	private Integer money;
	@ApiModelProperty("类型in/out 充值、消费")
	private String type;
	@ApiModelProperty("")
	private String remark;
	@ApiModelProperty("")
	private Date time;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getFk_user_id(){
		return fk_user_id;
	}

	public void setFk_user_id(Integer fk_user_id){
		this.fk_user_id = fk_user_id;
	}

	public Integer getMoney(){
		return money;
	}

	public void setMoney(Integer money){
		this.money = money;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
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
