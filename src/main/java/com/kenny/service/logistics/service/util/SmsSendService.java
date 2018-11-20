package com.kenny.service.logistics.service.util;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.util.AliSmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/7/27.
 */
@Service
public class SmsSendService {
    @Autowired
    RestTemplate restTemplate;

    String signame = "蓝云物流科技";

    public void OrderToDriver(String phone,String order,String from,String to) throws ErrorCodeException {
        /*
        String template_code = "SMS_79075081";
        AliSmsUtil aliSmsUtil = new AliSmsUtil();
        Map<String,String> template = new HashMap<>();
        template.put("order",order);
        template.put("from",from);
        template.put("to",to);
        aliSmsUtil.sendSms(phone,signame,template_code,template);*/


        String uri = String.format("http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-WSS-010-11572&pwd=BFA87FC266BFC933C1F560EDF1B74D80&mobile={1}&content={0}&ext=&stime=&rrid=&msgfmt=","【飞通供应链】您的订单："+order+"从"+from+"出发，到"+to,phone);
        String strbody = restTemplate.getForObject(uri, String.class);
    }

    public void UserRegistString(String phone,String code) throws ErrorCodeException {
        /*
        String template_code = "SMS_78890304";
        AliSmsUtil aliSmsUtil = new AliSmsUtil();
        Map<String,String> template = new HashMap<>();
        template.put("code",code);
        aliSmsUtil.sendSms(phone,signame,template_code,template);*/


        String uri = String.format("http://sdk.entinfo.cn:8061/webservice.asmx/mdsmssend?sn=SDK-WSS-010-11572&pwd=BFA87FC266BFC933C1F560EDF1B74D80&mobile={1}&content={0}&ext=&stime=&rrid=&msgfmt=","【飞通供应链】您的验证码是："+code,phone);
        String strbody = restTemplate.getForObject(uri, String.class);
    }
}
