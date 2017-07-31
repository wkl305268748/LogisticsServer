package com.kenny.service.logistics.controller.util;

import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.json.JsonBean;
import com.kenny.service.logistics.model.user.User;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kenny on 2017/7/23.
 */


@Api(value = "/v1/util", description = "帮助模块")
@RequestMapping(value = "/v1/util")
@RestController
public class UploadController {

    @ApiOperation(value = "获取凭证")
    @RequestMapping(value = "/upload/token", method = RequestMethod.GET)
    @ResponseBody
    public JsonBean<String> Token() {
        String accessKey = "Ki58ozMysYhc5V63CwNBIJm7FAzwV_kw8lbGITDD";
        String secretKey = "G8AsbOnpAZrxPuCFTa-K02P5c6khdL6ja6wFM4SN";
        String bucket = "logistor";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return new JsonBean(ErrorCode.SUCCESS,upToken);

    }
}
