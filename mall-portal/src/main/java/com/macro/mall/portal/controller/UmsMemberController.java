package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "UmsMemberController", description = "会员管理")
@RestController
@RequestMapping("sso")
public class UmsMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public CommonResult register() {
        return null;
    }

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return CommonResult.success(umsMemberService.getAuthCode(telephone),"获取验证码成功");
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
