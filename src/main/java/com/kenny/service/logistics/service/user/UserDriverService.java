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
public class UserDriverService {
    @Autowired
    private UserBaseService userBaseService;

    private final String type = "driver";

    /**
     * 根据手机号创建用户
     *
     * @param phone
     * @return
     */
    public User insert(String phone,String password) throws ErrorCodeException {
        return userBaseService.insertByPhone(phone,password,type);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws ErrorCodeException
     */
    public UserToken login(String username, String password) throws ErrorCodeException {
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

    //重置密码
    public void updatePassword(String token, String old_password, String new_password) throws ErrorCodeException {
        userBaseService.updatePassword(token,old_password,new_password);
    }

    //根据token获取用户
    public User getUserByToken(String token) throws ErrorCodeException {
        return userBaseService.getUserByToken(token);
    }

    //根据token获取用户ex
    public UserSet getUserByTokenEx(String token) throws ErrorCodeException {
        return userBaseService.getUserByTokenEx(token);
    }
}
