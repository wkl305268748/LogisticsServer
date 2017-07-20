package com.kenny.service.logistics.service.user;

import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.mapper.user.UserInfoMapper;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.mapper.user.UserTokenMapper;
import com.kenny.service.logistics.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Kenny on 2017/7/20.
 */

@Service
public class UserDriverService {
    @Autowired
    private UserMapper userMapper;
    private final String type = "driver";

    /**
     * 根据手机号创建用户
     *
     * @param phone
     * @return
     */
    public User insert(String phone,String password) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.USER_PHONE_ERROR);

        //用户存在判断
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
        user.setIs_valid(true);
        int result = userMapper.insert(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    public User selectByPrimaryKey(int user_id){
        return userMapper.selectByPrimaryKey(user_id);
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
}
