package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.SchoolInformationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserSchoolInformationController {
    @Resource
    private SchoolInformationService schoolInformationService;
    @PostMapping("/web/schoolInformation/get/{Id}")
    public AjaxResult getInformationById(@PathVariable Integer Id)
    {
        final SchoolInformation information = schoolInformationService.getInformation(Id);
        return AjaxResult.success("获取成功",information);
    }
    @PostMapping("/web/schoolInformation/getAll")
    public AjaxResult getAllInformation(@RequestBody PageDto pageDto)
    {
        schoolInformationService.getAllInformation(pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/web/schoolInformation/getAllBySchoolId/{SchoolId}")
    public AjaxResult getAllInformationBySchoolId(@PathVariable Integer SchoolId, @RequestBody PageDto pageDto)
    {
        schoolInformationService.getAllInformationBySchoolId(SchoolId,pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/web/schoolInformation/home")
    public AjaxResult getHomeInformation(){
        final PageDto pageDto = new PageDto();
        pageDto.setPage(1);
        pageDto.setPageSize(10);
        schoolInformationService.getAllInformation(pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
}
