package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.service.user.SmsService;
import com.kenny.service.logistics.service.user.UserInfoService;
import com.kenny.service.logistics.service.user.UserLoginService;
import com.kenny.service.logistics.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "/v1/user", description = "用户接口模块")
@RequestMapping(value = "/v1/user")
@RestController
public class UserController {
    @Autowired
    private SmsService smsService;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "校验手机号码是否注册")
    @RequestMapping(value = "/check_phone", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean PhoneCheck(@ApiParam(value = "需要校验的手机号码", required = true) @RequestParam String phone) {
        try {
            userService.RegisterPhoneCheck(phone);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    /*

    @ApiOperation(value = "短信验证码创建用户")
    @RequestMapping(value = "/create_sms", method = RequestMethod.POST)
    @ResponseBody

    public JsonBean<PasssmsResponse> CreateSms(@ApiParam("与验证码绑定的cookie") @RequestParam(required = true) String cookie,
                                               @ApiParam("验证码") @RequestParam(required = true) String code,
                                               @ApiParam("手机号码") @RequestParam(required = true) String phone,
                                               @ApiParam("密码") @RequestParam(required = false) String password) {
        try {
            //校验验证码
            smsService.CheckCode(cookie, code);
            //用户创建
            User user = userService.CreateUser(phone,password);
            //用户信息创建
            userInfoService.CreateUserInfo(user.getId(),phone,"男",null,null);
            //用户登陆
            PasssmsResponse bean = userLoginService.LoginSms(phone);
            return new JsonBean(UserErrorCode.SUCCESS, bean);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "用户名密码创建用户")
    @RequestMapping(value = "/create_pass", method = RequestMethod.POST)
    @ResponseBody

    public JsonBean<PasswordResponse> CreatePass(@ApiParam("用户名") @RequestParam(required = true) String name,
                                                 @ApiParam("密码") @RequestParam(required = true) String password) {
        try {
            //用户创建
            User user = userService.CreateUser(name, password);
            //用户信息创建
            userInfoService.CreateUserInfo(user.getId(),name,"男",null,null);
            //用户登陆
            PasswordResponse bean = userLoginService.LoginPass(name);
            return new JsonBean(UserErrorCode.SUCCESS, bean);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

*/
    @ApiOperation(value = "校验用户名与密码")
    @RequestMapping(value = "/check_password", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean PasswordCheck(@ApiParam("用户名") @RequestParam(required = true) String username,
                                  @ApiParam("密码") @RequestParam(required = true) String password) {
        try {
            userService.CheckUserPassword(username, password);
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
    public JsonBean<UserInfo> info_edit(@ApiParam("用户登录令牌") @RequestParam(required = true) String token,
                                        @ApiParam("昵称") @RequestParam(required = false) String nickname,
                                        @ApiParam("性别") @RequestParam(required = false) String sex,
                                        @ApiParam("上传图像地址") @RequestParam(required = false) String img,
                                        @ApiParam("生日") @RequestParam(required = false) long birthday) {
        try {
            User user = userLoginService.CheckToken(token);
            return new JsonBean(UserErrorCode.SUCCESS,userInfoService.UpdateUserInfo(user.getId(), nickname, sex, img,new Date(birthday)));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "根据手机号获取指定用户信息")
    @RequestMapping(value = "/info_phone", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserInfo> info_phone(@ApiParam("用户登录令牌") @RequestParam(required = true)String token,
                                            @ApiParam("用户手机号") @RequestParam(required = true)String phone){
        try {
            return new JsonBean(UserErrorCode.SUCCESS,userInfoService.GetUserInfoByPhone(phone));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

}

