package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.dto.AjaxResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserSchoolInformation {
    @PostMapping("/web/schoolInformation/get/{id}")
    public AjaxResult getInformation(@PathVariable Integer id){

        return  AjaxResult.success();
    }
}
