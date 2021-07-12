package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "UmsMemberController", description = "会员管理")
@RestController
@RequestMapping("sso")
public class UmsMemberController {

    @ApiOperation("注册")
    @PostMapping("/register")
    public CommonResult register() {
        return CommonResult.success("注册成功");
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode() {
        return null;
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassword")
    public CommonResult updatePassword() {
        return null;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public CommonResult login() {
        return null;
    }

    @ApiOperation("根据用户名获取通用用户信息")
    @GetMapping("/loadByUsername")
    public CommonResult loadByUsername() {
        return null;
    }

    @ApiOperation("获取会员信息")
    @GetMapping("/info")
    public CommonResult info() {
        return null;
    }
}
