package com.kenny.service.logistics.util;


import com.google.gson.Gson;
import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 采用阿里大于短信平台
 * http://www.alidayu.com/
 */
public class AliSmsUtil {

    private static String url = "http://gw.api.taobao.com/router/rest";
    private static String appkey;
    private static String secret;

    private static AliSmsUtil instance = null;

    public static AliSmsUtil getInstance(){
        if(instance == null)
            instance = new AliSmsUtil();
        return instance;
    }

    private AliSmsUtil(){
        secret = SystemConfigUtil.getProperty("alidayu.master_secret");
        appkey = SystemConfigUtil.getProperty("alidayu.app_key");
    }

    public void sendCode(String phone, String signame, Map<String, String> template, String template_id, String code) throws ErrorCodeException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName(signame);
        //Json拼接
        Map<String, String> sms_param = new HashMap<>();
        sms_param.put("code", code);
        for (Map.Entry<String, String> entry : template.entrySet()) {
            sms_param.put(entry.getKey(), entry.getValue());
        }
        req.setSmsParamString(new Gson().toJson(sms_param));
        req.setRecNum(phone);
        req.setSmsTemplateCode(template_id);

        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            throw new ErrorCodeException(new ErrorCode(0, "阿里大于异常：" + e.getErrCode() + "-" + e.getErrMsg()));
        }

        if (!rsp.getResult().getSuccess())
            throw new ErrorCodeException(new ErrorCode(0, "阿里大于异常：" + rsp.getMsg() + " " + rsp.getSubMsg()));
    }

}
