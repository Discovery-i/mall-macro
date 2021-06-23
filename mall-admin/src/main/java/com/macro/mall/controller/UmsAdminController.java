package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户管理
 */
@RestController
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    UmsAdminService umsAdminService;

    @PostMapping("/register")//注册/添加账号
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        return CommonResult.success(umsAdminService.register(umsAdminParam), "注册成功");
    }

    @GetMapping("/list")//条件分页查询
    public CommonResult<CommonPage<UmsAdmin>> list(
            @RequestParam(defaultValue = "1") Integer pageNum, // 第几页
            @RequestParam(defaultValue = "5") Integer pageSize, // 每页数量
            String keyword // 搜索关键词
    ) {
        List<UmsAdmin> umsAdminList = umsAdminService.list(pageNum, pageSize, keyword);
        return CommonResult.success(CommonPage.restPage(umsAdminList),"查询成功");
    }

    @GetMapping("/{id}")//查找单个item
    public CommonResult<UmsAdmin> getItem(@PathVariable long id) {
        return CommonResult.success(umsAdminService.getItem(id), "查询成功");
    }

    @PostMapping("/update/{id}")//更改单个item
    public CommonResult update(@PathVariable long id, @RequestBody UmsAdmin umsAdmin) {
        return CommonResult.success(umsAdminService.update(id, umsAdmin), "修改成功");
    }

    @PostMapping("/delete/{id}")//删除单个item
    public CommonResult delete(@PathVariable Long id) {
        int delete = umsAdminService.delete(id);
        if (delete > 0) {
            return CommonResult.success(umsAdminService.delete(id), "删除成功");
        } else {
            return CommonResult.failed("删除失败-请检查是否有该用户");
        }
    }

    @GetMapping("/role/{id}")//查找admin的角色
    public CommonResult<List<UmsRole>> getRoleByAdminId(@PathVariable Long id) {
        return CommonResult.success(umsAdminService.getRoleByAdminId(id), "获取用户角色成功");
    }
}
