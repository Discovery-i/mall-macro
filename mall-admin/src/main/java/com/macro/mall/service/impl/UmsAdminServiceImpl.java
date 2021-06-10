package com.macro.mall.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import com.macro.mall.service.UmsAdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class   UmsAdminServiceImpl implements UmsAdminService {
    @Autowired
    UmsAdminMapper umsAdminMapper;

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        //条件查询内添加用户输入的名字
        umsAdminExample.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        //查表获取用户
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        //有用户
        if (umsAdmins.size() > 0) {
            return null;
        }
        //  ↓  无用户则可注册
        //完善用户信息
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //明文 -> 密文 / 不加盐值
        String encodePassword = BCrypt.hashpw(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);

        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }
}
