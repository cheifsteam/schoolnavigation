package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.ResponseDto;
import com.hqd.schoolnavigation.dto.SchoolDto;
import com.hqd.schoolnavigation.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class SchoolController {
    @Autowired
    public SchoolService schoolService;
    @RequestMapping("/admin/school/getAll")
    public ResponseDto getAllSchool(@RequestBody(required = false) PageDto pageDto){
        ResponseDto responseDto=new ResponseDto();
        schoolService.getAllSchool(pageDto);
        responseDto.setData(pageDto);
        return responseDto;
    }

}
