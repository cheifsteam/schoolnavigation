package com.hqd.schoolnavigation.mapper;

import com.hqd.schoolnavigation.domain.SchoolInformation;
import com.hqd.schoolnavigation.domain.SchoolInformationExample;
import java.util.List;

public interface SchoolInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolInformation record);

    int insertSelective(SchoolInformation record);

    List<SchoolInformation> selectByExampleWithBLOBs(SchoolInformationExample example);

    List<SchoolInformation> selectByExample(SchoolInformationExample example);

    SchoolInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolInformation record);

    int updateByPrimaryKeyWithBLOBs(SchoolInformation record);

    int updateByPrimaryKey(SchoolInformation record);
}