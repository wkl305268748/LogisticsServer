package com.kenny.service.logistics.controller.fleet;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.fleet.License;
import com.kenny.service.logistics.service.fleet.LicenseService;

@Api(value = "/v1/fleet/license", description = "其他证件模块")
@RequestMapping(value = "/v1/fleet/license")
@RestController
public class LicenseController{
	@Autowired
	private LicenseService licenseService;

	@ApiOperation(value = "增加License")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<License> Insert(@ApiParam(value = "证件编号",required = true)@RequestParam(value = "number",required = true)String number,
	                                @ApiParam(value = "证件类型",required = true)@RequestParam(value = "type",required = true)String type,
	                                @ApiParam(value = "办证日期",required = false)@RequestParam(value = "pass_time",required = false)Date pass_time,
	                                @ApiParam(value = "有效日期",required = true)@RequestParam(value = "valid_date",required = true)Date valid_date,
	                                @ApiParam(value = "有效日期止",required = true)@RequestParam(value = "unvalide_date",required = true)Date unvalide_date,
	                                @ApiParam(value = "附件",required = false)@RequestParam(value = "files",required = false)String files,
	                                @ApiParam(value = "备注",required = false)@RequestParam(value = "remark",required = false)String remark,
	                                @ApiParam(value = "",required = false)@RequestParam(value = "time",required = false)Date time){
		return new JsonBean(ErrorCode.SUCCESS, licenseService.insert(number,type,pass_time,valid_date,unvalide_date,files,remark,time));
	}

	@ApiOperation(value = "修改指定的License")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<License> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                @ApiParam(value = "证件编号",required = true)@RequestParam(value = "number",required = true)String number,
	                                @ApiParam(value = "证件类型",required = true)@RequestParam(value = "type",required = true)String type,
	                                @ApiParam(value = "办证日期",required = false)@RequestParam(value = "pass_time",required = false)Date pass_time,
	                                @ApiParam(value = "有效日期",required = true)@RequestParam(value = "valid_date",required = true)Date valid_date,
	                                @ApiParam(value = "有效日期止",required = true)@RequestParam(value = "unvalide_date",required = true)Date unvalide_date,
	                                @ApiParam(value = "附件",required = false)@RequestParam(value = "files",required = false)String files,
	                                @ApiParam(value = "备注",required = false)@RequestParam(value = "remark",required = false)String remark,
	                                @ApiParam(value = "",required = false)@RequestParam(value = "time",required = false)Date time){
		try{
			return new JsonBean(ErrorCode.SUCCESS, licenseService.update(id,number,type,pass_time,valid_date,unvalide_date,files,remark,time));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的License")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<License> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, licenseService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的License")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<License>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                  @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, licenseService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的License")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, licenseService.deleteByPrimaryKey(id));
	}

}
