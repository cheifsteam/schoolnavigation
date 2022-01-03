package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.SchoolService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/web/school/getAll")
    public AjaxResult getAll()
    {
        PageDto pageDto=new PageDto();
        pageDto.setPage(1);
        pageDto.setPageSize(10);
        schoolService.getAllSchool(pageDto);
        return AjaxResult.success(pageDto);
    }
    @PostMapping("/web/school/getLike")
    public AjaxResult getLike(@RequestParam(required = false) String filterName,@RequestParam PageDto pageDto)
    {
        schoolService.getLikeSchool(filterName,pageDto);
        return AjaxResult.success(pageDto);
    }
}
