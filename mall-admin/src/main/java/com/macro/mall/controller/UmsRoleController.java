package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    UmsRoleService umsRoleService;

    @GetMapping("/listAll")
    public CommonResult<List<UmsRole>> listAll(){
        return CommonResult.success(umsRoleService.list(), "查询所有角色成功");
    }
}
