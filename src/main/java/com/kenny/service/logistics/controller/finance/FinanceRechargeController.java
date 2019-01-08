package com.kenny.service.logistics.controller.finance;

import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.model.po.user.UserSet;
import com.kenny.service.logistics.service.user.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.po.finance.FinanceRecharge;
import com.kenny.service.logistics.service.finance.FinanceRechargeService;

@Api(value = "/v1/finance/recharge", description = "用户充值表，记录用户充值信息")
@RequestMapping(value = "/v1/finance/recharge")
@RestController
public class FinanceRechargeController{
	@Autowired
	private FinanceRechargeService financeRechargeService;
	@Autowired
	private UserCustomerService userCustomerService;


	@ApiOperation(value = "获取随机生成用户充值信息")
	@RequestMapping(value = "create",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<FinanceRecharge> Create(@ApiParam(value = "",required = true)@RequestParam(value = "token",required = true)String token){
		try {
			UserSet userSet = userCustomerService.getUserByTokenEx(token);
			return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.create(userSet.getUser().getId(),userSet.getUserInfo().getCompany()));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出自己的FinanceRecharge")
	@RequestMapping(value = "/my/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<FinanceRecharge>> selectMyPage(@ApiParam(value = "",required = true)@RequestParam(value = "token",required = true)String token,
																@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
															  	@ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){

		try {
			User user = userCustomerService.getUserByToken(token);
			return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.selectPageByUserId(user.getId(),offset,pageSize));
		} catch (ErrorCodeException e) {
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "增加FinanceRecharge")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<FinanceRecharge> Insert(@ApiParam(value = "",required = true)@RequestParam(value = "fk_user_id",required = true)Integer fk_user_id,
	                                        @ApiParam(value = "进行充值的公司名",required = false)@RequestParam(value = "company_out",required = false)String company_out,
	                                        @ApiParam(value = "账户名",required = false)@RequestParam(value = "bank_name",required = false)String bank_name,
	                                        @ApiParam(value = "银行账号",required = false)@RequestParam(value = "bank_number",required = false)String bank_number,
	                                        @ApiParam(value = "银行名称",required = false)@RequestParam(value = "bank",required = false)String bank,
	                                        @ApiParam(value = "支行地址",required = false)@RequestParam(value = "bank_addr",required = false)String bank_addr,
	                                        @ApiParam(value = "充值金额",required = true)@RequestParam(value = "money",required = true)Float money,
	                                        @ApiParam(value = "汇款识别码",required = true)@RequestParam(value = "money_code",required = true)String money_code,
	                                        @ApiParam(value = "用途",required = false)@RequestParam(value = "money_for",required = false)String money_for,
	                                        @ApiParam(value = "充值状态",required = false)@RequestParam(value = "status",required = false)String status,
	                                        @ApiParam(value = "操作的管理员",required = false)@RequestParam(value = "fk_admin_id",required = false)Integer fk_admin_id){
		return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.insert(fk_user_id,company_out,bank_name,bank_number,bank,bank_addr,money,money_code,money_for,status,fk_admin_id));
	}

	@ApiOperation(value = "修改指定的FinanceRecharge")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<FinanceRecharge> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                        @ApiParam(value = "",required = true)@RequestParam(value = "fk_user_id",required = true)Integer fk_user_id,
	                                        @ApiParam(value = "进行充值的公司名",required = false)@RequestParam(value = "company_out",required = false)String company_out,
	                                        @ApiParam(value = "账户名",required = false)@RequestParam(value = "bank_name",required = false)String bank_name,
	                                        @ApiParam(value = "银行账号",required = false)@RequestParam(value = "bank_number",required = false)String bank_number,
	                                        @ApiParam(value = "银行名称",required = false)@RequestParam(value = "bank",required = false)String bank,
	                                        @ApiParam(value = "支行地址",required = false)@RequestParam(value = "bank_addr",required = false)String bank_addr,
	                                        @ApiParam(value = "充值金额",required = true)@RequestParam(value = "money",required = true)Float money,
	                                        @ApiParam(value = "汇款识别码",required = true)@RequestParam(value = "money_code",required = true)String money_code,
	                                        @ApiParam(value = "用途",required = false)@RequestParam(value = "money_for",required = false)String money_for,
	                                        @ApiParam(value = "充值状态",required = false)@RequestParam(value = "status",required = false)String status,
	                                        @ApiParam(value = "操作的管理员",required = false)@RequestParam(value = "fk_admin_id",required = false)Integer fk_admin_id,
	                                        @ApiParam(value = "",required = false)@RequestParam(value = "time",required = false)Date time){
		try{
			return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.update(id,fk_user_id,company_out,bank_name,bank_number,bank,bank_addr,money,money_code,money_for,status,fk_admin_id,time));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的FinanceRecharge")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<FinanceRecharge> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的FinanceRecharge")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<FinanceRecharge>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                          @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的FinanceRecharge")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, financeRechargeService.deleteByPrimaryKey(id));
	}

}
