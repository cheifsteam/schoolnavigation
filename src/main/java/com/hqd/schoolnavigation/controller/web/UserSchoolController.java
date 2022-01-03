package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.SchoolCategoryService;
import com.hqd.schoolnavigation.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserSchoolController {
    @Resource
    private SchoolService schoolService;
@Resource
private SchoolCategoryService schoolCategoryService;
    /**
     * 首页获取学校
     * @return
     */
    @PostMapping("/web/school/getAll")
    public AjaxResult getAlSchool()
    {
        PageDto pageDto=new PageDto();
        pageDto.setPage(1);
        pageDto.setPageSize(10);
        schoolService.getAllSchool(pageDto);
        return AjaxResult.success(pageDto);
    }

    /**
     * 模糊查询学校
     * @param filterName
     * @param pageDto
     * @return
     */
    @PostMapping("/web/school/getLike/{filterName}")
    public AjaxResult getLike(@PathVariable(required = false) String filterName,@RequestBody PageDto pageDto)
    {
        schoolService.getLikeSchool(filterName,pageDto);
        return AjaxResult.success(pageDto);
    }

    /**
     * 用户获取所有学校
     * @param pageDto
     * @return
     */
    @PostMapping(value = {"/web/school/getAllSchool/{categoryId}","/web/school/getAllSchool"})
    public AjaxResult getAllSchool(@PathVariable(required = false) String categoryId,  @RequestBody PageDto pageDto)
    {
        if (categoryId!=null){
            final List<Integer> Id = schoolCategoryService.getSchoolIdByCategoryId(categoryId);

            schoolService.getAllSchoolBySchoolId(Id,pageDto);

            return AjaxResult.success(pageDto);
        }
        else {
            schoolService.getAllSchool(pageDto);

        }
        return AjaxResult.success(pageDto);
    }

}
