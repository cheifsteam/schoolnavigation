package com.hqd.schoolnavigation.service;

import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.dto.SchoolInformationDto;
import com.hqd.schoolnavigation.mapper.SchoolInformationMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class SchoolInformationService {
    @Resource
    private SchoolInformationMapper schoolInformationMapper;
    public SchoolInformation  getInformation(Integer id)
    {
        final SchoolInformation schoolInformation = schoolInformationMapper.selectByPrimaryKey(id);

        return schoolInformation;
    }
    public void  addInformation(SchoolInformationDto schoolInformationDto){
        final SchoolInformation schoolInformation = BeanCopyUtils.copyBean(schoolInformationDto, SchoolInformation.class);
        schoolInformationMapper.insert(schoolInformation);
    }
    public void  deleteInformation(Integer id)
    {
        schoolInformationMapper.deleteByPrimaryKey(id);
    }

}
