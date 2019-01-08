package com.kenny.service.logistics.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.po.system.SystemVersion;
import com.kenny.service.logistics.service.system.SystemVersionService;

@Api(value = "/v1/system/version", description = "")
@RequestMapping(value = "/v1/system/version")
@RestController
public class SystemVersionController{
	@Autowired
	private SystemVersionService systemVersionService;

	@ApiOperation(value = "增加SystemVersion")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<SystemVersion> Insert(@ApiParam(value = "类型",required = false)@RequestParam(value = "type",required = false)String type,
	                                      @ApiParam(value = "版本名称",required = false)@RequestParam(value = "version",required = false)String version,
	                                      @ApiParam(value = "版本号",required = false)@RequestParam(value = "version_number",required = false)Integer version_number,
	                                      @ApiParam(value = "更新记录",required = false)@RequestParam(value = "changelog",required = false)String changelog,
	                                      @ApiParam(value = "下载地址",required = false)@RequestParam(value = "url",required = false)String url){
		return new JsonBean(ErrorCode.SUCCESS, systemVersionService.insert(type,version,version_number,changelog,url));
	}

	@ApiOperation(value = "修改指定的SystemVersion")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<SystemVersion> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                      @ApiParam(value = "类型",required = false)@RequestParam(value = "type",required = false)String type,
	                                      @ApiParam(value = "版本名称",required = false)@RequestParam(value = "version",required = false)String version,
	                                      @ApiParam(value = "版本号",required = false)@RequestParam(value = "version_number",required = false)Integer version_number,
	                                      @ApiParam(value = "更新记录",required = false)@RequestParam(value = "changelog",required = false)String changelog,
	                                      @ApiParam(value = "下载地址",required = false)@RequestParam(value = "url",required = false)String url){
		try{
			return new JsonBean(ErrorCode.SUCCESS, systemVersionService.update(id,type,version,version_number,changelog,url));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的SystemVersion")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<SystemVersion> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, systemVersionService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}


	@ApiOperation(value = "获取指定type的最新SystemVersion")
	@RequestMapping(value = "/newest",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<SystemVersion> selectTopByType(@ApiParam(value = "类型",required = false)@RequestParam(value = "type",required = false)String type){
		try{
			return new JsonBean(ErrorCode.SUCCESS, systemVersionService.selectTopByType(type));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的SystemVersion")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<SystemVersion>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                        @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, systemVersionService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的SystemVersion")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, systemVersionService.deleteByPrimaryKey(id));
	}

}
