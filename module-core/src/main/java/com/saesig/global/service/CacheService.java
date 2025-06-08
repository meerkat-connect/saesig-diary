package com.saesig.global.service;

public interface CacheService {
    void saveRefreshToken(String userId, String refreshToken);
    String getRefreshToken(String userId);
    void deleteRefreshToken(String userId);
}
