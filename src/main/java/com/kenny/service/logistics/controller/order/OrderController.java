package com.kenny.service.logistics.controller.order;


import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.model.order.OrderCustomer;
import com.kenny.service.logistics.model.system.Defind;
import com.kenny.service.logistics.service.order.OrderService;
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

    @ApiOperation(value = "列出所有的Order")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderCustomer>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                   @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPage(offset, pageSize));
    }

    @ApiOperation(value = "列出待处理的Order")
    @RequestMapping(value = "/page_place", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderCustomer>> selectPageByPLACE(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                   @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_PLACE));
    }


    @ApiOperation(value = "列出被拒绝的Order")
    @RequestMapping(value = "/page_refuse", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderCustomer>> selectPageByREFUSE(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                    @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_REFUSE));
    }

    @ApiOperation(value = "列出已处理的Order")
    @RequestMapping(value = "/page_taking", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderCustomer>> selectPageByTAKING(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                    @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_TAKING));
    }

    @ApiOperation(value = "列出已签收的Order")
    @RequestMapping(value = "/page_sign", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<OrderCustomer>> selectPageBySIGN(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                                  @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) throws ErrorCodeException {
        return new JsonBean(ErrorCode.SUCCESS, orderService.selectPageByStatus(offset, pageSize, Defind.ORDER_SIGN));
    }
}
