package com.kenny.service.logistics.controller.fleet;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.fleet.DriverLicense;
import com.kenny.service.logistics.service.fleet.DriverLicenseService;

@Api(value = "/v1/fleet/driver_license", description = "司机驾驶证模块")
@RequestMapping(value = "/v1/fleet/driver_license")
@RestController
public class DriverLicenseController{
	@Autowired
	private DriverLicenseService driverLicenseService;

	@ApiOperation(value = "增加DriverLicense")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<DriverLicense> Insert(@ApiParam(value = "司机表外键",required = true)@RequestParam(value = "fk_driver_id",required = true)Integer fk_driver_id,
	                                      @ApiParam(value = "驾驶证号码",required = true)@RequestParam(value = "number",required = true)String number,
	                                      @ApiParam(value = "驾驶证级别",required = true)@RequestParam(value = "level",required = true)String level,
	                                      @ApiParam(value = "有效日期",required = true)@RequestParam(value = "valid_time",required = true)Date valid_time,
	                                      @ApiParam(value = "有效日期止",required = true)@RequestParam(value = "unvalid_time",required = true)Date unvalid_time,
	                                      @ApiParam(value = "驾照考取日",required = false)@RequestParam(value = "pass_time",required = false)Date pass_time,
	                                      @ApiParam(value = "从业资格证号码",required = false)@RequestParam(value = "work_license",required = false)String work_license,
	                                      @ApiParam(value = "IC卡号",required = false)@RequestParam(value = "ic_number",required = false)String ic_number,
	                                      @ApiParam(value = "附件列表",required = false)@RequestParam(value = "files",required = false)String files,
	                                      @ApiParam(value = "",required = false)@RequestParam(value = "time",required = false)Date time){
		return new JsonBean(ErrorCode.SUCCESS, driverLicenseService.insert(fk_driver_id,number,level,valid_time,unvalid_time,pass_time,work_license,ic_number,files,time));
	}

	@ApiOperation(value = "修改指定的DriverLicense")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<DriverLicense> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                      @ApiParam(value = "司机表外键",required = true)@RequestParam(value = "fk_driver_id",required = true)Integer fk_driver_id,
	                                      @ApiParam(value = "驾驶证号码",required = true)@RequestParam(value = "number",required = true)String number,
	                                      @ApiParam(value = "驾驶证级别",required = true)@RequestParam(value = "level",required = true)String level,
	                                      @ApiParam(value = "有效日期",required = true)@RequestParam(value = "valid_time",required = true)Date valid_time,
	                                      @ApiParam(value = "有效日期止",required = true)@RequestParam(value = "unvalid_time",required = true)Date unvalid_time,
	                                      @ApiParam(value = "驾照考取日",required = false)@RequestParam(value = "pass_time",required = false)Date pass_time,
	                                      @ApiParam(value = "从业资格证号码",required = false)@RequestParam(value = "work_license",required = false)String work_license,
	                                      @ApiParam(value = "IC卡号",required = false)@RequestParam(value = "ic_number",required = false)String ic_number,
	                                      @ApiParam(value = "附件列表",required = false)@RequestParam(value = "files",required = false)String files,
	                                      @ApiParam(value = "",required = false)@RequestParam(value = "time",required = false)Date time){
		try{
			return new JsonBean(ErrorCode.SUCCESS, driverLicenseService.update(id,fk_driver_id,number,level,valid_time,unvalid_time,pass_time,work_license,ic_number,files,time));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的DriverLicense")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<DriverLicense> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, driverLicenseService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的DriverLicense")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<DriverLicense>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                        @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, driverLicenseService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的DriverLicense")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, driverLicenseService.deleteByPrimaryKey(id));
	}

}
