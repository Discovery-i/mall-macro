package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "UmsMemberController", description = "前台会员/用户管理")
@Controller
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @ResponseBody
    @GetMapping("/getAuthCode")
    public CommonResult getAuthCode(String telephone) {
        return CommonResult.success(umsMemberService.getAuthCode(telephone), "获取验证码成功");
    }

    @ApiOperation("注册")
    @ResponseBody
    @PostMapping("/register")
    public CommonResult register(
            @RequestParam String username, @RequestParam String password,
            @RequestParam String telephone, @RequestParam String authCode) {
        int register = umsMemberService.register(username, password, telephone, authCode);
        if (register > 0) {
            return CommonResult.success("成功注册");
        } else {
            return CommonResult.failed("注册失败,请稍后再试");
        }
    }

    @ApiOperation("修改密码")
    @ResponseBody
    @PostMapping("/updatePassword")
    public CommonResult updatePassword(
            @RequestParam String telephone,
            @RequestParam String authCode,
            @RequestParam String password
    ) {
        int updatePassword = umsMemberService.updatePassword(telephone, password, authCode);
        if (updatePassword > 0) {
            return CommonResult.success("修改密码成功");
        }
        return CommonResult.success("修改密码失败");
    }


}
