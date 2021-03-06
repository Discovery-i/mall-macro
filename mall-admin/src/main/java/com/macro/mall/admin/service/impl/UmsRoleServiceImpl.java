package com.macro.mall.admin.service.impl;

import com.macro.mall.mapper.UmsRoleMapper;
import com.macro.mall.model.UmsRole;
import com.macro.mall.model.UmsRoleExample;
import com.macro.mall.admin.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    UmsRoleMapper umsRoleMapper;

    @Override
    public List<UmsRole> list() {
        return umsRoleMapper.selectByExample(new UmsRoleExample());
    }

    @Override
    public int create(UmsRole umsRole) {
        umsRole.setCreateTime(new Date());
        return umsRoleMapper.insert(umsRole);
    }

    @Override
    public int update(Long id, UmsRole umsRole) {
        umsRole.setId(id);
        return umsRoleMapper.updateByPrimaryKeySelective(umsRole);
    }

    @Override
    public int delete(Long id) {
        return umsRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsRole getItem(Long id) {
        return umsRoleMapper.selectByPrimaryKey(id);
    }

}
