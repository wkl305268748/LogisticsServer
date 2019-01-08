package com.kenny.service.logistics.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.po.system.SystemDefindType;
import com.kenny.service.logistics.service.system.SystemDefindTypeService;

@Api(value = "/v1/system/defind_type", description = "系统所有标准菜单的类型")
@RequestMapping(value = "/v1/system/defind_type")
@RestController
public class SystemDefindTypeController{
	@Autowired
	private SystemDefindTypeService systemDefindTypeService;

	@ApiOperation(value = "增加SystemDefindType")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<SystemDefindType> Insert(@ApiParam(value = "",required = false)@RequestParam(value = "name",required = false)String name){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindTypeService.insert(name));
	}

	@ApiOperation(value = "修改指定的SystemDefindType")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<SystemDefindType> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                         @ApiParam(value = "",required = false)@RequestParam(value = "name",required = false)String name){
		try{
			return new JsonBean(ErrorCode.SUCCESS, systemDefindTypeService.update(id,name));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的SystemDefindType")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<SystemDefindType> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, systemDefindTypeService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的SystemDefindType")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<SystemDefindType>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                           @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindTypeService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "列出所有的SystemDefindType")
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<List<SystemDefindType>> selectAll(){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindTypeService.select());
	}

	@ApiOperation(value = "删除指定的SystemDefindType")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindTypeService.deleteByPrimaryKey(id));
	}

}
