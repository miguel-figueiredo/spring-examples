package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public UUID put(String value) {
        final UUID uuid = UUID.randomUUID();
        redisTemplate.opsForValue().set(uuid.toString(), value);
        return uuid;
    }

    public String get(final UUID uuid) {
        return redisTemplate.opsForValue().get(uuid.toString()).toString();
    }
}
