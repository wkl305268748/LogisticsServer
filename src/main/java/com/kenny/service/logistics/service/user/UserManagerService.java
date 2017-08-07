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
public class UserManagerService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 创建用户
     *
     * @param phone
     * @return
     */
    public User insert(String username,
                       String phone,
                       String password,
                       String type) throws ErrorCodeException {
        //参数判断
        if (phone.length() != 11)
            throw new ErrorCodeException(UserErrorCode.PARAM_ERROR);

        User user = userMapper.selectByPhone(phone,type);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);

        user = userMapper.selectByUserName(username,type);
        if (user != null)
            throw new ErrorCodeException(UserErrorCode.USER_EXISTS);

        user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setUsername(username);
        user.setType(type);
        user.setIs_disable(false);
        user.setUsername(phone);
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
    public User resetPassword(int id, String password) throws ErrorCodeException {
        User user = userMapper.selectByPrimaryKey(id);
        user.setPassword(password);
        int result = userMapper.update(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);
        return user;
    }

    public User selectByPrimaryKey(Integer id) throws ErrorCodeException{
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null){
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        return user;
    }

    public UserSet selectByPrimaryKeyEx(Integer id) throws ErrorCodeException{
        User user = userMapper.selectByPrimaryKey(id);
        if(user == null){
            throw new ErrorCodeException(ErrorCodeException.DATA_NO_ERROR);
        }
        UserSet userSet = new UserSet();
        userSet.setUser(user);
        userSet.setUserInfo(userInfoMapper.selectByUserId(user.getId()));
        return userSet;
    }

    public PageResponse<User> selectPage(Integer offset,Integer pageSize){
        PageResponse<User> response = new PageResponse();
        response.setItem(userMapper.selectPage(offset,pageSize));
        response.setTotal(userMapper.count());
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public PageResponse<User> selectPageByType(Integer offset,Integer pageSize,String type){
        PageResponse<User> response = new PageResponse();
        response.setItem(userMapper.selectPageByType(offset,pageSize,type));
        response.setTotal(userMapper.countByType(type));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public PageResponse<UserSet> selectPageByTypeEx(Integer offset,Integer pageSize,String type){
        PageResponse<UserSet> response = new PageResponse();

        List<UserSet> userSets = new ArrayList<>();
        for(User user: userMapper.selectPageByType(offset,pageSize,type)){
            UserSet userSet = new UserSet();
            userSet.setUser(user);
            userSet.setUserInfo(userInfoMapper.selectByUserId(user.getId()));
            userSets.add(userSet);
        }
        response.setItem(userSets);
        response.setTotal(userMapper.countByType(type));
        response.setOffset(offset);
        response.setPageSize(pageSize);
        return response;
    }

    public int deleteByPrimaryKey(Integer id){
        User user = userMapper.selectByPrimaryKey(id);
        user.setIs_valid(false);
        return userMapper.update(user);
    }

    /**
     * 封禁用户
     */
    public User disable(int id) throws ErrorCodeException {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null)
            throw new ErrorCodeException(UserErrorCode.USER_NO_EXISTS);
        //设置valid
        user.setIs_disable(true);
        int result = userMapper.update(user);
        if (result <= 0)
            throw new ErrorCodeException(UserErrorCode.DB_ERROR);

        return user;
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

    public List<String> selectNameByType(Integer offset,Integer pageSize,String type){
        List<UserSet> users = selectPageByTypeEx(offset,pageSize,type).getItem();
        List<String> names = new ArrayList<>();
        for(UserSet user : users){
            names.add(user.getUserInfo().getCompany());
        }
        return names;
    }
}




