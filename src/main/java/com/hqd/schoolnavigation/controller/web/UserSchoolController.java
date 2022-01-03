package com.hqd.schoolnavigation.controller.web;

import com.github.pagehelper.Page;
import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.SchoolCategoryService;
import com.hqd.schoolnavigation.service.SchoolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @PostMapping("/web/school/getLike")
    public AjaxResult getLike(@RequestParam(required = false) String filterName,@RequestParam PageDto pageDto)
    {
        schoolService.getLikeSchool(filterName,pageDto);
        return AjaxResult.success(pageDto);
    }

    /**
     * 用户获取所有学校
     * @param pageDto
     * @return
     */
    @PostMapping("/web/school/getAllSchool/{categoryId}")
    public AjaxResult getAllSchool(@PathVariable String categoryId,  @RequestBody PageDto pageDto)
    {
        if (categoryId!=null){
            final Integer schoolIdByCategoryId = schoolCategoryService.getSchoolIdByCategoryId(categoryId);

            final School school = schoolService.getSchoolBySchoolId(schoolIdByCategoryId);
            return AjaxResult.success(school);
        }
        else {
            schoolService.getAllSchool(pageDto);

        }
        return AjaxResult.success(pageDto);
    }

}
