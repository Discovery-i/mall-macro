package com.macro.mall.service;


import com.macro.mall.model.UmsResource;

import java.util.List;

public interface UmsResourceService {
    List<UmsResource> listAll();

    int update(Long id,UmsResource umsResource);

    int delete(Long id);

    int create(UmsResource umsResource);

    UmsResource getItem(Long id);

    List<UmsResource> list(Long categoryId,String nameKeyword,String urlKeyword,Integer pageNum,Integer pageSize);
}
