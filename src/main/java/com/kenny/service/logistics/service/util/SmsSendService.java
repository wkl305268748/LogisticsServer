package com.kenny.service.logistics.service.util;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.util.AliSmsUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kenny on 2017/7/27.
 */
@Service
public class SmsSendService {
    String signame = "蓝云物流科技";

    public void OrderToDriver(String phone,String order,String from,String to) throws ErrorCodeException {
        String template_code = "SMS_79075081";
        AliSmsUtil aliSmsUtil = new AliSmsUtil();
        Map<String,String> template = new HashMap<>();
        template.put("order",order);
        template.put("from",from);
        template.put("to",to);
        aliSmsUtil.sendSms(phone,signame,template_code,template);
    }

    public void UserRegistString(String phone,String code) throws ErrorCodeException {
        String template_code = "SMS_78890304";
        AliSmsUtil aliSmsUtil = new AliSmsUtil();
        Map<String,String> template = new HashMap<>();
        template.put("code",code);
        aliSmsUtil.sendSms(phone,signame,template_code,template);
    }
}
