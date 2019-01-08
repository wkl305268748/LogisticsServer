package com.kenny.service.logistics.controller.order;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.po.fleet.FleetDriver;
import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.service.fleet.FleetDriverService;
import com.kenny.service.logistics.service.order.OrderCountService;
import com.kenny.service.logistics.service.user.UserCompanyService;
import com.kenny.service.logistics.service.user.UserCustomerService;
import com.kenny.service.logistics.service.user.UserManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Kenny on 2017/8/6.
 */


@Api(value = "/v1/order/count", description = "订单统计模块")
@RequestMapping(value = "/v1/order/count")
@RestController
public class OrderCountController {
    @Autowired
    UserCustomerService userCustomerService;
    @Autowired
    OrderCountService orderCountService;
    @Autowired
    UserCompanyService userCompanyService;
    @Autowired
    UserManagerService userManagerService;
    @Autowired
    FleetDriverService fleetDriverService;

    @ApiOperation(value = "统计所有数据")
    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> All(){
        Map<String,Object> countMaps = new HashMap<>();
        countMaps.put("customer",userCustomerService.getCount());
        countMaps.put("company",userCompanyService.getCount());
        countMaps.put("order_all",orderCountService.getAllCount());
        countMaps.put("order_place",orderCountService.getPlaceCount());
        countMaps.put("order_taking",orderCountService.getTakingCount());
        countMaps.put("order_sign",orderCountService.getSignCount());
        countMaps.put("order_refuse",orderCountService.getRefuseCount());
        return new JsonBean(ErrorCode.SUCCESS,countMaps);
    }

    @ApiOperation(value = "按账户统计订单总数量")
    @RequestMapping(value = "order/company", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> OrderCompany() throws ErrorCodeException {

        return new JsonBean(ErrorCode.SUCCESS,orderCountService.getOrderCompanyMap(0,10));
    }

    @ApiOperation(value = "统计各状态订单总数量")
    @RequestMapping(value = "order/status", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean selectPage1() throws ErrorCodeException {
        return null;
    }


    @ApiOperation(value = "按天统计范围内订单数量")
    @RequestMapping(value = "order/day", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> OrderDay(@ApiParam(value = "开始时间", required = true) @RequestParam(value = "begin", required = true) Date begin,
                             @ApiParam(value = "结束时间", required = true) @RequestParam(value = "end", required = true) Date end) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS,orderCountService.getOrderCrateDay(begin,end));
    }

    @ApiOperation(value = "按天统计最近7天范围内订单数量")
    @RequestMapping(value = "order/day7", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> OrderDay7() throws ErrorCodeException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
        return new JsonBean(ErrorCode.SUCCESS,orderCountService.getOrderCrateDay(cal.getTime(),new Date()));
    }


    @ApiOperation(value = "统计所有数据")
    @RequestMapping(value = "customer/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> CustomerAll(@ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true)String token){
        Map<String,Object> countMaps = new HashMap<>();
        try {
            User user = userCustomerService.getUserByToken(token);
            countMaps.put("order_all",orderCountService.getCustomerAllCount(user.getId()));
            countMaps.put("order_place",orderCountService.getCustomerPlaceCount(user.getId()));
            countMaps.put("order_taking",orderCountService.getCustomerTakingCount(user.getId()));
            countMaps.put("order_sign",orderCountService.getCustomerSignCount(user.getId()));
            countMaps.put("order_refuse",orderCountService.getCustomerRefuseCount(user.getId()));
            return new JsonBean(ErrorCode.SUCCESS,countMaps);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "客户按天统计最近7天范围内订单数量")
    @RequestMapping(value = "customer/order/day7", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> CustomerOrderDay7(@ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true)String token){
        try {
            User user = userCustomerService.getUserByToken(token);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
            return new JsonBean(ErrorCode.SUCCESS,orderCountService.getOrderCrateDayByUserId(user.getId(),cal.getTime(),new Date()));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "（公司统计）统计所有数据")
    @RequestMapping(value = "company/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> CompanyAll(@ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true)String token){
        Map<String,Object> countMaps = new HashMap<>();
        try {
            User user = userCustomerService.getUserByToken(token);
            countMaps.put("order_all",orderCountService.getCompanyAllCount(user.getId()));
            countMaps.put("order_place",orderCountService.getCompanyPlaceCount(user.getId()));
            countMaps.put("order_taking",orderCountService.getCompanyTakingCount(user.getId()));
            countMaps.put("order_sign",orderCountService.getCompanySignCount(user.getId()));
            countMaps.put("order_refuse",orderCountService.getCompanyRefuseCount(user.getId()));
            return new JsonBean(ErrorCode.SUCCESS,countMaps);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "（公司统计）公司按天统计最近7天范围内订单数量、处理订单量")
    @RequestMapping(value = "company/order/day7", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> CompanyOrderDay7(@ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true)String token){
        try {
            User user = userCompanyService.getUserByToken(token);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
            return new JsonBean(ErrorCode.SUCCESS,orderCountService.getOrderTakingDayByUserId(user.getId(),cal.getTime(),new Date()));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "（司机）统计所有数据")
    @RequestMapping(value = "driver/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> DriverAll(@ApiParam(value = "token", required = true) @RequestParam(value = "token", required = true)String token){
        Map<String,Object> countMaps = new HashMap<>();
        try {
            User user = userCustomerService.getUserByToken(token);
            FleetDriver fleetDriver = fleetDriverService.selectByUserId(user.getId());
            countMaps.put("order_all",orderCountService.getDriverAllCount(fleetDriver.getId()));
            countMaps.put("order_taking",orderCountService.getDriverTakingCount(fleetDriver.getId()));
            countMaps.put("order_sign",orderCountService.getDriverSignCount(fleetDriver.getId()));
            return new JsonBean(ErrorCode.SUCCESS,countMaps);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}
