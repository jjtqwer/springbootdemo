package com.example.springbootdemo;

import com.example.springbootdemo.entity.T;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

public class RedisTest {
    private static final String REDIS_KEY="user";

    public static void main(String[] args) {
//        Jedis jedis=new Jedis("localhost",6379);
//        Gson gson=new Gson();
//        T user=new T(18,"张三");
//
//        jedis.set(REDIS_KEY,gson.toJson(user));
//        T getUserInfoFromRedis =gson.fromJson(jedis.get(REDIS_KEY),T.class);
//        System.out.println("get:"+getUserInfoFromRedis);
//        System.out.println("exists:"+jedis.exists(REDIS_KEY));
//        System.out.println("del:"+jedis.del(REDIS_KEY));
//        System.out.println("get:"+jedis.get(REDIS_KEY));

    }
}
