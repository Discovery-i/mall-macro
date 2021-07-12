package com.macro.mall.admin.service.impl;

import com.macro.mall.mapper.UmsResourceCategoryMapper;
import com.macro.mall.model.UmsResourceCategory;
import com.macro.mall.model.UmsResourceCategoryExample;
import com.macro.mall.admin.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {
    @Autowired
    UmsResourceCategoryMapper umsResourceCategoryMapper;
    @Override
    public List<UmsResourceCategory> listAll() {
        return umsResourceCategoryMapper.selectByExample(new UmsResourceCategoryExample());
    }

    @Override
    public int delete(Long id) {
        return umsResourceCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Long id,UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        umsResourceCategory.setCreateTime(null);
        return umsResourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
    }

    @Override
    public int add(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return umsResourceCategoryMapper.insert(umsResourceCategory);
    }
}
