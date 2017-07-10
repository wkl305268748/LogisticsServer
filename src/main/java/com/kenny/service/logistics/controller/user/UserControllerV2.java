package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.user.UserToken;
import com.kenny.service.logistics.service.user.SmsService;
import com.kenny.service.logistics.service.user.UserLoginService;
import com.kenny.service.logistics.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "/v2/user", description = "用户接口模块V2")
@RequestMapping(value = "/v2/user")
@RestController
public class UserControllerV2 {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "手机号和密码登录")
    @RequestMapping(value = "/login_phone", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserToken> login_phone(@ApiParam("手机号") @RequestParam(required = true) String phone,
                                           @ApiParam("密码") @RequestParam(required = true) String password) {

        try {
            //校验密码
            userService.CheckPhonePassword(phone, password);
            return new JsonBean(UserErrorCode.SUCCESS, userLoginService.LoginPhonePass(phone));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "用户名和密码登录")
    @RequestMapping(value = "/login_user", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserToken> login_user(@ApiParam("用户名") @RequestParam(required = true) String username,
                                                 @ApiParam("密码") @RequestParam(required = true) String password) {

        try {
            //校验密码
            userService.CheckUserPassword(username, password);
            return new JsonBean(UserErrorCode.SUCCESS, userLoginService.LoginUserPass(username));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "短信验证码登录")
    @RequestMapping(value = "/login_sms", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserToken> login_sms(@ApiParam("与验证码绑定的cookie") @RequestParam(required = true) String cookie,
                                         @ApiParam("验证码") @RequestParam(required = true) String code,
                                         @ApiParam("手机号码") @RequestParam(required = true) String phone) {
        try {
            //校验验证码
            smsService.CheckCode(cookie, code);
            return new JsonBean(UserErrorCode.SUCCESS, userLoginService.LoginSms(phone));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}

