package com.example.retry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.RetryCacheCapacityExceededException;
import org.springframework.retry.policy.RetryContextCache;

public class RedisRetryContextCache implements RetryContextCache {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public RetryContext get(final Object key) {
        return (RetryContext) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void put(final Object key, final RetryContext context) throws RetryCacheCapacityExceededException {
        redisTemplate.opsForValue().set(key, context);

    }

    @Override
    public void remove(final Object key) {
        redisTemplate.delete(key);

    }

    @Override
    public boolean containsKey(final Object key) {
        return redisTemplate.hasKey(key);
    }
}
