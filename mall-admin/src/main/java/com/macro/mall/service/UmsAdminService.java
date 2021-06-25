package com.macro.mall.service;

import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;

import java.util.List;

public interface UmsAdminService {
    UmsAdmin register(UmsAdminParam umsAdminParam);

    List<UmsAdmin> list(Integer pageNum,// 页数
                        Integer pageSize,//每页数量
                        String keyword //搜索词);
    );

    UmsAdmin getItem(Long id);

    int update(Long id, UmsAdmin umsAdmin);

    int delete(Long id);

    List<UmsRole> getRoleByAdminId(Long id);
}
