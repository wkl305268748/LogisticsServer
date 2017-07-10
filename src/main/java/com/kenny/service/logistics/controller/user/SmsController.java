package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.user.Sms;
import com.kenny.service.logistics.service.user.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/v1/sms", description = "短信发送模块")
@RequestMapping(value = "/v1/sms")
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    @ApiOperation(value = "发送验证码")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Sms> SmsSend(@ApiParam(value = "需要发送短信的手机号", required = true) @RequestParam String phone,
                                 @ApiParam(value = "短信类型", required = true) @RequestParam String type) {

        try {
            Sms bean = smsService.SendMessage(phone, type);
            return new JsonBean(UserErrorCode.SUCCESS, bean);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "校验短信验证码")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean SmsCheck(@ApiParam(value = "与验证码绑定的cookie", required = true) @RequestParam String cookie,
                             @ApiParam(value = "验证码", required = true) @RequestParam String code) {

        try {
            smsService.CheckCode(cookie, code);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}

