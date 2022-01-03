package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.domain.SchoolExample;
import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.domain.SchoolInformationExample;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.SchoolInformationDto;
import com.hqd.schoolnavigation.dto.SchoolInformationDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.SchoolInformationMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class SchoolInformationService {
    @Resource
    private SchoolInformationMapper schoolInformationMapper;
    private SchoolInformationExample schoolInformationExample;
    public SchoolInformation  getInformation(Integer id)
    {
        final SchoolInformation schoolInformation = schoolInformationMapper.selectByPrimaryKey(id);

        return schoolInformation;
    }
    public void  addInformation(SchoolInformationDto schoolInformationDto){
        if (schoolInformationDto.getTime()==null)
        {
            schoolInformationDto.setTime(new Date());
        }
        final SchoolInformation schoolInformation = BeanCopyUtils.copyBean(schoolInformationDto, SchoolInformation.class);
        schoolInformationMapper.insert(schoolInformation);
    }
    public void  deleteInformation(Integer id)
    {
        schoolInformationMapper.deleteByPrimaryKey(id);
    }
    public void getAllInformationBySchoolId(Integer SchoolId, PageDto pageDto)
    {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        schoolInformationExample=new SchoolInformationExample();
        final SchoolInformationExample.Criteria criteria = schoolInformationExample.createCriteria().andSchoolIdEqualTo(SchoolId);
        schoolInformationExample.or(criteria);
        schoolInformationList(pageDto,schoolInformationExample);
    }
    public void updateInformation(SchoolInformationDto schoolInformationDto)
    {
        if (getInformation(schoolInformationDto.getId())==null)
        {
            throw new MyException("该资讯不存在");
        }
        final SchoolInformation schoolInformation = BeanCopyUtils.copyBean(schoolInformationDto, SchoolInformation.class);
        schoolInformationMapper.updateByPrimaryKeySelective(schoolInformation);

    }
    public void getAllInformation(PageDto pageDto)
    {
        schoolInformationExample=new SchoolInformationExample();
        schoolInformationList(pageDto,schoolInformationExample);
    }
    public void schoolInformationList(PageDto pageDto,SchoolInformationExample schoolInformationExample)
    {
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        List<SchoolInformation> schoolInformations = schoolInformationMapper.selectByExampleWithBLOBs(schoolInformationExample);
        PageInfo<SchoolInformation> pageInfo=new PageInfo<>(schoolInformations);
        List<SchoolInformationDto> schoolInformationDtos = BeanCopyUtils.copyListProperties(schoolInformations,SchoolInformationDto::new);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setData(schoolInformationDtos);
    }



}
