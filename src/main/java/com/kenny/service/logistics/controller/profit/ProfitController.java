package com.kenny.service.logistics.controller.profit;

import com.kenny.service.logistics.json.response.PayCardResponse;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.user.UserBaseService;
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
	@Autowired
	private UserBaseService userBaseService;

	@ApiOperation(value = "修改指定的Profit")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Profit> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "fk_order_customer_id",required = false)Integer fk_order_customer_id,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "order_number",required = false)String order_number,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "recive",required = false)Float recive,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "pay",required = false)Float pay,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "recive_now",required = false)Float recive_now,
	                               @ApiParam(value = "",required = false)@RequestParam(value = "pay_now",required = false)Float pay_now,
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
								@ApiParam(value = "",required = false)@RequestParam(value = "pay",required = false)Float pay){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.pay(id,pay));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "增加付款到银行卡")
	@RequestMapping(value = "/pay/card/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Profit> payCard(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
									@ApiParam(value = "",required = false)@RequestParam(value = "token",required = false)String token,
									@ApiParam(value = "",required = false)@RequestParam(value = "name",required = false)String name,
									@ApiParam(value = "",required = false)@RequestParam(value = "card",required = false)String card,
									@ApiParam(value = "",required = false)@RequestParam(value = "bank",required = false)String bank,
									@ApiParam(value = "",required = false)@RequestParam(value = "phone",required = false)String phone,
									@ApiParam(value = "",required = false)@RequestParam(value = "idcard",required = false)String idcard,
									@ApiParam(value = "",required = false)@RequestParam(value = "pay",required = false)Float pay){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.payToCard(token,id,name,card,bank,phone,idcard,pay));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取付款信息")
	@RequestMapping(value = "/pay/card/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PayCardResponse> getCard(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
											 @ApiParam(value = "",required = false)@RequestParam(value = "token",required = false)String token){
		try{
			return new JsonBean(ErrorCode.SUCCESS, profitService.getCard(token,id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "增加收款")
	@RequestMapping(value = "/recive/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Profit> recive(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
									@ApiParam(value = "",required = false)@RequestParam(value = "recive",required = false)Float recive){
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
	public JsonBean<PageResponse<Profit>> selectPage(@ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token,
													 @ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                 @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		try {
			User user = userBaseService.getUserByToken(token);
			return new JsonBean(ErrorCode.SUCCESS, profitService.selectPageByBelongUser(offset,pageSize,user.getId()));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "删除指定的Profit")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, profitService.deleteByPrimaryKey(id));
	}

}
