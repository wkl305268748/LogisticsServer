package com.kenny.service.logistics.controller.fleet;

import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.service.fleet.DriverLicenseService;
import com.kenny.service.logistics.service.fleet.LicenseService;
import com.kenny.service.logistics.service.user.UserDriverService;
import com.kenny.service.logistics.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.model.fleet.Driver;
import com.kenny.service.logistics.service.fleet.DriverService;

@Api(value = "/v1/fleet/driver", description = "司机模块")
@RequestMapping(value = "/v1/fleet/driver")
@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private UserDriverService userDriverService;
    @Autowired
    private DriverLicenseService driverLicenseService;
    @Autowired
    private LicenseService licenseService;

    @ApiOperation(value = "增加Driver")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Driver> Insert(@ApiParam(value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
                                   @ApiParam(value = "", required = false) @RequestParam(value = "sex", required = false) String sex,
                                   @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                   @ApiParam(value = "APP账户密码", required = true) @RequestParam(value = "password", required = true) String password,
                                   @ApiParam(value = "是否短信通知司机", required = true) @RequestParam(value = "is_sms", required = true) Boolean is_sms,
                                   @ApiParam(value = "身份证号码", required = false) @RequestParam(value = "idcard", required = false) String idcard,
                                   @ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
                                   @ApiParam(value = "籍贯", required = false) @RequestParam(value = "hometown", required = false) String hometown,
                                   @ApiParam(value = "", required = false) @RequestParam(value = "remark", required = false) String remark) {
        try {
            User user = userDriverService.insert(phone, password);
            return new JsonBean(ErrorCode.SUCCESS, driverService.insert(name, sex, phone, user.getId(), is_sms, idcard, email, hometown, remark));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "增加Driver带驾驶证和证件信息")
    @RequestMapping(value = "/ex", method = RequestMethod.POST)
    @ResponseBody
    public JsonBean<Driver> InsertEx(@ApiParam(value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
                                     @ApiParam(value = "", required = false) @RequestParam(value = "sex", required = false) String sex,
                                     @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                     @ApiParam(value = "APP账户密码", required = true) @RequestParam(value = "password", required = true) String password,
                                     @ApiParam(value = "是否短信通知司机", required = true) @RequestParam(value = "is_sms", required = true) Boolean is_sms,
                                     @ApiParam(value = "身份证号码", required = false) @RequestParam(value = "idcard", required = false) String idcard,
                                     @ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
                                     @ApiParam(value = "籍贯", required = false) @RequestParam(value = "hometown", required = false) String hometown,
                                     @ApiParam(value = "", required = false) @RequestParam(value = "remark", required = false) String remark,
                                     @ApiParam(value = "", required = false) @RequestParam(value = "other_license[]", required = false) String other_license) {

        licenseService.insertByDriver(other_license,0);
        return new JsonBean(ErrorCode.SUCCESS );
//        try {
//            User user = userDriverService.insert(phone, password);
//            Driver driver = driverService.insert(name, sex, phone, user.getId(), is_sms, idcard, email, hometown, remark);
//            licenseService.insertByDriver(other_license,driver.getId());
//            return new JsonBean(ErrorCode.SUCCESS, driver );
//
//        } catch (ErrorCodeException e) {
//            return new JsonBean(e.getErrorCode());
//        }
    }

    @ApiOperation(value = "修改指定的Driver")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonBean<Driver> update(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id,
                                   @ApiParam(value = "姓名", required = true) @RequestParam(value = "name", required = true) String name,
                                   @ApiParam(value = "", required = false) @RequestParam(value = "sex", required = false) String sex,
                                   @ApiParam(value = "手机号", required = true) @RequestParam(value = "phone", required = true) String phone,
                                   @ApiParam(value = "司机APP客户端密码，默认123456", required = true) @RequestParam(value = "password", required = true) String password,
                                   @ApiParam(value = "是否短信通知司机", required = true) @RequestParam(value = "is_sms", required = true) Boolean is_sms,
                                   @ApiParam(value = "身份证号码", required = false) @RequestParam(value = "idcard", required = false) String idcard,
                                   @ApiParam(value = "邮箱", required = false) @RequestParam(value = "email", required = false) String email,
                                   @ApiParam(value = "籍贯", required = false) @RequestParam(value = "hometown", required = false) String hometown,
                                   @ApiParam(value = "", required = false) @RequestParam(value = "remark", required = false) String remark) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, driverService.update(id, name, sex, phone, 0, is_sms, idcard, email, hometown, remark));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "获取指定的Driver")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<Driver> selectByPrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) {
        try {
            return new JsonBean(ErrorCode.SUCCESS, driverService.selectByPrimaryKey(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

    @ApiOperation(value = "列出所有的有效Driver")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<PageResponse<Driver>> selectPage(@ApiParam(value = "从第几个开始列出") @RequestParam(required = false, defaultValue = "0") Integer offset,
                                                     @ApiParam(value = "每页内容数量") @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return new JsonBean(ErrorCode.SUCCESS, driverService.selectPage(offset, pageSize));
    }

    @ApiOperation(value = "删除指定的Driver")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonBean deletePrimaryKey(@ApiParam(value = "查询主键", required = true) @PathVariable() Integer id) {
        try {
            userDriverService.deleteByPrimaryKey(driverService.selectByPrimaryKey(id).getFk_user_id());
            return new JsonBean(ErrorCode.SUCCESS, driverService.deleteByPrimaryKey(id));
        } catch (ErrorCodeException e) {
            return new JsonBean(e.getErrorCode());
        }
    }

}
