package com.kenny.service.logistics.model.user;

import java.util.List;

public class UserSet {
    User user;
    UserInfo userInfo;
    List<UserMoney> userMonies;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<UserMoney> getUserMonies() {
        return userMonies;
    }

    public void setUserMonies(List<UserMoney> userMonies) {
        this.userMonies = userMonies;
    }
}