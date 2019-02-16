package com.xiantang.bike.bike.service;

import com.xiantang.bike.bike.pojo.User;

public interface UserService {
    boolean sendMsg(String phoneNum);

    boolean verify(String phoneNum, String verifyCode);

    void register(User user);

    void update(User user);
}
