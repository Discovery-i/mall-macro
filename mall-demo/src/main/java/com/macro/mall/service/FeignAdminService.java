package com.macro.mall.service;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsAdmin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient("mall-admin")
@RequestMapping("/admin")
public interface FeignAdminService {
    @GetMapping("/{id}")//查找单个item
    CommonResult<UmsAdmin> getItem(@PathVariable Long id);
}
