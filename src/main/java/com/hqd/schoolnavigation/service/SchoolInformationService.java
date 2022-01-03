package com.hqd.schoolnavigation.service;

import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.mapper.SchoolInformationMapper;
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
}
