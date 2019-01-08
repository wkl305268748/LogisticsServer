package com.kenny.service.logistics.service.user;


import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.mapper.user.UserInfoMapper;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.mapper.user.UserTokenMapper;
import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.model.po.user.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    /**
     * 校验手机号是否已经注册
     * @param phone
     */
    public void CheckPhone(String phone,String type) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);
        User user = userMapper.selectByPhone(phone,type);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);
    }

    /**
     * 校验账户是否已经注册
     * @param username
     */
    public void CheckUser(String username,String type) throws ErrorCodeException {
        User user = userMapper.selectByUserName(username,type);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);
    }

    /**
     * 用户名密码检验
     *
     * @param username
     * @param password
     * @return
     */
    public User CheckUserPassword(String username, String password,String type) throws ErrorCodeException {
        User user = userMapper.selectByUserName(username,type);

        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);

        //2、校验用户是否封禁
        if (user.getIs_disable())
            throw new ErrorCodeException(UserErrorCode.USER_BLOCKED);

        //3、是否设定密码
        if (user.getPassword() == null)
            throw new ErrorCodeException(UserErrorCode.USER_PASS_NULL);

        //4、校验密码是否正确
        if (!password.equals(user.getPassword()))
            throw new ErrorCodeException(UserErrorCode.PASS_ERROR);

        return user;
    }

    /**
     * 手机号密码检验
     *
     * @param phone
     * @param password
     * @return
     */
    public User CheckPhonePassword(String phone, String password,String type) throws ErrorCodeException {
        User user = userMapper.selectByPhone(phone,type);

        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);

        //2、校验用户是否封禁
        if (user.getIs_disable())
            throw new ErrorCodeException(UserErrorCode.USER_BLOCKED);

        //3、是否设定密码
        if (user.getPassword() == null)
            throw new ErrorCodeException(UserErrorCode.USER_PASS_NULL);

        //4、校验密码是否正确
        if (!password.equals(user.getPassword()))
            throw new ErrorCodeException(UserErrorCode.PASS_ERROR);

        return user;
    }


    /**
     * 根据手机号创建用户
     *
     * @param phone
     * @return
     */
    public User CreateUser(String phone,String password,String type) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);

        User user = userMapper.selectByPhone(phone,type);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);

        user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setIs_disable(false);
        user.setUsername(phone);
        user.setType(type);
        user.setRegtime(new Date());
        int result = userMapper.insert(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    /**
     * 重置密码
     *
     * @param id
     * @param password
     * @return
     */
    public User ResetPassword(int id, String password) throws ErrorCodeException {
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(password);
        int result = userMapper.update(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public User getUser(String token) throws ErrorCodeException {
        //1、参数合法判断
        if (token.length() != 16)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);
        //2、判断token是否存在
        UserToken userToken = userTokenMapper.selectByToken(token);
        if (userToken == null)
            throw new ErrorCodeException(UserErrorCode.TOKEN_ERROR);
        //3、判断token是否超时

        //校验用户
        User user = userMapper.selectByPrimaryKey(userToken.getUser_id());
        return user;
    }

}




