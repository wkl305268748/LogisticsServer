package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.user.Sms;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.model.user.UserToken;
import com.kenny.service.logistics.service.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "/v1/user/customer", description = "客户接口模块")
@RequestMapping(value = "/v1/user/customer")
@RestController
public class CustomerController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserCustomerService userCustomerService;

    String type = "customer";

    @ApiOperation(value = "校验手机号码是否注册")
    @RequestMapping(value = "/check_phone", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean CheckPhone(@ApiParam(value = "需要校验的手机号码", required = true) @RequestParam String phone) {
        try {
            userService.CheckPhone(phone, type);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "发送验证码")
    @RequestMapping(value = "/send_sms", method = RequestMethod.POST)
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
    @RequestMapping(value = "/check_sms", method = RequestMethod.GET)
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

    @ApiOperation(value = "短信验证码创建用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody

    public JsonBean<UserToken> Register(@ApiParam("与验证码绑定的cookie") @RequestParam(required = true) String cookie,
                                        @ApiParam("验证码") @RequestParam(required = true) String code,
                                        @ApiParam("手机号码") @RequestParam(required = true) String phone,
                                        @ApiParam("密码") @RequestParam(required = false) String password) {
        try {
            //校验验证码
            smsService.CheckCode(cookie, code);
            //用户创建
            User user = userService.CreateUser(phone, password, type);
            //用户信息创建
            userInfoService.insertByRegist(user.getId());
            //用户登陆
            UserToken bean = userLoginService.LoginSms(phone, type);
            return new JsonBean(UserErrorCode.SUCCESS, bean);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "校验手机号与密码")
    @RequestMapping(value = "/check_phone_pass", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean CheckPhonePass(@ApiParam("手机号") @RequestParam(required = true) String phone,
                                   @ApiParam("密码") @RequestParam(required = true) String password) {
        try {
            userService.CheckPhonePassword(phone, password, type);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean ResetPassword(@ApiParam("用户登录令牌") @RequestParam(required = true) String token,
                                  @ApiParam("重置密码") @RequestParam(required = true) String password) {
        try {
            User user = userLoginService.CheckToken(token);
            userService.ResetPassword(user.getId(), password);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "手机号和密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<UserToken> login(@ApiParam("手机号") @RequestParam(required = true) String phone,
                                     @ApiParam("密码") @RequestParam(required = true) String password) {

        try {
            //校验密码
            userService.CheckPhonePassword(phone, password, type);
            return new JsonBean(UserErrorCode.SUCCESS, userLoginService.LoginPhonePass(phone, type));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "账户和密码登录")
    @RequestMapping(value = "/loginex", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<UserToken> loginEx(@ApiParam("账户") @RequestParam(required = true) String username,
                                       @ApiParam("密码") @RequestParam(required = true) String password) {

        try {
            return new JsonBean(UserErrorCode.SUCCESS, userCustomerService.login(username, password));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "短信验证码登录")
    @RequestMapping(value = "/login_sms", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<UserToken> login_sms(@ApiParam("与验证码绑定的cookie") @RequestParam(required = true) String cookie,
                                         @ApiParam("验证码") @RequestParam(required = true) String code,
                                         @ApiParam("手机号码") @RequestParam(required = true) String phone) {
        try {
            //校验验证码
            smsService.CheckCode(cookie, code);
            return new JsonBean(UserErrorCode.SUCCESS, userLoginService.LoginSms(phone, type));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean LogOut(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {

        try {
            userLoginService.Logout(token);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "校验Token，通过Token获取用户信息")
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<User> Token(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {
        try {
            User user = userLoginService.CheckToken(token);
            return new JsonBean(UserErrorCode.SUCCESS, user);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "获取用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserInfo> info_get(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {
        try {
            User user = userLoginService.CheckToken(token);
            return new JsonBean(UserErrorCode.SUCCESS, userInfoService.GetUserInfo(user.getId()));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<UserInfo> updateInfo(@ApiParam("用户登录令牌") @RequestParam(required = true) String token,
                                        @ApiParam("昵称") @RequestParam(required = false) String nickname,
                                        @ApiParam("性别") @RequestParam(required = false) String sex,
                                        @ApiParam("上传图像地址") @RequestParam(required = false) String img,
                                        @ApiParam("生日") @RequestParam(required = false) Date birthday,
                                        @ApiParam("") @RequestParam(required = false) String company,
                                        @ApiParam("") @RequestParam(required = false) int money) {
        try {
            User user = userLoginService.CheckToken(token);
            return new JsonBean(UserErrorCode.SUCCESS, userInfoService.update(user.getId(), nickname, sex, img, birthday, company, money));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

}

