package com.macro.mall.demo.feignService;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.demo.hystrix.UmsFallback;
import com.macro.mall.model.UmsAdmin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@FeignClient(value = "mall-admin",fallback = UmsFallback.class)
public interface FeignAdminService {

    @ResponseBody
    @GetMapping("/admin/{id}")
//查找单个item
    CommonResult<UmsAdmin> getItem(@PathVariable Long id);
}
