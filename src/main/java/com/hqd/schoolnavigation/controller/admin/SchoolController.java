package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.constant.Constants;
import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.domain.SchoolCategory;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.SchoolCategoryDto;
import com.hqd.schoolnavigation.dto.SchoolDto;
import com.hqd.schoolnavigation.service.SchoolCategoryService;
import com.hqd.schoolnavigation.service.SchoolService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Autowired
    public SchoolCategoryService schoolCategoryService;
    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) { // windos系统
                registry.addResourceHandler("/img/school/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_WIN_PATH + "\\img\\school\\");
            } else { // MAC、Linux系统
                registry.addResourceHandler("/img/school/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_MAC_PATH + "/img/school/");
                registry.addResourceHandler("/song/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_MAC_PATH + "/song/");
            }
        }
    }

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

    /**
     * 根据学校id获取学校信息
     * @param Id
     * @return
     */
    @PostMapping("/admin/school/get/{Id}")
    public AjaxResult getSchoolBySchoolId(@PathVariable Integer Id)
    {
        final School school= schoolService.getSchoolBySchoolId(Id);
        return AjaxResult.success("获取成功",school);
    }
    /**
     * 查找学校下所有分类
     * @param schoolId
     */
    @PostMapping("/admin/school/list-category/{schoolId}")
    public AjaxResult listCategory(@PathVariable(value = "schoolId") Integer schoolId) {
        List<SchoolCategory> dtoList = schoolCategoryService.listBySchool(schoolId);
        return AjaxResult.success(dtoList);
    }
    @PostMapping("/admin/school/updateImg")
    public AjaxResult updateUserPic(@RequestParam("id") String id,@RequestParam("file") MultipartFile avatorFile,@RequestParam("dir") String dir) {
         String path = schoolService.updateImg(avatorFile, dir, Integer.valueOf(id));

        return AjaxResult.success("上传成功",path);
    }

}
