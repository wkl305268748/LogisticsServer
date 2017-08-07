package com.kenny.service.logistics.service.user;


import com.kenny.service.logistics.exception.ErrorCode;
import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.mapper.user.UserInfoMapper;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.mapper.user.UserTokenMapper;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.model.user.UserToken;
import com.kenny.service.logistics.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserBaseService {
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
    public void checkPhoneHas(String phone,String type) throws ErrorCodeException {
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
    public void checkUserHas(String username,String type) throws ErrorCodeException {
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
    public User checkUserPassword(String username, String password,String type) throws ErrorCodeException {
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
    public User checkPhonePassword(String phone, String password,String type) throws ErrorCodeException {
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
    public User insertByPhone(String phone,String password,String type) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);

        checkPhoneHas(phone,type);

        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setIs_disable(false);
        user.setUsername(phone);
        user.setType(type);
        user.setRegtime(new Date());
        user.setIs_valid(true);
        int result = userMapper.insert(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    /**
     * 根据用户名创建用户
     *
     * @param username
     * @return
     */
    public User insertByUserName(String username,String password,String type) throws ErrorCodeException {
        checkUserHas(username,type);

        User user = new User();
        user.setPhone("");
        user.setPassword(password);
        user.setIs_disable(false);
        user.setUsername(username);
        user.setType(type);
        user.setRegtime(new Date());
        user.setIs_valid(true);
        int result = userMapper.insert(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }


    //重置密码
    public User updatePassword(String token, String old_password, String new_password) throws ErrorCodeException {
        User user = getUserByToken(token);
        if(!user.getPassword().equals(old_password))
            throw new ErrorCodeException(UserErrorCode.PASS_ERROR);
        user.setPassword(new_password);
        int result = userMapper.update(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    /**
     * 通过token获取用户信息
     *
     * @param token
     * @return
     */
    public User getUserByToken(String token) throws ErrorCodeException {
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
        if(user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        return user;
    }

    /**
     * 通过token获取用户所有信息
     *
     * @param token
     * @return
     */
    public UserSet getUserByTokenEx(String token) throws ErrorCodeException {
        //1、参数合法判断
        if (token.length() != 16)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);
        //2、判断token是否存在
        UserToken userToken = userTokenMapper.selectByToken(token);
        if (userToken == null)
            throw new ErrorCodeException(UserErrorCode.TOKEN_ERROR);
        //3、判断token是否超时

        UserSet userSet = new UserSet();
        //校验用户
        User user = userMapper.selectByPrimaryKey(userToken.getUser_id());
        UserInfo userInfo = userInfoMapper.selectByUserId(user.getId());

        userSet.setUser(user);
        userSet.setUserInfo(userInfo);
        return userSet;
    }

    /**
     * 用户名密码登录
     * @param username
     * @param password
     * @param type
     * @return
     * @throws ErrorCodeException
     */
    public UserToken loginUserName(String username,String password,String type) throws ErrorCodeException {
        User user = checkUserPassword(username,password,type);

        //1、删除已有的token
        userTokenMapper.deleteByUserId(user.getId());
        //2、生成新的token 加入数据库
        String token = createToken(username);
        UserToken userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUser_id(user.getId());
        userToken.setTime(new Date());
        userTokenMapper.insert(userToken);
        return userToken;
    }

    /**
     * 手机号密码登录
     * @param phone
     * @param password
     * @param type
     * @return
     * @throws ErrorCodeException
     */
    public UserToken loginPhone(String phone,String password,String type) throws ErrorCodeException {
        User user = checkPhonePassword(phone,password,type);

        //1、删除已有的token
        userTokenMapper.deleteByUserId(user.getId());
        //2、生成新的token 加入数据库
        String token = createToken(phone);
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

    /**
     * 删除用户
     * @param user_id
     */
    public void deleteByPrimaryKey(int user_id){
        User user = userMapper.selectByPrimaryKey(user_id);
        if (user == null)
            return;
        user.setIs_valid(false);
        userMapper.update(user);
    }

    public int getCount(String type){
        return userMapper.countByType(type);
    }

    private String createToken(String name) {
        return MD5Util.MD5_16(name + new Date().getTime());
    }

}




