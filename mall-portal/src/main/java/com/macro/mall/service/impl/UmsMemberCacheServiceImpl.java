package com.macro.mall.service.impl;

import com.macro.mall.common.annotation.CacheException;
import com.macro.mall.common.service.RedisService;
import com.macro.mall.model.UmsMember;
import com.macro.mall.service.UmsMemberCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UmsMemberCacheServiceImpl implements UmsMemberCacheService {
    @Autowired
    RedisService redisService;

    @Value("${redis.database}")
    String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    Long REDIS_EXPIRE;
    @Value("${redis.expire.authCode}")
    Long REDIS_EXPIRE_AUTH_CODE;
    @Value("${redis.key.member}")
    String REDIS_KEY_MEMBER;
    @Value("${redis.key.authCode}")
    String REDIS_KEY_AUTH_CODE;

    @Override
    public void delMember(Long memberId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + memberId;
        redisService.del(key);
    }

    @Override
    public UmsMember getMember(Long memberId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + memberId;
        return (UmsMember) redisService.get(key);
    }

    @Override
    public void setMember(UmsMember member) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_MEMBER + ":" + member.getId();
        redisService.set(key, member, REDIS_EXPIRE);
    }

    @CacheException
    @Override
    public void setAuthCode(String telephone, String authCode) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        redisService.set(key, authCode, REDIS_EXPIRE_AUTH_CODE);
    }

    @CacheException
    @Override
    public String getAuthCode(String telephone) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_AUTH_CODE + ":" + telephone;
        return (String) redisService.get(key);
    }
}
