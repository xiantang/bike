package com.xiantang.bike.bike.service;

import com.xiantang.bike.bike.pojo.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BikeServiceImp implements BikeService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(@RequestBody Bike bike) {
        mongoTemplate.insert(bike);

        // 调用具体业务
    }
}
