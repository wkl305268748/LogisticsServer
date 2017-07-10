package com.kenny.service.logistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.Goods;
import com.kenny.service.logistics.service.GoodsService;

@Api(value = "/v1/goods", description = "货物信息，用户下单时需要提供货物信息")
@RequestMapping(value = " /v1/goods")
@RestController
public class GoodsController{
	@Autowired
	private GoodsService goodsService;

	@ApiOperation(value = "增加goods")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Goods> Insert(@ApiParam(value = "货物名称",required = false)@RequestParam(value = "name",required = false)String name,
	                              @ApiParam(value = "货物的体积",required = false)@RequestParam(value = "size",required = false)String size,
	                              @ApiParam(value = "货物总重量，单位吨",required = false)@RequestParam(value = "weight",required = false)Integer weight,
	                              @ApiParam(value = "货物备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		return new JsonBean(ErrorCode.SUCCESS, goodsService.insert(name,size,weight,remark));
	}

	@ApiOperation(value = "修改指定的goods")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Goods> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                              @ApiParam(value = "货物名称",required = false)@RequestParam(value = "name",required = false)String name,
	                              @ApiParam(value = "货物的体积",required = false)@RequestParam(value = "size",required = false)String size,
	                              @ApiParam(value = "货物总重量，单位吨",required = false)@RequestParam(value = "weight",required = false)Integer weight,
	                              @ApiParam(value = "货物备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		try{
			return new JsonBean(ErrorCode.SUCCESS, goodsService.update(id,name,size,weight,remark));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的goods")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Goods> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, goodsService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的goods")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Goods>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                                @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, goodsService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的goods")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, goodsService.deleteByPrimaryKey(id));
	}

}
