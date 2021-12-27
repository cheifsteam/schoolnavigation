package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.ResponseDto;
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
    public ResponseDto getAllSchool(@RequestBody(required = false) PageDto pageDto){

        schoolService.getAllSchool(pageDto);

        return new ResponseDto(pageDto);
    }
    @RequestMapping("/admin/school/add")
    public ResponseDto addSchool(@RequestBody SchoolDto schoolDto){

        schoolService.addSchool(schoolDto);
        return new ResponseDto(true);
    }
    @DeleteMapping("/admin/school/delete/{id}")
    public ResponseDto deleteSchool(@PathVariable Integer id)
    {
        schoolService.deleteSchool(id);
        return new ResponseDto(true);
    }
    @RequestMapping("/admin/school/getByType")
    public ResponseDto getSchoolByType(@RequestParam String type ,@RequestBody PageDto pageDto)
    {

        schoolService.getSchoolByType(type,pageDto);

        return new ResponseDto(pageDto);
    }
    @RequestMapping("/admin/school/getLike")
    public ResponseDto getLikeSchool(@RequestBody PageDto pageDto,@RequestParam(required = false) String filterName)
    {

        schoolService.getLikeSchool(filterName,pageDto);

        return new ResponseDto(pageDto);
    }
    @PostMapping("/admin/school/update")
    public ResponseDto updateSchool(@RequestBody SchoolDto schoolDto)
    {

        schoolService.updateSchool(schoolDto);
        return new ResponseDto(true);
    }
}
