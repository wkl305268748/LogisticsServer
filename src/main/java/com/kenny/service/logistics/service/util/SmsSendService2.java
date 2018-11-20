package com.kenny.service.logistics.service.util;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.util.AliSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/7/27.
 */
@Service
public class SmsSendService2 {

    @Autowired
    RestTemplate restTemplate;

    public void SendCode(String phone,String code) throws ErrorCodeException {
        String uri = String.format("http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-WSS-010-11572&pwd=BFA87FC266BFC933C1F560EDF1B74D80&mobile={1}&content={0}&ext=&stime=&rrid=&msgfmt=","【飞通供应链】您的验证码是："+code,phone);
        String strbody = restTemplate.getForObject(uri, String.class);
    }
}
