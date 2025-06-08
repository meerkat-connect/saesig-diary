package com.saesig.global.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RedisCacheServiceImpl implements CacheService {
    private final StringRedisTemplate redisTemplate;
    private static final Duration TTL = Duration.ofDays(7);

    @Override
    public void saveRefreshToken(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(buildKey(userId), refreshToken, TTL);
    }

    @Override
    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get(buildKey(userId));
    }

    @Override
    public void deleteRefreshToken(String userId) {
        redisTemplate.delete(buildKey(userId));
    }

    private String buildKey(String userId) {
        return "refresh:" + userId;
    }
}
