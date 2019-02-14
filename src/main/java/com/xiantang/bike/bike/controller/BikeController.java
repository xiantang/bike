package com.xiantang.bike.bike.controller;


import com.xiantang.bike.bike.pojo.Bike;
import com.xiantang.bike.bike.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @RequestMapping("/bike/add")
    @ResponseBody
    public  String add(@RequestBody  Bike bike){
//        System.out.println(bike);
        bikeService.save(bike);
        return "success";
    }



}
