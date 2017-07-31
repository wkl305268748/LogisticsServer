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

    /*
    public User selectByPrimaryKey(int user_id){
    }*/

    /**
     * 删除用户
     * @param user_id
     */
    public void deleteByPrimaryKey(int user_id){
        userBaseService.deleteByPrimaryKey(user_id);
    }
}
