package com.kenny.service.logistics.service.user;


import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.mapper.user.UserInfoMapper;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.model.po.user.User;
import com.kenny.service.logistics.model.po.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by madengfeng on 2016/11/8.
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserMapper userMapper;

    public UserInfo insertByRegist(int user_id) throws ErrorCodeException {
        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        if (userInfo != null) {
            return userInfo;
        }
        //创建用户信息
        userInfo = new UserInfo();
        userInfo.setNickname("");
        userInfo.setImg("");
        userInfo.setSex("男");
        userInfo.setUser_id(user_id);
        userInfo.setBirthday(new Date());
        userInfo.setCompany("");
        userInfo.setMoney(0f);
        int result = userInfoMapper.insert(userInfo);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);

        return userInfo;
    }

    /**
     * 创建用户信息
     */
    public UserInfo insert(int user_id, String nickname, String sex, String img, Date birthday, String company, Float money) throws ErrorCodeException {

        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        if (userInfo != null) {
            userInfo.setNickname(nickname);
            userInfo.setImg(img);
            userInfo.setSex(sex);
            userInfo.setBirthday(birthday);
            userInfo.setCompany(company);
            userInfo.setMoney(money);
            userInfoMapper.update(userInfo);
        }
        //创建用户信息
        userInfo = new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setImg(img);
        userInfo.setSex(sex);
        userInfo.setUser_id(user_id);
        userInfo.setBirthday(birthday);
        userInfo.setCompany(company);
        userInfo.setMoney(money);
        int result = userInfoMapper.insert(userInfo);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);

        return userInfo;
    }

    /**
     * 获取用户信息
     *
     * @param user_id
     * @return
     */
    public UserInfo GetUserInfo(int user_id) throws ErrorCodeException {
        //通过usertoken查找user_info
        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        //userinfo不存在
        if (userInfo == null)
            throw new ErrorCodeException(UserErrorCode.DATA_NULL);
        return userInfo;
    }

    /**
     * 更新用户信息
     *
     * @param user_id
     * @param nickname
     * @param sex
     * @param img
     * @return
     */
    public UserInfo update(int user_id, String nickname, String sex, String img, Date birthday,String company, Float money) throws ErrorCodeException {
        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        //userinfo不存在
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUser_id(user_id);
        }
        userInfo.setNickname(nickname);
        userInfo.setSex(sex);
        userInfo.setImg(img);
        userInfo.setBirthday(birthday);
        userInfo.setCompany(company);
        userInfo.setMoney(money);
        int result = userInfoMapper.update(userInfo);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return userInfo;
    }

    //充值
    public UserInfo updateAddMoney(int user_id, Float money) throws ErrorCodeException {
        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        if (userInfo == null) {
            throw new ErrorCodeException(UserErrorCode.USER_INOF_NO_EXISTS);
        }
        userInfo.setMoney(userInfo.getMoney() + money);
        int result = userInfoMapper.update(userInfo);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return userInfo;
    }

    //消费
    public UserInfo updateReduceMoney(int user_id, Float money) throws ErrorCodeException {
        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        if (userInfo == null) {
            throw new ErrorCodeException(UserErrorCode.USER_INOF_NO_EXISTS);
        }
        if(userInfo.getMoney() < money)
            throw new ErrorCodeException(UserErrorCode.MONEY_NO_FULL);
        userInfo.setMoney(userInfo.getMoney() - money);
        int result = userInfoMapper.update(userInfo);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return userInfo;
    }

    public UserInfo checkMoney(int user_id, Float money) throws ErrorCodeException {
        UserInfo userInfo = userInfoMapper.selectByUserId(user_id);
        if (userInfo == null) {
            throw new ErrorCodeException(UserErrorCode.USER_INOF_NO_EXISTS);
        }
        if(userInfo.getMoney() < money)
            throw new ErrorCodeException(UserErrorCode.MONEY_NO_FULL);
        return userInfo;
    }

    /**
     * 通过手机号码获取用户信息
     *
     * @param phone
     * @return
     */
    public UserInfo GetUserInfoByPhone(String phone, String type) throws ErrorCodeException {
        //通过phone查找user
        User user = userMapper.selectByPhone(phone, type);
        //user
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        //通过user_id查询user_info
        UserInfo userInfo = userInfoMapper.selectByUserId(user.getId());
        //userinfo不存在
        if (userInfo == null)
            throw new ErrorCodeException(UserErrorCode.DATA_NULL);
        return userInfo;
    }
}

