package com.macro.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.json.JSONUtil;
import com.macro.mall.common.api.ResultCode;
import com.macro.mall.common.constant.AuthConstant;
import com.macro.mall.common.domain.LoginUserDto;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.UmsMemberLevelMapper;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberExample;
import com.macro.mall.model.UmsMemberLevel;
import com.macro.mall.model.UmsMemberLevelExample;

import com.macro.mall.service.UmsMemberCacheService;
import com.macro.mall.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    UmsMemberMapper umsMemberMapper;
    @Autowired
    UmsMemberLevelMapper umsMemberLevelMapper;
    @Autowired
    UmsMemberCacheService umsMemberCacheService;
    @Autowired
    private HttpServletRequest request;


    @Override
    public int delete(Long id) {
        return umsMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsMember getById(Long id) {
        return umsMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePassword(String telephone, String password, String authCode) {
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }

        UmsMemberExample umsMemberExample = new UmsMemberExample();
        umsMemberExample.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
        if (umsMembers.isEmpty()) {
            Asserts.fail("用户不存在");
        }
//        不验证密码前后是否一致
        UmsMember umsMember = umsMembers.get(0);
        umsMember.setPassword(BCrypt.hashpw(password));
        return umsMemberMapper.updateByPrimaryKeySelective(umsMember);
    }

    @Override
    public LoginUserDto getLoginUserByUsername(String username) {
        UmsMember umsMember = getUmsMemberByUsername(username);
        if (umsMember != null) {
            LoginUserDto loginUserDto = new LoginUserDto();
            BeanUtils.copyProperties(umsMember, loginUserDto);
            loginUserDto.setRoles(CollUtil.toList("前台会员"));
            return loginUserDto;
        }
        return null;
    }

    public UmsMember getUmsMemberByUsername(String username) {
        UmsMemberExample umsMemberExample = new UmsMemberExample();
        umsMemberExample.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> umsMembers = umsMemberMapper.selectByExample(umsMemberExample);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            return umsMembers.get(0);
        }
        return null;
    }

    @Override
    public UmsMember info() {
        String userStr = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        if (StrUtil.isEmpty(userStr)) {
            Asserts.fail(ResultCode.UNAUTHORIZED);
        }
        LoginUserDto loginUserDto = JSONUtil.toBean(userStr, LoginUserDto.class);

        return null;
    }

    @Override
    public int register(String username, String password, String telephone, String authCode) {
//        查看验证码是否正确
        if (!verifyAuthCode(authCode, telephone)) {
            Asserts.fail("验证码错误");
        }

//        判断手机号是否注册过
        UmsMemberExample telephoneMemberExample = new UmsMemberExample();
        telephoneMemberExample.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> telephoneUmsMembers = umsMemberMapper.selectByExample(telephoneMemberExample);
        if (telephoneUmsMembers.size() > 0) {
            Asserts.fail("该手机号已注册过");
        }

//            判断用户名是否存在
        UmsMemberExample usernameMemberExample = new UmsMemberExample();
        usernameMemberExample.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> usernameUmsMember = umsMemberMapper.selectByExample(usernameMemberExample);
        if (usernameUmsMember.size() > 0) {
            Asserts.fail("该用户名已被使用");
//                返回-2表示: 手机未注册但用户名已注册过
        }

        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPassword(BCrypt.hashpw(password));
        umsMember.setPhone(telephone);
        umsMember.setStatus(1);
        umsMember.setCreateTime(new Date());

//        找到默认会员等级并设置
        UmsMemberLevelExample umsMemberLevelExample = new UmsMemberLevelExample();
        umsMemberLevelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> umsMemberLevels = umsMemberLevelMapper.selectByExample(umsMemberLevelExample);
        if (!umsMemberLevels.isEmpty()) {
            umsMember.setMemberLevelId(umsMemberLevels.get(0).getId());
        }
        return umsMemberMapper.insert(umsMember);
    }

    @Override
    public String getAuthCode(String telephone) {
        String authCode = generateAuthCode();
        umsMemberCacheService.setAuthCode(telephone, generateAuthCode());
        return authCode;
    }

    //    认证验证码
    public Boolean verifyAuthCode(String authCode, String telephone) {
        if (StrUtil.isEmpty(authCode)) {
            Asserts.fail("未输入验证码");
        }
        String realAuthCode = umsMemberCacheService.getAuthCode(telephone);
        return realAuthCode.equals(authCode);
    }

    //    创建验证码
    public String generateAuthCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
