package com.kenny.service.logistics.controller.profit;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.profit.Profit;
import com.kenny.service.logistics.service.profit.ProfitService;

@Api(value = "/v1/profit", description = "")
@RequestMapping(value = "/v1/profit")
@RestController
public class ProfitController{
	@Autowired
	private ProfitService profitService;

	@ApiOperation(value = "修改指定的Profit")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Profit> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "order_number",required = false)String order_number,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "recive",required = false)Integer recive,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "pay",required = false)Integer pay,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "recive_now",required = false)Integer recive_now,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "pay_now",required = false)Integer pay_now,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "is_recive",required = false)Boolean is_recive,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "is_pay",required = false)Boolean is_pay){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.update(id,fk_order_customer_id,order_number,recive,pay,recive_now,pay_now,is_recive,is_pay));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "增加付款")
	@RequestMapping(value = "/pay/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Profit> pay(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
								@ApiParam(value = "",required = false)@RequestParam(value = "pay",required = false)Integer pay){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.pay(id,pay));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "增加收款")
	@RequestMapping(value = "/recive/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Profit> recive(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
									@ApiParam(value = "",required = false)@RequestParam(value = "recive",required = false)Integer recive){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.recive(id,recive));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的Profit")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Profit> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的Profit")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Profit>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                 @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, profitService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的Profit")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, profitService.deleteByPrimaryKey(id));
	}

}
