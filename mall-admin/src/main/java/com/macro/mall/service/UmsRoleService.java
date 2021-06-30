package com.macro.mall.service;

import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsRoleService {
    List<UmsRole> list();

    int create(UmsRole umsRole);

    int update(Long id, UmsRole umsRole);

    int delete(Long id);

    UmsRole getItem(Long id);

}
