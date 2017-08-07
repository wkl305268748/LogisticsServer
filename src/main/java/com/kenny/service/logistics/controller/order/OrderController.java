package com.kenny.service.logistics.controller.order;


import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.order.OrderSet;
import com.kenny.service.logistics.model.system.Defind;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.order.OrderService;
import com.kenny.service.logistics.service.user.UserBaseService;
import com.kenny.service.logistics.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "/v1/order", description = "订单接口模块")
@RequestMapping(value = "/v1/order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserBaseService userBaseService;

    @ApiOperation(value = "列出所有的Order")
    @RequestMapping(value = "/page/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                       @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPage(offset, pageSize));
    }

    @ApiOperation(value = "获取指定的Order")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<OrderSet> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "根据Token列出所有的Order")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPageByToken(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                              @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                              @ApiParam(value = "用户TOKEN", required = true) @RequestParam(value = "token", required = true) String token) {
        try {
            User user = userBaseService.getUserByToken(token);
            return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByUserId(offset, pageSize, user.getId()));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "列出所有customer的Order")
    @RequestMapping(value = "/page/customer", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPageByCustomer(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                 @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByCustomer(offset, pageSize));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "列出待处理的Order")
    @RequestMapping(value = "/page_place", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPageByPLACE(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                              @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_PLACE));
    }


    @ApiOperation(value = "列出被拒绝的Order")
    @RequestMapping(value = "/page_refuse", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPageByREFUSE(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                               @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_REFUSE));
    }

    @ApiOperation(value = "列出已处理的Order")
    @RequestMapping(value = "/page_taking", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPageByTAKING(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                               @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_TAKING));
    }

    @ApiOperation(value = "列出已签收的Order")
    @RequestMapping(value = "/page_sign", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderSet>> selectPageBySIGN(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                             @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_SIGN));
    }

}
