package com.kenny.service.logistics.service.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.model.po.user.UserSet;
import com.kenny.service.logistics.model.po.user.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kenny on 2017/7/20.
 */

@Service
public class UserCompanyService {
    @Autowired
    UserBaseService userBaseService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserMoneyService userMoneyService;

    public static final String type = "company";

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

    //重置密码
    public void updatePassword(String token, String old_password, String new_password) throws ErrorCodeException {
        userBaseService.updatePassword(token,old_password,new_password);
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

    //根据token获取用户
    public User getUserByToken(String token) throws ErrorCodeException {
        return userBaseService.getUserByToken(token);
    }

    //根据token获取用户ex
    public UserSet getUserByTokenEx(String token) throws ErrorCodeException {
        return userBaseService.getUserByTokenEx(token);
    }

    //减少金钱
    public void reduceMoney(String token,Float money,String remark) throws ErrorCodeException {
        User user = userBaseService.getUserByToken(token);
        userInfoService.updateReduceMoney(user.getId(),money);
        userMoneyService.insert(user.getId(),money,"out",remark);
    }

    //校验金钱
    public void checkMoney(String token,Float money) throws ErrorCodeException {
        userInfoService.checkMoney(userBaseService.getUserByToken(token).getId(),money);
    }

    public int getCount(){
        return userBaseService.getCount(type);
    }

}
