package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.po.user.*;
import com.kenny.service.logistics.service.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "/v1/user/admin", description = "管理员用户接口模块")
@RequestMapping(value = "/v1/user/admin")
@RestController
public class AdminController {
    @Autowired
    private UserAdminService userAdminService;
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "账户密码登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<UserToken> CheckPhonePass(@ApiParam("账户") @RequestParam(required = true) String username,
                                              @ApiParam("密码") @RequestParam(required = true) String password) {
        try {
            return new JsonBean(UserErrorCode.SUCCESS, userAdminService.login(username, password));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean LogOut(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {

        try {
            userAdminService.logout(token);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "获取用户所有信息")
    @RequestMapping(value = "/ex/info", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserSet> InfoEx(@ApiParam("用户登录令牌") @RequestParam(required = true) String token) {

        try {
            return new JsonBean(UserErrorCode.SUCCESS,userAdminService.getUserEx(token));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<UserInfo> updateInfo(@ApiParam("用户登录令牌") @RequestParam(required = true) String token,
                                         @ApiParam("") @RequestParam(required = false) String nickname,
                                         @ApiParam("") @RequestParam(required = false) String sex,
                                         @ApiParam("") @RequestParam(required = false) String img,
                                         @ApiParam("") @RequestParam(required = false) Date birthday,
                                         @ApiParam("") @RequestParam(required = false) String company,
                                         @ApiParam("") @RequestParam(required = false) Float money) {
        try {
            return new JsonBean(UserErrorCode.SUCCESS,userAdminService.updateUserInfo(token,nickname,sex,img,birthday,company,money));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean UpdatePassword(@ApiParam("用户登录令牌") @RequestParam(required = true) String token,
                                   @ApiParam("历史密码") @RequestParam(required = true) String old_password,
                                   @ApiParam("新密码") @RequestParam(required = true) String new_password) {
        try {
            userAdminService.updatePassword(token, old_password, new_password);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}

