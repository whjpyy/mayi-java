package com.mayi.jiagousi.ch20_redis;

import redis.clients.jedis.Jedis;
import sun.security.krb5.internal.crypto.HmacSha1Aes128CksumType;

import java.util.HashMap;
import java.util.Map;

public class RedisDemo {
    private static Jedis jedis = new Jedis("192.168.10.201", 6379);

    public static void main(String[] args) {
        jedis.auth("123456");
        setString();
        setMap();
        zset();
    }

    public static void setString(){
        jedis.set("name", "chen");
        System.out.println("获取：" + jedis.get("name"));
    }

    public static void setMap(){
        Map<String, String> map = new HashMap<>();
        map.put("age", "11");
        map.put("name", "chen");
        jedis.hmset("hmap", map);
    }

    public static void zset(){
        jedis.zadd("aa", 1, "aa");
    }
}
