package com.kenny.service.logistics.controller.profit;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.profit.ProfitStatus;
import com.kenny.service.logistics.service.profit.ProfitStatusService;

@Api(value = "/v1/profit_status", description = "")
@RequestMapping(value = "/v1/profit_status")
@RestController
public class ProfitStatusController{
	@Autowired
	private ProfitStatusService profitStatusService;

	@ApiOperation(value = "增加ProfitStatus")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<ProfitStatus> Insert(@ApiParam(value = "",required = false)@RequestParam(value = "fk_profit_id",required = false)Integer fk_profit_id,
	                                     @ApiParam(value = "pay或者recive",required = false)@RequestParam(value = "type",required = false)String type,
	                                     @ApiParam(value = "收款或者付款的钱",required = false)@RequestParam(value = "value",required = false)Float value){
		return new JsonBean(ErrorCode.SUCCESS, profitStatusService.insert(fk_profit_id,type,value));
	}


	@ApiOperation(value = "根据Profit_Id列出所有的ProfitStatus")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<ProfitStatus>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                       @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize,
														   @ApiParam(value = "",required = false)@RequestParam(value = "fk_profit_id",required = false)Integer fk_profit_id){
		return new JsonBean(ErrorCode.SUCCESS, profitStatusService.selectPageByProfitId(offset,pageSize,fk_profit_id));
	}

}
