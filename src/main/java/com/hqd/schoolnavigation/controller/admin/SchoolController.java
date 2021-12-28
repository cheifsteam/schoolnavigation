package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.SchoolDto;
import com.hqd.schoolnavigation.service.SchoolService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@CrossOrigin
@RestController
public class SchoolController {
    @Autowired
    public SchoolService schoolService;
    @RequestMapping("/admin/school/getAll")
    public AjaxResult getAllSchool(@RequestBody(required = false) PageDto pageDto){

        schoolService.getAllSchool(pageDto);

        return AjaxResult.success(pageDto);
    }
    @RequestMapping("/admin/school/add")
    public AjaxResult addSchool(@RequestBody SchoolDto schoolDto){

        schoolService.addSchool(schoolDto);
        return AjaxResult.success(true);
    }
    @DeleteMapping("/admin/school/delete/{id}")
    public AjaxResult deleteSchool(@PathVariable Integer id)
    {
        schoolService.deleteSchool(id);
        return AjaxResult.success(true);
    }
    @RequestMapping("/admin/school/getByType")
    public AjaxResult getSchoolByType(@RequestParam String type ,@RequestBody PageDto pageDto)
    {

        schoolService.getSchoolByType(type,pageDto);

        return AjaxResult.success(pageDto);
    }
    @RequestMapping("/admin/school/getLike")
    public AjaxResult getLikeSchool(@RequestBody PageDto pageDto,@RequestParam(required = false) String filterName)
    {

        schoolService.getLikeSchool(filterName,pageDto);

        return AjaxResult.success(pageDto);
    }
    @PostMapping("/admin/school/update")
    public AjaxResult updateSchool(@RequestBody SchoolDto schoolDto)
    {

        schoolService.updateSchool(schoolDto);
        return AjaxResult.success(true);
    }
}
