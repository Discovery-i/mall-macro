package com.macro.mall.service;

import com.macro.mall.common.domain.LoginUserDto;
import com.macro.mall.model.UmsMember;

public interface UmsMemberService {

    int delete(Long id);

    UmsMember getById(Long id);

    int updatePassword(String telephone,String password,String authCode);

    LoginUserDto getLoginUserByUsername(String username);

    String getAuthCode(String telephone);

    UmsMember info();

    int register(String username,
                 String password,
                 String telephone,
                 String authCode);
}
