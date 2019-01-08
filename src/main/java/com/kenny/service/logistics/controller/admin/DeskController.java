package com.kenny.service.logistics.controller.admin;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.po.finance.FinanceRecharge;
import com.kenny.service.logistics.model.po.user.UserSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "/v1/admin/desk", description = "管理员工作台桌面")
@RequestMapping(value = "/v1/admin/desk")
@RestController
public class DeskController {

    @ApiOperation(value = "获取平台运费总额")
    @RequestMapping(value = "/order/all/money" ,method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Integer> getOrderAllMoney(@ApiParam(value = "",required = true)@RequestParam(value = "token",required = true)String token){
        return new JsonBean(ErrorCode.SUCCESS, null);
    }

    @ApiOperation(value = "获取Top10公司客户运费总额")
    @RequestMapping(value = "/order/company/money" ,method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> getAllMoney(@ApiParam(value = "",required = true)@RequestParam(value = "token",required = true)String token){
        return new JsonBean(ErrorCode.SUCCESS, null);
    }
}
