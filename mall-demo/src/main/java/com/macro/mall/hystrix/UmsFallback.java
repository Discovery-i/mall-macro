package com.macro.mall.hystrix;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.feignService.FeignAdminService;
import com.macro.mall.model.UmsAdmin;
import org.springframework.stereotype.Component;

@Component
public class UmsFallback implements FeignAdminService {
    @Override
    public CommonResult<UmsAdmin> getItem(Long id) {
        return CommonResult.failed("服务错误,请稍后再试");
    }
}
