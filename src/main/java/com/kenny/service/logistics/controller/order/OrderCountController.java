package com.kenny.service.logistics.controller.order;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.count.CountMap;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.order.OrderCountService;
import com.kenny.service.logistics.service.user.UserBaseService;
import com.kenny.service.logistics.service.user.UserCustomerService;
import com.kenny.service.logistics.service.user.UserManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
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
    UserManagerService userManagerService;

    @ApiOperation(value = "统计所有数据")
    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> All(){
        Map<String,Object> countMaps = new HashMap<>();
        countMaps.put("customer",userCustomerService.getCount());
        countMaps.put("order_all",orderCountService.getAllCount());
        countMaps.put("order_place",orderCountService.getPlaceCount());
        countMaps.put("order_taking",orderCountService.getTakingCount());
        countMaps.put("order_sign",orderCountService.getSignCount());
        countMaps.put("order_refuse",orderCountService.getRefuseCount());
        return new JsonBean(ErrorCode.SUCCESS,countMaps);
    }

    @ApiOperation(value = "按账户统计订单总数量")
    @RequestMapping(value = "order/customer", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Map> OrderCustomer() throws ErrorCodeException {

        return new JsonBean(ErrorCode.SUCCESS,orderCountService.getOrderCustomerMap(0,10,"customer"));
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
}
