package com.macro.mall.admin.service;

import com.macro.mall.model.UmsMenu;

import java.util.List;

public interface UmsMenuService {
    List<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize);

    UmsMenu getItem(Long id);

    int update(Long id, UmsMenu umsMenu);

    int delete(Long id);

    int create(UmsMenu umsMenu);
}
