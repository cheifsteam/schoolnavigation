package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController("webSchoolController")
@RequestMapping("/web/school")
public class SchoolController {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolController.class);
    public static final String BUSINESS_NAME = "课程";

    @Resource
    private SchoolService schoolService;


    @GetMapping("/list-new")
    public AjaxResult listNew() {
        PageDto pageDto = new PageDto();
        pageDto.setPage(1);
        pageDto.setPageSize(3);
        schoolService.getAllSchool(pageDto);
        return AjaxResult.success(pageDto);

    }

}
