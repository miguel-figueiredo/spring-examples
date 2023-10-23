package com.example.retry;

import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.RetryCacheCapacityExceededException;
import org.springframework.retry.policy.RetryContextCache;

public class RedisRetryContextCache implements RetryContextCache {
    @Override
    public RetryContext get(final Object key) {
        return null;
    }

    @Override
    public void put(final Object key, final RetryContext context) throws RetryCacheCapacityExceededException {

    }

    @Override
    public void remove(final Object key) {

    }

    @Override
    public boolean containsKey(final Object key) {
        return false;
    }
}
