package com.xiantang.bike.bike.service;

public interface UserService {
    boolean sendMsg(String phoneNum);

    boolean verify(String phoneNum, String verifyCode);
}
