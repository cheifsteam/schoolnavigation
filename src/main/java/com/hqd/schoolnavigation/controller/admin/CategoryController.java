package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.CategoryDto;
import com.hqd.schoolnavigation.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @PostMapping("/admin/category/addTop")
    public AjaxResult addTopCategory(@RequestBody CategoryDto categoryDto)
    {
        categoryService.addTopLevelCategory(categoryDto);
        return AjaxResult.success("添加成功");
    }
    @PostMapping("/admin/category/addSecondary")
    public  AjaxResult addSecondaryLevelCategory(@RequestBody CategoryDto categoryDto)
    {
        categoryService.addSubLevelCategory(categoryDto);
        return AjaxResult.success("添加成功");
    }
    @DeleteMapping("/admin/category/delete/{id}")
    public AjaxResult deleteLevelCategory(@PathVariable String id)
    {
        categoryService.deleteCategory(id);
        return AjaxResult.success("删除成功");
    }
    @PostMapping("/admin/category/getTop")
    public AjaxResult getTopLevelCategory()
    {
        final List topLevelCategory = categoryService.getTopLevelCategory();
        return AjaxResult.success("获取成功",topLevelCategory);

    }
    @PostMapping("/admin/category/getSub/{parent}")
    public AjaxResult getSubLevelCategory(@PathVariable String parent)
    {
        final List subLevelCategory = categoryService.getSubLevelCategory(parent);
        return AjaxResult.success("获取成功",subLevelCategory);
    }

}
