package com.macro.mall.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.UmsResourceMapper;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsResourceExample;
import com.macro.mall.admin.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    UmsResourceMapper umsResourceMapper;


    @Override
    public List<UmsResource> listAll() {
        return umsResourceMapper.selectByExample(new UmsResourceExample());
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setCategoryId(id);
        umsResource.setCreateTime(null);
        return umsResourceMapper.updateByPrimaryKeySelective(umsResource);
    }

    @Override
    public int delete(Long id) {
        return umsResourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(new Date());
        return umsResourceMapper.insert(umsResource);
    }

    @Override
    public UmsResource getItem(Long id) {
        return umsResourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample umsResourceExample = new UmsResourceExample();
        if (!StrUtil.isEmpty(nameKeyword)) {
            umsResourceExample.createCriteria().andUrlLike("%" + nameKeyword + "%");
        }
        if (!StrUtil.isEmpty(urlKeyword)) {
            umsResourceExample.createCriteria().andUrlLike("%" + urlKeyword + "%");
        }
        if (categoryId != null) {
            umsResourceExample.createCriteria().andCategoryIdEqualTo(categoryId);
        }
        return umsResourceMapper.selectByExample(umsResourceExample);
    }
}
