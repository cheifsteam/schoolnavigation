package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.SchoolInformationDto;
import com.hqd.schoolnavigation.service.SchoolCategoryService;
import com.hqd.schoolnavigation.service.SchoolInformationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserSchoolInformation {
    @Resource
    private SchoolInformationService schoolInformationService;
    @PostMapping("/web/schoolInformation/get/{id}")
    public AjaxResult getInformation(@PathVariable Integer id){

        return  AjaxResult.success();
    }
    @PostMapping("/web/schoolInformation/add")
    public AjaxResult addInformation(SchoolInformationDto schoolInformationDto)
    {

        return AjaxResult.success("添加成功");
    }
}
