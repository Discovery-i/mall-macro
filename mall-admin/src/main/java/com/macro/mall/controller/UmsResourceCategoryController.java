package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsResourceCategory;
import com.macro.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    UmsResourceCategoryService umsResourceCategoryService;

    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll() {
        return CommonResult.success(umsResourceCategoryService.listAll());
    }

    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory) {
        int add = umsResourceCategoryService.add(umsResourceCategory);
        if (add > 0) {
            return CommonResult.success(add, "添加后台资源分类成功");
        }
        return CommonResult.failed("添加后台资源分类失败");
    }

    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id){
        int delete = umsResourceCategoryService.delete(id);
        if (delete > 0) {
            return CommonResult.success("删除后台资源分类成功");
        } else {
            return CommonResult.failed("删除后台资源分类失败");
        }
    }

    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,@RequestBody UmsResourceCategory umsResourceCategory){
        int update = umsResourceCategoryService.update(id, umsResourceCategory);
        if (update > 0) {
            return CommonResult.success("更新后台资源分类成功");
        } else {
            return CommonResult.failed("更新后台资源分类失败");
        }
    }
}
