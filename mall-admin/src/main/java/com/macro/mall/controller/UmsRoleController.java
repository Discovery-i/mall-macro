package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class UmsRoleController {
    @Autowired
    UmsRoleService umsRoleService;

    @GetMapping("/listAll")
    public CommonResult<List<UmsRole>> listAll() {
        return CommonResult.success(umsRoleService.list(), "查询所有角色成功");
    }

    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable("id") Long id) {
        return CommonResult.success(umsRoleService.getItem(id));
    }

    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int delete = umsRoleService.delete(id);
        if (delete > 0) {
            return CommonResult.success("删除角色成功");
        } else {
            return CommonResult.failed("删除角色失败");
        }
    }

    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody UmsRole umsRole) {
        int update = umsRoleService.update(id, umsRole);
        if (update > 0) {
            return CommonResult.success("更新角色成功");
        } else {
            return CommonResult.failed("更新角色失败");
        }
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsRole umsRole) {
        int create = umsRoleService.create(umsRole);
        if (create > 0) {
            return CommonResult.success("添加角色成功");
        } else {
            return CommonResult.failed("添加角色失败");
        }
    }

    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable("id") Long id, Integer status) {
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int update = umsRoleService.update(id, umsRole);
        if (update > 0) {
            return CommonResult.success("更新状态成功");
        } else {
            return CommonResult.failed("更新状态失败");
        }
    }
}
