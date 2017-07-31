package com.kenny.service.logistics.service.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.mapper.user.UserTokenMapper;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.model.user.UserToken;
import com.kenny.service.logistics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Kenny on 2017/7/20.
 */

@Service
public class UserCustomerService {
    @Autowired
    UserBaseService userBaseService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserMoneyService userMoneyService;

    public static final String type = "customer";

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws ErrorCodeException
     */
    public UserToken login(String username,String password) throws ErrorCodeException {
        return userBaseService.loginUserName(username,password,type);
    }

    /**
     * 退出登陆
     *
     * @param token
     * @return
     */
    public void logout(String token) throws ErrorCodeException {
        userBaseService.logout(token);
    }

    /**
     * 校验手机号是否已经注册
     * @param phone
     */
    public void checkPhoneHas(String phone) throws ErrorCodeException {
        userBaseService.checkPhoneHas(phone,type);
    }

    /**
     * 根据手机号创建用户
     *
     * @param phone
     * @return
     */
    public User insertPhone(String phone,String password) throws ErrorCodeException {
        return userBaseService.insertByPhone(phone,password,type);
    }

    /**
     * 根据用户名密码创建用户
     *
     * @param username
     * @return
     */
    public User insertUserName(String username,String password) throws ErrorCodeException {
        return userBaseService.insertByUserName(username,password,type);
    }

    public User getUserByToken(String token) throws ErrorCodeException {
        return userBaseService.getUserByToken(token);
    }

    //减少金钱
    public void reduceMoney(String token,int money,String remark) throws ErrorCodeException {
        User user = userBaseService.getUserByToken(token);
        userInfoService.updateReduceMoney(user.getId(),money);
        userMoneyService.insert(user.getId(),money,"out",remark);
    }

    //校验金钱
    public void checkMoney(String token,int money) throws ErrorCodeException {
        userInfoService.checkMoney(userBaseService.getUserByToken(token).getId(),money);
    }

}
