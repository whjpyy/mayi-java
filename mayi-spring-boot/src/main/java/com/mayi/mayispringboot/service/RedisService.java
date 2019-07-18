package com.mayi.mayispringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, String value, Long time){
        setObject(key, value, time);
    }
    public void setList(String key, List<String> list, Long time){
        setObject(key, list, time);
    }

    public void setObject(String key, Object value, Long time){
        // redis类型：string, list, set, zset, hashset
        if(StringUtils.isEmpty(key) || value == null){
            return;
        }
        time = time == null ? 0 : time;
        if(value instanceof String){
            stringRedisTemplate.opsForValue().set(key, (String)value, time, TimeUnit.SECONDS);
        }else if(value instanceof List){
            List<String> list = (List<String>) value;
            list.forEach(item -> stringRedisTemplate.opsForList().leftPush(key, item));
        }
    }

    public String getKey(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
