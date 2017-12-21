package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.user.*;
import com.kenny.service.logistics.service.user.SmsService;
import com.kenny.service.logistics.service.user.UserCustomerService;
import com.kenny.service.logistics.service.user.UserDriverService;
import com.kenny.service.logistics.service.user.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "/v1/user/driver", description = "客户接口模块")
@RequestMapping(value = "/v1/user/driver")
@RestController
public class DriverController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserDriverService userDriverService;
    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation(value = "手机号和密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<UserToken> login(@ApiParam("手机号") @RequestParam(required = true) String phone,
                                     @ApiParam("密码") @RequestParam(required = true) String password) {

        try {
            return new JsonBean(UserErrorCode.SUCCESS, userDriverService.login(phone, password));
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
            return new JsonBean(UserErrorCode.SUCCESS, userDriverService.login(username, password));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean LogOut(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {

        try {
            userDriverService.logout(token);
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
            return new JsonBean(UserErrorCode.SUCCESS, userDriverService.getUserByToken(token));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "获取用户所有信息")
    @RequestMapping(value = "/ex/info", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserSet> InfoEx(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {

        try {
            return new JsonBean(UserErrorCode.SUCCESS,userDriverService.getUserByTokenEx(token));
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
                                        @ApiParam("") @RequestParam(required = false) Float money) {
        try {
            User user = userDriverService.getUserByToken(token);
            return new JsonBean(UserErrorCode.SUCCESS, userInfoService.update(user.getId(), nickname, sex, img, birthday, company, money));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

}

