package com.kenny.service.logistics.controller.user;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.json.response.SelectOptionResponse;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.service.system.SystemConfigService;
import com.kenny.service.logistics.service.user.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2016/11/4.
 */
@Api(value = "/v1/user/manager", description = "用户管理模块")
@RequestMapping(value = "/v1/user/manager")
@RestController
public class ManagerController {
    @Autowired
    UserManagerService userManagerService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserCustomerService userCustomerService;
    @Autowired
    UserMoneyService userMoneyService;
    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    UserAdminService userAdminService;

    @ApiOperation(value = "获取用户列表")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<User>> UserList(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                 @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean(UserErrorCode.SUCCESS, userManagerService.selectPage(offset, pageSize));
    }

    @ApiOperation(value = "获取用户详细信息列表")
    @RequestMapping(value = "/usersets", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<UserSet>> UserSetList(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                       @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean(UserErrorCode.SUCCESS, userManagerService.GetUserSetList(offset, pageSize));
    }

    @ApiOperation(value = "获取客户类型用户详细信息列表")
    @RequestMapping(value = "/ex/customer", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<UserSet>> CustomerInfoListEx(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                              @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean(UserErrorCode.SUCCESS, userManagerService.selectPageByTypeEx(offset, pageSize, "customer"));
    }

    @ApiOperation(value = "获取物流公司类型用户详细信息列表")
    @RequestMapping(value = "/ex/company", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<UserSet>> CompanyInfoListEx(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                              @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean(UserErrorCode.SUCCESS, userManagerService.selectPageByTypeEx(offset, pageSize, "company"));
    }

    @ApiOperation(value = "获取物流公司列表（下拉菜单选项）")
    @RequestMapping(value = "company/select", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<List<SelectOptionResponse>> CompanyInfoListSelect() {
        return new JsonBean(UserErrorCode.SUCCESS, userManagerService.selectPageBySelectOption("company"));
    }

    @ApiOperation(value = "获取管理员类型用户详细信息列表")
    @RequestMapping(value = "/ex/admin", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<UserSet>> AdminInfoListEx(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                           @ApiParam(value = "每页数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean(UserErrorCode.SUCCESS, userManagerService.selectPageByTypeEx(offset, pageSize, "admin"));
    }

    @ApiOperation(value = "增加客户类型用户")
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<User> CustomerAdd(@ApiParam("账户") @RequestParam String username,
                                      @ApiParam("密码") @RequestParam String password,
                                      @ApiParam("昵称") @RequestParam(required = false) String nickname,
                                      @ApiParam("性别") @RequestParam(required = false) String sex,
                                      @ApiParam("上传图像地址") @RequestParam(required = false) String img,
                                      @ApiParam("公司名称") @RequestParam String company,
                                      @ApiParam("金额") @RequestParam Float money) {
        try {
            User user = userCustomerService.insertUserName(username,password);
            userInfoService.insert(user.getId(),nickname,sex,img,null,company,money);
            systemConfigService.init(user.getId());
            return new JsonBean(ErrorCode.SUCCESS,user);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean CustomerDelete(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id) {
        userManagerService.deleteByPrimaryKey(id);
        return new JsonBean(UserErrorCode.SUCCESS);
    }

    @ApiOperation(value = "删除公司")
    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean CompanyDelete(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id) {
        userManagerService.deleteByPrimaryKey(id);
        return new JsonBean(UserErrorCode.SUCCESS);
    }

    @ApiOperation(value = "用户充值")
    @RequestMapping(value = "/customer/money/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean CustomerMoney(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id,
                                  @ApiParam("金额") @RequestParam Float money) {
        try {
            //充值
            userInfoService.updateAddMoney(id,money);
            //添加记录
            userMoneyService.insert(id,money,"in","");
            return new JsonBean(ErrorCode.SUCCESS);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "客户重置密码")
    @RequestMapping(value = "/customer/password/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean CustomerPassword(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id,
                                     @ApiParam(value = "密码", required = true) @RequestParam String password) {
        try {
            return new JsonBean(UserErrorCode.SUCCESS, userManagerService.resetPassword(id, password));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "增加客户类型用户")
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<User> AdminAdd(@ApiParam("账户") @RequestParam String username,
                                  @ApiParam("密码") @RequestParam String password,
                                  @ApiParam("昵称") @RequestParam(required = false) String nickname,
                                  @ApiParam("性别") @RequestParam(required = false) String sex,
                                  @ApiParam("上传图像地址") @RequestParam(required = false) String img) {
        try {
            User user = userAdminService.insertUserName(username,password);
            userInfoService.insert(user.getId(),nickname,sex,img,null,"",0f);
            return new JsonBean(ErrorCode.SUCCESS,user);
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }


    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean AdminDelete(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id) {
        userManagerService.deleteByPrimaryKey(id);
        return new JsonBean(UserErrorCode.SUCCESS);
    }

    @ApiOperation(value = "管理员重置密码")
    @RequestMapping(value = "/admin/password/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean AdminPassword(@ApiParam(value = "用户ID", required = true) @PathVariable Integer id,
                                     @ApiParam(value = "密码", required = true) @RequestParam String password) {
        try {
            return new JsonBean(UserErrorCode.SUCCESS, userManagerService.resetPassword(id, password));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }
}
