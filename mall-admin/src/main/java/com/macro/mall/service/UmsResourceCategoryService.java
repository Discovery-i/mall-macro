package com.macro.mall.service;

import com.macro.mall.model.UmsResourceCategory;

import java.util.List;

public interface UmsResourceCategoryService {

    List<UmsResourceCategory> listAll();

    int delete(Long id);

    int update(Long id, UmsResourceCategory umsResourceCategory);

    int add(UmsResourceCategory umsResourceCategory);
}
