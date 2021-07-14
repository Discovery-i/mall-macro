package com.macro.mall.portal.service.impl;

import com.macro.mall.portal.service.UmsMemberCacheService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    UmsMemberCacheService umsMemberCacheService;

    @Override
    public String getAuthCode(String telephone) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        String authCode = stringBuilder.toString();
        umsMemberCacheService.setAuthCode(telephone,authCode);
        return authCode;
    }
}
