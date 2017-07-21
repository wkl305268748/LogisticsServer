package com.kenny.service.logistics.service.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.mapper.user.UserTokenMapper;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserToken;
import com.kenny.service.logistics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Kenny on 2017/7/20.
 */

@Service
public class UserAdminService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    private final String type = "admin";

    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws ErrorCodeException
     */
    public UserToken login(String username,String password) throws ErrorCodeException {
        User user = userMapper.selectByUserName(username,type);
        if(user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        if(!password.equals(user.getPassword()))
            throw new ErrorCodeException(UserErrorCode.PASS_ERROR);
        if(user.getIs_disable())
            throw new ErrorCodeException(UserErrorCode.USER_BLOCKED);

        //1、删除已有的token
        userTokenMapper.deleteByUserId(user.getId());
        //2、生成新的token 加入数据库
        String token = createToken(user.getUsername());
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUser_id(user.getId());
        userToken.setTime(new Date());
        userTokenMapper.insert(userToken);
        return userToken;
    }

    /**
     * 退出登陆
     *
     * @param token
     * @return
     */
    public void logout(String token) throws ErrorCodeException {
        UserToken userToken = userTokenMapper.selectByToken(token);
        int result = userTokenMapper.deleteByUserId(userToken.getUser_id());
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
    }

    private String createToken(String name) {
        return MD5Util.MD5_16(name + new Date().getTime());
    }
}
