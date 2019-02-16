package com.xiantang.bike.bike.controller;

import com.xiantang.bike.bike.pojo.User;
import com.xiantang.bike.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user/genCode")
    @ResponseBody
    public boolean genVerfyCode(String countryCode, String phoneNum) {
        boolean status = userService.sendMsg(phoneNum);
        return status;
    }

    @RequestMapping("/user/verify")
    @ResponseBody
    public boolean verify(String phoneNum, String verifyCode) {
//        System.out.println(1);
        boolean flag = userService.verify(phoneNum, verifyCode);
        return flag;
    }

    @RequestMapping("/user/register")
    @ResponseBody
    // 接受RequestsBody
    public boolean register(@RequestBody User user){
//        System.out.println(1);
        try {
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @RequestMapping("/user/deposite")
    @ResponseBody
    public boolean deposite(@RequestBody User user) {
        try {
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @RequestMapping("/user/identify")
    @ResponseBody
    public boolean identify(@RequestBody User user) {
        try {
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


}
