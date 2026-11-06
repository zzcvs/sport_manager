package com.project.demo.service;

import com.project.demo.entity.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class TokenService {

    @Autowired
    private RedisTemplate redisTemplate;

    public Integer getUserId(String token) {
        if (token == null || token.isEmpty()){
            return null;
        }

        Object value = redisTemplate.opsForValue().get(token);
        if (!(value instanceof AccessToken)){
            return null;
        }
        return ((AccessToken) value).getUser_id();
    }


}
