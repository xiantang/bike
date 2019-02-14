package com.xiantang.bike.bike.controller;


import com.xiantang.bike.bike.pojo.Bike;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BikeController {

    @RequestMapping("/addBike")
    @ResponseBody
    public  String add(@RequestBody  Bike bike){
        System.out.println(bike);
        return "hello";
    }



}
