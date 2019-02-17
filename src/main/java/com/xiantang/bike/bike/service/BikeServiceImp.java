package com.xiantang.bike.bike.service;

import com.xiantang.bike.bike.pojo.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BikeServiceImp implements BikeService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(@RequestBody Bike bike) {
        mongoTemplate.insert(bike);

        // 调用具体业务
    }


    //根据当前经纬度
    @Override
    public List<GeoResult<Bike>> findNear(double longitude, double latitude) {
       // return  mongoTemplate.findAll(Bike.class);
        NearQuery nearQuery = NearQuery.near(longitude, latitude);
        // 查找的范围
        nearQuery.maxDistance(0.5, Metrics.KILOMETERS);

        GeoResults<Bike> bikes = mongoTemplate.geoNear(nearQuery.query(new Query(Criteria.where("status").is(0)).limit(20)), Bike.class);
        return bikes.getContent();
    }
}
