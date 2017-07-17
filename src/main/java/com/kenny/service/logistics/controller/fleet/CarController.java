package com.kenny.service.logistics.controller.fleet;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.fleet.Car;
import com.kenny.service.logistics.service.fleet.CarService;

@Api(value = "/v1/fleet/car", description = "车辆模块")
@RequestMapping(value = "/v1/fleet/car")
@RestController
public class CarController{
	@Autowired
	private CarService carService;

	@ApiOperation(value = "增加Car")
	@RequestMapping(value = "",method = RequestMethod.POST)
	@ResponseBody
	public JsonBean<Car> Insert(@ApiParam(value = "车牌号如皖A5504",required = true)@RequestParam(value = "plate",required = true)String plate,
	                            @ApiParam(value = "车辆类型如面包车",required = true)@RequestParam(value = "type",required = true)String type,
	                            @ApiParam(value = "车辆所属如自有车辆",required = true)@RequestParam(value = "resource",required = true)String resource,
	                            @ApiParam(value = "挂车车牌",required = false)@RequestParam(value = "two_plate",required = false)String two_plate,
	                            @ApiParam(value = "随车电话",required = false)@RequestParam(value = "driver_phone",required = false)String driver_phone,
	                            @ApiParam(value = "随车司机姓名",required = false)@RequestParam(value = "driver_name",required = false)String driver_name,
	                            @ApiParam(value = "能耗类型",required = false)@RequestParam(value = "energy",required = false)String energy,
	                            @ApiParam(value = "车辆长度",required = false)@RequestParam(value = "length",required = false)String length,
	                            @ApiParam(value = "核定载重吨",required = false)@RequestParam(value = "weight",required = false)Float weight,
	                            @ApiParam(value = "汽车VIN码",required = false)@RequestParam(value = "vin",required = false)String vin,
	                            @ApiParam(value = "车辆品牌",required = false)@RequestParam(value = "brand",required = false)String brand,
	                            @ApiParam(value = "发动机号",required = false)@RequestParam(value = "engine",required = false)String engine,
	                            @ApiParam(value = "车轴数",required = false)@RequestParam(value = "axle",required = false)String axle,
	                            @ApiParam(value = "轴距",required = false)@RequestParam(value = "wheelbase",required = false)String wheelbase,
	                            @ApiParam(value = "轮胎数量",required = false)@RequestParam(value = "tire",required = false)String tire,
	                            @ApiParam(value = "出厂日期",required = false)@RequestParam(value = "factory_time",required = false)Date factory_time,
	                            @ApiParam(value = "购买日期",required = false)@RequestParam(value = "buy_time",required = false)Date buy_time,
	                            @ApiParam(value = "购买价格",required = false)@RequestParam(value = "buy_price",required = false)Integer buy_price,
	                            @ApiParam(value = "年审日期",required = false)@RequestParam(value = "limited_time",required = false)Date limited_time,
	                            @ApiParam(value = "二级维护有效期",required = false)@RequestParam(value = "tow_maintain_time",required = false)Date tow_maintain_time,
	                            @ApiParam(value = "保险单号",required = false)@RequestParam(value = "insurance_policy",required = false)String insurance_policy,
	                            @ApiParam(value = "保险公司",required = false)@RequestParam(value = "insurance_company",required = false)String insurance_company,
	                            @ApiParam(value = "保险有效期",required = false)@RequestParam(value = "insurance_time",required = false)Date insurance_time,
	                            @ApiParam(value = "车头照片",required = false)@RequestParam(value = "front_img",required = false)String front_img,
	                            @ApiParam(value = "车尾照片",required = false)@RequestParam(value = "tail_img",required = false)String tail_img,
	                            @ApiParam(value = "车辆备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		return new JsonBean(ErrorCode.SUCCESS, carService.insert(plate,type,resource,two_plate,driver_phone,driver_name,energy,length,weight,vin,brand,engine,axle,wheelbase,tire,factory_time,buy_time,buy_price,limited_time,tow_maintain_time,insurance_policy,insurance_company,insurance_time,front_img,tail_img,remark));
	}

	@ApiOperation(value = "修改指定的Car")
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	@ResponseBody
	public JsonBean<Car> update(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id,
	                            @ApiParam(value = "车牌号如皖A5504",required = true)@RequestParam(value = "plate",required = true)String plate,
	                            @ApiParam(value = "车辆类型如面包车",required = true)@RequestParam(value = "type",required = true)String type,
	                            @ApiParam(value = "车辆所属如自有车辆",required = true)@RequestParam(value = "resource",required = true)String resource,
	                            @ApiParam(value = "挂车车牌",required = false)@RequestParam(value = "two_plate",required = false)String two_plate,
	                            @ApiParam(value = "随车电话",required = false)@RequestParam(value = "driver_phone",required = false)String driver_phone,
	                            @ApiParam(value = "随车司机姓名",required = false)@RequestParam(value = "driver_name",required = false)String driver_name,
	                            @ApiParam(value = "能耗类型",required = false)@RequestParam(value = "energy",required = false)String energy,
	                            @ApiParam(value = "车辆长度",required = false)@RequestParam(value = "length",required = false)String length,
	                            @ApiParam(value = "核定载重吨",required = false)@RequestParam(value = "weight",required = false)Float weight,
	                            @ApiParam(value = "汽车VIN码",required = false)@RequestParam(value = "vin",required = false)String vin,
	                            @ApiParam(value = "车辆品牌",required = false)@RequestParam(value = "brand",required = false)String brand,
	                            @ApiParam(value = "发动机号",required = false)@RequestParam(value = "engine",required = false)String engine,
	                            @ApiParam(value = "车轴数",required = false)@RequestParam(value = "axle",required = false)String axle,
	                            @ApiParam(value = "轴距",required = false)@RequestParam(value = "wheelbase",required = false)String wheelbase,
	                            @ApiParam(value = "轮胎数量",required = false)@RequestParam(value = "tire",required = false)String tire,
	                            @ApiParam(value = "出厂日期",required = false)@RequestParam(value = "factory_time",required = false)Date factory_time,
	                            @ApiParam(value = "购买日期",required = false)@RequestParam(value = "buy_time",required = false)Date buy_time,
	                            @ApiParam(value = "购买价格",required = false)@RequestParam(value = "buy_price",required = false)Integer buy_price,
	                            @ApiParam(value = "年审日期",required = false)@RequestParam(value = "limited_time",required = false)Date limited_time,
	                            @ApiParam(value = "二级维护有效期",required = false)@RequestParam(value = "tow_maintain_time",required = false)Date tow_maintain_time,
	                            @ApiParam(value = "保险单号",required = false)@RequestParam(value = "insurance_policy",required = false)String insurance_policy,
	                            @ApiParam(value = "保险公司",required = false)@RequestParam(value = "insurance_company",required = false)String insurance_company,
	                            @ApiParam(value = "保险有效期",required = false)@RequestParam(value = "insurance_time",required = false)Date insurance_time,
	                            @ApiParam(value = "车头照片",required = false)@RequestParam(value = "front_img",required = false)String front_img,
	                            @ApiParam(value = "车尾照片",required = false)@RequestParam(value = "tail_img",required = false)String tail_img,
	                            @ApiParam(value = "车辆备注",required = false)@RequestParam(value = "remark",required = false)String remark){
		try{
			return new JsonBean(ErrorCode.SUCCESS, carService.update(id,plate,type,resource,two_plate,driver_phone,driver_name,energy,length,weight,vin,brand,engine,axle,wheelbase,tire,factory_time,buy_time,buy_price,limited_time,tow_maintain_time,insurance_policy,insurance_company,insurance_time,front_img,tail_img,remark));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "获取指定的Car")
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<Car> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		try{
			return new JsonBean(ErrorCode.SUCCESS, carService.selectByPrimaryKey(id));
		}catch(ErrorCodeException e){
			return new JsonBean(e.getErrorCode());
		}
	}

	@ApiOperation(value = "列出所有的Car")
	@RequestMapping(value = "/page",method = RequestMethod.GET)
	@ResponseBody
	public JsonBean<PageResponse<Car>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0")Integer offset,
	                                              @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10")Integer pageSize){
		return new JsonBean(ErrorCode.SUCCESS, carService.selectPage(offset,pageSize));
	}

	@ApiOperation(value = "删除指定的Car")
	@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true)@PathVariable()Integer id){
		return new JsonBean(ErrorCode.SUCCESS, carService.deleteByPrimaryKey(id));
	}

}
