package com.macro.mall.demo.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.demo.feignService.FeignAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    FeignAdminService feignAdminService;

    @ResponseBody
    @GetMapping("/getAdminItem/{id}")
    public CommonResult<UmsAdmin> getItem(@PathVariable("id") Long id){
        return feignAdminService.getItem(id);
    }
}
