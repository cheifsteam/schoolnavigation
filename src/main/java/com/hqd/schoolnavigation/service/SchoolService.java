package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.domain.SchoolExample;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.SchoolDto;
import com.hqd.schoolnavigation.mapper.SchoolMapper;
import com.hqd.schoolnavigation.util.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class SchoolService {
    @Resource
    private SchoolMapper schoolMapper;

    private SchoolExample schoolExample;

    public void getAllSchool(PageDto pageDto)
    {
        schoolExample=new SchoolExample();
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        List<School> schools = schoolMapper.selectByExample(schoolExample);
        PageInfo<School> pageInfo=new PageInfo<>(schools);
        List<SchoolDto> schoolDtos = BeanCopyUtils.copyListProperties(schools,SchoolDto::new);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setData(schoolDtos);
    }
    public void addSchool(){

    }
}
