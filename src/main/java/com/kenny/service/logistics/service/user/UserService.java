package com.kenny.service.logistics.service.user;


import com.kenny.service.logistics.exception.ErrorCodeException;
import com.kenny.service.logistics.exception.UserErrorCode;
import com.kenny.service.logistics.json.response.PageResponse;
import com.kenny.service.logistics.mapper.user.UserInfoMapper;
import com.kenny.service.logistics.mapper.user.UserMapper;
import com.kenny.service.logistics.mapper.user.UserTokenMapper;
import com.kenny.service.logistics.model.user.User;
import com.kenny.service.logistics.model.user.UserInfo;
import com.kenny.service.logistics.model.user.UserSet;
import com.kenny.service.logistics.model.user.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void CheckPhone(String phone) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);
        User user = userMapper.selectUserByPhone(phone);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);
    }

    /**
     * 校验账户是否已经注册
     * @param username
     */
    public void CheckUser(String username) throws ErrorCodeException {
        User user = userMapper.selectUserByUserName(username);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);
    }

    /**
     * 用户校验
     * 1、校验用户是否存在
     * 2、校验用户是否封禁
     */
    public void CheckUser(User user) throws ErrorCodeException {
        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);

        //2、校验用户是否封禁
        if (user.getIsDisable())
            throw new ErrorCodeException(UserErrorCode.USER_BLOCKED);
    }

    /**
     * 用户名密码检验
     *
     * @param username
     * @param password
     * @return
     */
    public User CheckUserPassword(String username, String password) throws ErrorCodeException {
        User user = userMapper.selectUserByUserName(username);

        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);

        //2、校验用户是否封禁
        if (user.getIsDisable())
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
    public User CheckPhonePassword(String phone, String password) throws ErrorCodeException {
        User user = userMapper.selectUserByPhone(phone);

        //1、校验用户是否存在
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);

        //2、校验用户是否封禁
        if (user.getIsDisable())
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
    public User CreateUser(String phone,String password) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);

        User user = userMapper.selectUserByPhone(phone);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);

        user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setIsDisable(false);
        user.setUsername(phone);
        user.setRegtime(new Date());
        int result = userMapper.insert(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    /**
     * 根据用户名密码创建用户
     *
     * @param username
     * @param password
     * @return
     */
    public User CreateUserByUsername(String username, String password) throws ErrorCodeException {

        User user = new User();
        user.setPhone(null);
        user.setPassword(password);
        user.setIsDisable(false);
        user.setUsername(username);
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
     * 删除用户
     *
     * @param id
     */
    public void DeleteUser(int id) throws ErrorCodeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        int result = userMapper.delete(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
    }

    /**
     * 修改用户
     */
    public User EditUser(int id, String phone, String username) throws ErrorCodeException {
        //修改
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        user.setPhone(phone);
        user.setUsername(username);
        int result = userMapper.update(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);

        return user;
    }

    /**
     * 封禁用户
     */
    public User BlockedUser(int id) throws ErrorCodeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        //设置valid
        user.setIsDisable(true);
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
        User user = userMapper.selectByPrimaryKey(userToken.getUserId());
        CheckUser(user);
        return user;
    }


    public User GetUser(int id) throws ErrorCodeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        return user;
    }

    public PageResponse<User> GetUserList(int offset,int pageSize){
        List<User> users = userMapper.selectPage(offset,pageSize);
        PageResponse<User> pageResponse = new PageResponse<>();
        pageResponse.setItem(users);
        pageResponse.setOffset(offset);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(userMapper.count());
        return pageResponse;
    }

    public PageResponse<UserSet> GetUserSetList(int offset, int pageSize){
        PageResponse<UserSet> pageResponse = new PageResponse<>();
        List<UserSet> userSets = new ArrayList<>();
        for(User user : userMapper.selectPage(offset,pageSize))
        {
            UserInfo userInfo = userInfoMapper.selectByUserId(user.getId());
            UserSet userSet = new UserSet();
            userSet.setUser(user);
            userSet.setUserInfo(userInfo);
            userSets.add(userSet);
        }
        pageResponse.setItem(userSets);
        pageResponse.setOffset(offset);
        pageResponse.setPageSize(pageSize);
        pageResponse.setTotal(userMapper.count());
        return pageResponse;
    }
}




