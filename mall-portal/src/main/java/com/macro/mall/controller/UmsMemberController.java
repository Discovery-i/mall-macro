package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sso")
public class UmsMemberController {

    @ResponseBody
    @RequestMapping("/hello")
    public CommonResult hello(){
        return CommonResult.success(null, "sso-hello");
    }
}
