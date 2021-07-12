package com.macro.mall.demo.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsResource;
import com.macro.mall.admin.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class UmsResourceController {
    @Autowired
    UmsResourceService umsResourceService;

    @GetMapping("/listAll")
    public CommonResult<List<UmsResource>> listAll() {
        return CommonResult.success(umsResourceService.listAll());
    }

    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        int delete = umsResourceService.delete(id);
        if (delete > 0) {
            return CommonResult.success("删除后台资源成功");
        } else {
            return CommonResult.failed("删除后台资源失败");
        }
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResource umsResource) {
        int create = umsResourceService.create(umsResource);
        if (create > 0) {
            return CommonResult.success("创建后台资源成功");
        } else {
            return CommonResult.failed("创建后台资源成功");
        }
    }

    @PostMapping("/update/{id}")
    public CommonResult update(
            @PathVariable("id") Long id,
            @RequestBody UmsResource umsResource
    ) {
        int update = umsResourceService.update(id, umsResource);
        if (update > 0) {
            return CommonResult.success("修改后台资源成功");
        } else {
            return CommonResult.failed("修改后台资源失败");
        }
    }

    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable("id") Long id) {
        UmsResource item = umsResourceService.getItem(id);
        if (item != null) {
            return CommonResult.success(item, "查询后台资源成功");
        } else {
            return CommonResult.failed("查询后台资源失败");
        }
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String nameKeyword,
            @RequestParam(required = false) String urlKeyword,
            @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
            @RequestParam(defaultValue = "5",value = "pageSize") Integer pageSize) {
        return CommonResult.success(CommonPage.restPage(
                umsResourceService.list(categoryId, nameKeyword, urlKeyword, pageNum, pageSize)),
                "查询成功");
    }
}
