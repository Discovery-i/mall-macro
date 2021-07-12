package com.macro.mall.admin.dao;

import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsAdminRoleRelationDao {
   List<UmsRole> getRoleByAdminId(Long id);
}
