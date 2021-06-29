package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.UmsMenuMapper;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsMenuExample;
import com.macro.mall.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UmsMenuServiceImpl implements UmsMenuService {
    @Autowired
    UmsMenuMapper umsMenuMapper;


    @Override
    public List<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UmsMenuExample umsMenuExample = new UmsMenuExample();
        umsMenuExample.createCriteria().andParentIdEqualTo(parentId);
        return umsMenuMapper.selectByExample(umsMenuExample);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return umsMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        umsMenu.setCreateTime(null);
        return umsMenuMapper.updateByPrimaryKeySelective(umsMenu);
    }

    @Override
    public int delete(Long id) {
        return umsMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        return umsMenuMapper.insert(umsMenu);
    }

}
