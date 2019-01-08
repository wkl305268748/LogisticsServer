package com.kenny.service.logistics.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.po.order.OrderContract;
import com.kenny.service.logistics.service.order.OrderContractService;

@Api(value = "/v1/order/contract", description = "合同信息表")
@RequestMapping(value = "/v1/order/contract")
@RestController
public class OrderContractController{
	@Autowired
	private OrderContractService orderContractService;

	@ApiOperation(value = "增加OrderContract")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<OrderContract> Insert(@ApiParam(value = "订单外键",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                                      @ApiParam(value = "订单号",required = false)@RequestParam(value = "order_number",required = false)String order_number,
	                                      @ApiParam(value = "合同编号",required = false)@RequestParam(value = "contract_number",required = false)String contract_number,
	                                      @ApiParam(value = "甲方",required = false)@RequestParam(value = "aname",required = false)String aname,
	                                      @ApiParam(value = "乙方",required = false)@RequestParam(value = "bname",required = false)String bname,
	                                      @ApiParam(value = "账户名",required = false)@RequestParam(value = "bbank_name",required = false)String bbank_name,
	                                      @ApiParam(value = "账号",required = false)@RequestParam(value = "bbank_number",required = false)String bbank_number,
	                                      @ApiParam(value = "开户行",required = false)@RequestParam(value = "bbank",required = false)String bbank){
		return new JsonBean(ErrorCode.SUCCESS, orderContractService.insert(fk_order_customer_id,order_number,contract_number,aname,bname,bbank_name,bbank_number,bbank));
	}

	@ApiOperation(value = "修改指定的OrderContract")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<OrderContract> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                      @ApiParam(value = "甲方",required = false)@RequestParam(value = "aname",required = false)String aname,
	                                      @ApiParam(value = "乙方",required = false)@RequestParam(value = "bname",required = false)String bname,
	                                      @ApiParam(value = "账户名",required = false)@RequestParam(value = "bbank_name",required = false)String bbank_name,
	                                      @ApiParam(value = "账号",required = false)@RequestParam(value = "bbank_number",required = false)String bbank_number,
	                                      @ApiParam(value = "开户行",required = false)@RequestParam(value = "bbank",required = false)String bbank){
		try{
			return new JsonBean(ErrorCode.SUCCESS, orderContractService.update(id,aname,bname,bbank_name,bbank_number,bbank));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的OrderContract")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<OrderContract> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, orderContractService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的OrderContract")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<OrderContract>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                        @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, orderContractService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的OrderContract")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, orderContractService.deleteByPrimaryKey(id));
	}

}
