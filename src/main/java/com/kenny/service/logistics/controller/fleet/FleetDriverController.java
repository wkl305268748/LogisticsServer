package com.kenny.service.logistics.controller.fleet;

import com.kenny.service.logistics.model.po.fleet.FleetDriverSet;
import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.service.fleet.FleetDriverLicenseService;
import com.kenny.service.logistics.service.fleet.FleetLicenseService;
import com.kenny.service.logistics.service.user.UserBaseService;
import com.kenny.service.logistics.service.user.UserDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.po.fleet.FleetDriver;
import com.kenny.service.logistics.service.fleet.FleetDriverService;

@Api(value = "/v1/fleet/driver", description = "司机模块")
@RequestMapping(value = "/v1/fleet/driver")
@RestController
public class FleetDriverController {
    @Autowired
    private FleetDriverService fleetDriverService;
    @Autowired
    private UserDriverService userDriverService;
    @Autowired
    private FleetDriverLicenseService fleetDriverLicenseService;
    @Autowired
    private FleetLicenseService fleetLicenseService;
    @Autowired
    private UserBaseService userBaseService;

    @ApiOperation(value = "增加Driver")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<FleetDriver> Insert(@ApiParam(value = "用户token",required = true)@RequestParam(value = "token",required = true)String token,
                                        @ApiParam(value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
                                        @ApiParam(value = "", required = false) @RequestParam(value = "sex", required = false) String sex,
                                        @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                        @ApiParam(value = "APP账户密码", required = true) @RequestParam(value = "password", required = true) String password,
                                        @ApiParam(value = "是否短信通知司机", required = true) @RequestParam(value = "is_sms", required = true) Boolean is_sms,
                                        @ApiParam(value = "身份证号码", required = false) @RequestParam(value = "idcard", required = false) String idcard,
                                        @ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
                                        @ApiParam(value = "籍贯", required = false) @RequestParam(value = "hometown", required = false) String hometown,
                                        @ApiParam(value = "", required = false) @RequestParam(value = "remark", required = false) String remark,
                                        @ApiParam(value = "银行卡号",required = false)@RequestParam(value = "bank_number",required = false)String bank_number,
                                        @ApiParam(value = "开户行",required = false)@RequestParam(value = "bank_addr",required = false)String bank_addr) {
        try {
            User user = userBaseService.getUserByToken(token);
            User driverUser = userDriverService.insert(phone, password);
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.insert(name, sex, phone, driverUser.getId(), is_sms, idcard, email, hometown, remark,user.getId(),bank_number,bank_addr));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "增加Driver带驾驶证和证件信息")
    @RequestMapping(value = "/ex", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public JsonBean<FleetDriver> InsertEx(@ApiParam(value = "用户token",required = true)@RequestParam(value = "token",required = true)String token,
                                          @ApiParam(value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
                                          @ApiParam(value = "", required = false) @RequestParam(value = "sex", required = false) String sex,
                                          @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                          @ApiParam(value = "APP账户密码", required = true) @RequestParam(value = "password", required = true) String password,
                                          @ApiParam(value = "是否短信通知司机", required = true) @RequestParam(value = "is_sms", required = true) Boolean is_sms,
                                          @ApiParam(value = "身份证号码", required = false) @RequestParam(value = "idcard", required = false) String idcard,
                                          @ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
                                          @ApiParam(value = "籍贯", required = false) @RequestParam(value = "hometown", required = false) String hometown,
                                          @ApiParam(value = "", required = false) @RequestParam(value = "remark", required = false) String remark,
                                          @ApiParam(value = "银行卡号",required = false)@RequestParam(value = "bank_number",required = false)String bank_number,
                                          @ApiParam(value = "开户行",required = false)@RequestParam(value = "bank_addr",required = false)String bank_addr,
                                          @ApiParam(value = "", required = false) @RequestParam(value = "driver_license", required = false) String driver_license,
                                          @ApiParam(value = "", required = false) @RequestParam(value = "other_license[]", required = false) String other_license) {

        try {
            User user = userBaseService.getUserByToken(token);
            User driverUser = userDriverService.insert(phone, password);
            FleetDriver fleetDriver = fleetDriverService.insert(name, sex, phone, driverUser.getId(), is_sms, idcard, email, hometown, remark, user.getId(),bank_number,bank_addr);
            if(other_license != null)
                fleetLicenseService.insertByDriver(other_license, fleetDriver.getId());
            if(driver_license != null)
                fleetDriverLicenseService.insertByDriver(driver_license, fleetDriver.getId());
            return new JsonBean(ErrorCode.SUCCESS, fleetDriver);

        } catch (ErrorCodeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "修改指定的Driver")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<FleetDriver> update(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id,
                                        @ApiParam(value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
                                        @ApiParam(value = "", required = false) @RequestParam(value = "sex", required = false) String sex,
                                        @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                        @ApiParam(value = "是否短信通知司机", required = true) @RequestParam(value = "is_sms", required = true) Boolean is_sms,
                                        @ApiParam(value = "身份证号码", required = false) @RequestParam(value = "idcard", required = false) String idcard,
                                        @ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
                                        @ApiParam(value = "籍贯", required = false) @RequestParam(value = "hometown", required = false) String hometown,
                                        @ApiParam(value = "", required = false) @RequestParam(value = "remark", required = false) String remark,
                                        @ApiParam(value = "银行卡号",required = false)@RequestParam(value = "bank_number",required = false)String bank_number,
                                        @ApiParam(value = "开户行",required = false)@RequestParam(value = "bank_addr",required = false)String bank_addr) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.update(id, name, sex, phone, is_sms, idcard, email, hometown, remark,bank_number,bank_addr));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "获取指定的Driver")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<FleetDriver> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.selectByPrimaryKey(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "获取指定的Driver带证件信息")
    @RequestMapping(value = "/ex/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<FleetDriverSet> selectByPrimaryKeyEx(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.selectByPrimaryKeyEx(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "列出所有的有效Driver")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<FleetDriver>> selectPage(@ApiParam(value = "用户token",required = true)@RequestParam(value = "token",required = true)String token,
                                                          @ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                          @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            User user = userBaseService.getUserByToken(token);
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.selectPageByBelongUser(offset, pageSize, user.getId()));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "列出所有的有效Driver")
    @RequestMapping(value = "/ex/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<FleetDriverSet>> selectPageEx(@ApiParam(value = "用户token",required = true)@RequestParam(value = "token",required = true)String token,
                                                               @ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                               @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        try {
            User user = userBaseService.getUserByToken(token);
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.selectPageByBelongUserEx(offset, pageSize, user.getId()));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "删除指定的Driver")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) {
        try {
            userBaseService.deleteByPrimaryKey(fleetDriverService.selectByPrimaryKey(id).getFk_user_id());
            return new JsonBean(ErrorCode.SUCCESS, fleetDriverService.deleteByPrimaryKey(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

}
