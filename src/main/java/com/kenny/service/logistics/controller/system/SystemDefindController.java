package com.kenny.service.logistics.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.system.SystemDefind;
import com.kenny.service.logistics.service.system.SystemDefindService;

@Api(value = "/v1/system/defind", description = "系统所有标准菜单定义")
@RequestMapping(value = "/v1/system/defind")
@RestController
public class SystemDefindController{
	@Autowired
	private SystemDefindService systemDefindService;

	@ApiOperation(value = "增加SystemDefind")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<SystemDefind> Insert(@ApiParam(value = "",required = false)@RequestParam(value = "name",required = false)String name,
	                                     @ApiParam(value = "系统配置的类型",required = false)@RequestParam(value = "type_code",required = false)String type_code){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindService.insert(name,type_code));
	}

	@ApiOperation(value = "修改指定的SystemDefind")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<SystemDefind> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                                     @ApiParam(value = "",required = false)@RequestParam(value = "name",required = false)String name){
		try{
			return new JsonBean(ErrorCode.SUCCESS, systemDefindService.update(id,name));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获所有的的SystemDefind")
	@RequestMapping(value = "/type",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<List<SystemDefind>> selectPage(@ApiParam(value = "系统配置的类型",required = false)@RequestParam(value = "type_code",required = false)String type_code){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindService.select(type_code));

	}

	@ApiOperation(value = "列出所有的SystemDefind")
	@RequestMapping(value = "/type_page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<SystemDefind>> selectByType(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                       @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize,
														   @ApiParam(value = "系统配置的类型",required = false)@RequestParam(value = "type_code",required = false)String type_code){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindService.selectPageByType(offset,pageSize,type_code));
	}

	@ApiOperation(value = "删除指定的SystemDefind")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, systemDefindService.deleteByPrimaryKey(id));
	}

}
