package com.kenny.service.logistics.controller.util;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.service.util.SmsSendService;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kenny on 2017/7/23.
 */


@Api(value = "/v1/util", description = "帮助模块")
@RequestMapping(value = "/v1/util")
@RestController
public class SmsSendController {
    @Autowired
    SmsSendService smsSendService;

    @ApiOperation(value = "发送订单")
    @RequestMapping(value = "/sms/order_to_driver", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean OrderToDriver(String phone,String from,String to,String order) {
        try {
            smsSendService.OrderToDriver(phone,order,from,to);
            return new JsonBean(ErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}
