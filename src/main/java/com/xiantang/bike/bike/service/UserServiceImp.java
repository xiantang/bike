package com.xiantang.bike.bike.service;

import com.xiantang.bike.bike.pojo.User;
import com.xiantang.bike.bike.until.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public void update(User user) {
        Update update = new Update();
        if (user.getDeposit() != null) {
            update.set("deposit", user.getDeposit());
        }
        if (user.getStatus() != 0) {
            update.set("status", user.getStatus());
        }
        if (user.getName() != null) {
            update.set("name", user.getName());
        }
        if (user.getIdNum() != null) {
            update.set("idNum", user.getIdNum());
        }
        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
                update, User.class);
//        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
//                Update.update("status", user.getStatus()), User.class);
    }

//    @Override
//    public void identify(User user) {
//        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
//                Update.update("status", user.getStatus()), User.class);
//        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
//                Update.update("idNum", user.getIdNum()), User.class);
//        mongoTemplate.updateFirst(new Query(Criteria.where("phoneNum").is(user.getPhoneNum())),
//                Update.update("name", user.getName()), User.class);
//    }

    @Override

    public boolean sendMsg(String phoneNum) {
        String appkey = stringRedisTemplate.opsForValue().get("appkey");
        String tempId = stringRedisTemplate.opsForValue().get("tempId");
        String id = (int) (Math.random() * (9999 - 1000 + 1) + 1000) + "";
        boolean status = SmsUtil.mobileQuery(phoneNum, id, appkey, tempId);
        if (status) {
            stringRedisTemplate.opsForValue().set(phoneNum, id, 300, TimeUnit.SECONDS);
        }
        return status;

    }

    @Override
    public boolean verify(String phoneNum, String verifyCode) {
        String redisVerifyCode = stringRedisTemplate.opsForValue().get(phoneNum);
        if (redisVerifyCode != null && redisVerifyCode.equals(verifyCode)) {
            return true;
        }
        return false;
    }

    @Override
    public void register(User user) {
        mongoTemplate.insert(user);
    }
}
