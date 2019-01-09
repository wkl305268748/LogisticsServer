package com.kenny.service.logistics.model.po.user;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("")
@Data
public class UserToken{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("")
	private String token;
	@ApiModelProperty("")
	private Integer user_id;
	@ApiModelProperty("")
	private Integer userId;
	@ApiModelProperty("")
	private Date time;
	@ApiModelProperty("")
	private Boolean is_valid;

}
