package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.domain.SchoolExample;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.SchoolDto;
import com.hqd.schoolnavigation.mapper.SchoolMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Resource
    private SchoolCategoryService schoolCategoryService;

    private SchoolExample schoolExample;

    public void getAllSchool(PageDto pageDto)
    {
        schoolExample=new SchoolExample();
        SchoolList(pageDto,schoolExample);

    }


    @Transactional
    public void addSchool(SchoolDto schoolDto) {
        School school = BeanCopyUtils.copyBean(schoolDto, School.class);
        schoolMapper.insert(school);
        System.out.println("Makiori:" + schoolDto.getCategorys());
        schoolCategoryService.addCategory(school.getId(),schoolDto.getCategorys());


    }
    @Transactional
    public void deleteSchool(Integer id){
        schoolCategoryService.deleteCategory(id);
        schoolMapper.deleteByPrimaryKey(id);
    }
    public void getSchoolByType(String type,PageDto pageDto)
    {
        schoolExample=new SchoolExample();
        SchoolExample.Criteria criteria = schoolExample.createCriteria().andTypeEqualTo(type);
        schoolExample.or(criteria);
        SchoolList(pageDto,schoolExample);
    }
    public void getLikeSchool(String filterName,PageDto pageDto){
        String filter="%"+filterName+"%";
        schoolExample=new SchoolExample();
        final SchoolExample.Criteria criteria = schoolExample.or().andNameLike(filter);
        final SchoolExample.Criteria criteria1 = schoolExample.or().andLevelLike(filter);
        schoolExample.or(criteria1);
        SchoolList(pageDto,schoolExample);
    }

    public void updateSchool(SchoolDto schoolDto)
    {
        schoolExample=new SchoolExample();
        School school = BeanCopyUtils.copyBean(schoolDto, School.class);
        schoolCategoryService.updateCategory(school.getId(),schoolDto.getCategorys());
        schoolMapper.updateByPrimaryKeySelective(school);

    }
    public void SchoolList(PageDto pageDto,SchoolExample schoolExample)
    {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        List<School> schools = schoolMapper.selectByExampleWithBLOBs(schoolExample);
        PageInfo<School> pageInfo=new PageInfo<>(schools);
        List<SchoolDto> schoolDtos = BeanCopyUtils.copyListProperties(schools,SchoolDto::new);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setData(schoolDtos);
    }
    public School getSchoolBySchoolId(Integer Id)
    {
        return  schoolMapper.selectByPrimaryKey(Id);
    }
    public  void getAllSchoolBySchoolId(List<Integer> Id, PageDto pageDto)
    {
        schoolExample=new SchoolExample();
        final SchoolExample.Criteria criteria = schoolExample.createCriteria().andIdIn(Id);
        schoolExample.or(criteria);
        SchoolList(pageDto,schoolExample);
    }

}
