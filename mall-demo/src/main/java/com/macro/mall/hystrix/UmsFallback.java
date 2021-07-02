package com.macro.mall.hystrix;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.service.FeignAdminService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UmsFallback implements FeignAdminService {

    @Override
    public CommonResult<UmsAdmin> getItem(Long id) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setId(-1L);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(0);
        umsAdmin.setUsername("error");
        umsAdmin.setPassword("error");
        return CommonResult.success(umsAdmin,"系统错误 ,请稍后再试");
    }
}
