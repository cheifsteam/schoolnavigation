package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.SchoolInformationDto;
import com.hqd.schoolnavigation.service.SchoolCategoryService;
import com.hqd.schoolnavigation.service.SchoolInformationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class SchoolInformationController {
    @Resource
    private SchoolInformationService schoolInformationService;

    @PostMapping("/admin/schoolInformation/add")
    public AjaxResult addInformation(@RequestBody  SchoolInformationDto schoolInformationDto) {
        schoolInformationService.addInformation(schoolInformationDto);
        return AjaxResult.success("添加成功");
    }

    @DeleteMapping("/admin/schoolInformation/delete/{Id}")
    public AjaxResult deleteInformation(@PathVariable  Integer Id)
    {
        schoolInformationService.deleteInformation(Id);
        return AjaxResult.success("删除成功");
    }
    @PostMapping("/admin/schoolInformation/getAllBySchoolId")
    public AjaxResult getAllInformationBySchoolId(@RequestParam Integer SchoolId, @RequestBody PageDto pageDto)
    {
        schoolInformationService.getAllInformationBySchoolId(SchoolId,pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/admin/schoolInformation/get/{Id}")
    public AjaxResult getInformationById(@PathVariable Integer Id)
    {
        final SchoolInformation information = schoolInformationService.getInformation(Id);
        return AjaxResult.success("获取成功",information);
    }
    @PostMapping("/admin/schoolInformation/update")
    public AjaxResult updateInformation(@RequestBody SchoolInformationDto schoolInformationDto)
    {
        schoolInformationService.updateInformation(schoolInformationDto);
        return AjaxResult.success("更新成功");
    }
    @PostMapping("/admim/schoolInformation/getAll")
    public AjaxResult getAllInformation(@RequestBody PageDto pageDto)
    {
        schoolInformationService.getAllInformation(pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }

}