package com.macro.mall.demo.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.admin.service.UmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    UmsMenuService umsMenuService;

    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<UmsMenu>> list(
            @PathVariable Long parentId,
            @RequestParam(defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(defaultValue = "5", required = false) Integer pageSize) {
        List<UmsMenu> menuList = umsMenuService.list(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @GetMapping("/{id}")
    public CommonResult<UmsMenu> getItem(@PathVariable Long id) {
        return CommonResult.success(umsMenuService.getItem(id), "查询菜单成功");
    }

    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id, @RequestBody UmsMenu umsMenu) {
        int update = umsMenuService.update(id, umsMenu);
        if (update > 0) {
            return CommonResult.success("更新菜单成功");
        } else {
            return CommonResult.failed("更新菜单失败");
        }
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsMenu umsMenu) {
        int creat = umsMenuService.create(umsMenu);
        if (creat > 0) {
            return CommonResult.success("添加菜单成功");
        } else {
            return CommonResult.failed("添加菜单失败");
        }
    }

    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int delete = umsMenuService.delete(id);
        if (delete > 0) {
            return CommonResult.success("删除菜单成功");
        } else {
            return CommonResult.failed("删除菜单失败");
        }
    }
}
