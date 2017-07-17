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

@Service
public class UserLoginService {
    @Autowired
    UserTokenMapper userTokenMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    /**
     * 短信登陆流程
     * 1、删除已有的token
     * 2、生成新的token 加入数据库
     * 3、生成userOnline加入数据库
     *
     * @param phone
     * @return
     */
    public UserToken LoginSms(String phone,String type) throws ErrorCodeException {
        User user = userMapper.selectByPhone(phone,type);
        UserToken token = Login(user);
        return token;
    }

    /**
     * 用户名密码登陆流程
     * 1、删除已有的token
     * 2、生成新的token 加入数据库
     * 3、生成userOnline加入数据库
     *
     * @param username
     * @return
     */
    public UserToken LoginUserPass(String username,String type) throws ErrorCodeException {
        User user = userMapper.selectByPhone(username,type);
        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        //2、校验用户是否封禁
        if (user.getIs_disable())
            throw new ErrorCodeException(UserErrorCode.USER_BLOCKED);
        UserToken token = Login(user);
        return token;
    }

    /**
     * 用户名密码登陆流程
     * 1、删除已有的token
     * 2、生成新的token 加入数据库
     * 3、生成userOnline加入数据库
     *
     * @param phone
     * @return
     */
    public UserToken LoginPhonePass(String phone,String type) throws ErrorCodeException {
        User user = userMapper.selectByPhone(phone,type);
        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        //2、校验用户是否封禁
        if (user.getIs_disable())
            throw new ErrorCodeException(UserErrorCode.USER_BLOCKED);

        UserToken token = Login(user);
        return token;
    }

    public UserToken Login(User user) throws ErrorCodeException {
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
    public void Logout(String token) throws ErrorCodeException {
        UserToken userToken = userTokenMapper.selectByToken(token);
        int result = userTokenMapper.deleteByUserId(userToken.getUser_id());
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
    }
    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public User CheckToken(String token) throws ErrorCodeException {
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

    /**
     * 随机生成Token
     * 用户名+时间戳 -> MD5
     *
     * @param name
     * @return
     */
    private String createToken(String name) {
        return MD5Util.MD5_16(name + new Date().getTime());
    }
}
