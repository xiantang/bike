package com.xiantang.bike.bike.controller;

import com.xiantang.bike.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user/genCode")
    @ResponseBody
    public  boolean genVerfyCode(String countryCode,String phoneNum){


        boolean status = userService.sendMsg(phoneNum);

        return status;
    }
}
