package com.kenny.service.logistics.model.po.order;

import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("")
@Data
public class Order{
	@ApiModelProperty("")
	private Integer id;
	@ApiModelProperty("订单号")
	private String order_number;
	@ApiModelProperty("流水号")
	private String serial_number;
	@ApiModelProperty("下单的客户")
	private Integer fk_customer_id;
	@ApiModelProperty("是否指定物流公司接单")
	private Boolean is_company;
	@ApiModelProperty("客户指定接单的物流公司")
	private Integer fk_want_company_id;
	@ApiModelProperty("接单的物流公司")
	private Integer fk_company_id;
	@ApiModelProperty("订单状态，关联status表")
	private String status;
	@ApiModelProperty("")
	private Date time;
}
