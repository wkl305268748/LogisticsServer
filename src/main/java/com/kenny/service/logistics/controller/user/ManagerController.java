package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.service.user.UserInfoService;
import com.kenny.service.logistics.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * Created by Administrator on 2016/11/4.
 */
@Api(value = "/v1/manager", description = "用户管理模块")
@RequestMapping(value = "/v1/manager")
@RestController
public class ManagerController {
    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<User>> UserList(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                 @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return new JsonBean(UserErrorCode.SUCCESS, userService.GetUserList(offset,pageSize));
    }

    @ApiOperation(value = "获取用户详细信息列表")
    @RequestMapping(value = "/usersets", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<UserSet>> UserSetList(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                       @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return new JsonBean(UserErrorCode.SUCCESS, userService.GetUserSetList(offset,pageSize));
    }

    @ApiOperation(value = "增加用户")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<User> UserAdd(){
        return null;
    }

    @ApiOperation(value = "通过ID获取用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<User> UserGet(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id){
        try {
            return new JsonBean(UserErrorCode.SUCCESS, userService.GetUser(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "通过ID获取用户信息")
    @RequestMapping(value = "/users/{id}/info", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<UserInfo> UserInfoGet(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id){
        try {
            return new JsonBean(UserErrorCode.SUCCESS, userInfoService.GetUserInfo(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "修改用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<User> UserEdit(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id,
                                   @ApiParam(value = "手机号", required = true) @RequestParam String phone,
                                   @ApiParam(value = "登录用户名", required = true) @RequestParam String username){
        try {
            return new JsonBean(UserErrorCode.SUCCESS,userService.EditUser(id,phone,username));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/users/{id}/password", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<User> UserPass(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id,
                                   @ApiParam(value = "密码", required = true) @RequestParam String password){
        try {
            return new JsonBean(UserErrorCode.SUCCESS,userService.ResetPassword(id,password));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "修改用户信息")
    @RequestMapping(value = "/users/{id}/info", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<UserInfo> UserEditInfo(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id,
                                           @ApiParam(value = "昵称", required = true) @RequestParam String nickname,
                                           @ApiParam(value = "性别", required = true) @RequestParam String sex,
                                           @ApiParam(value = "头像", required = true) @RequestParam String img,
                                           @ApiParam(value = "生日", required = true) @RequestParam long birthday){
        try {
            return new JsonBean(UserErrorCode.SUCCESS,userInfoService.UpdateUserInfo(id,nickname,sex,img,new Date(birthday)));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean UserDelete(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id){
        try {
            userService.DeleteUser(id);
            userInfoService.DeleteUserInfo(id);
            return new JsonBean(UserErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}
