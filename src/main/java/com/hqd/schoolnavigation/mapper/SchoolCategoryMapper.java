package com.hqd.schoolnavigation.mapper;

import com.hqd.schoolnavigation.domain.SchoolCategory;
import com.hqd.schoolnavigation.domain.SchoolCategoryExample;
import java.util.List;

public interface SchoolCategoryMapper {
    int deleteByExample(SchoolCategoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(SchoolCategory record);

    int insertSelective(SchoolCategory record);

    List<SchoolCategory> selectByExample(SchoolCategoryExample example);

    SchoolCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SchoolCategory record);

    int updateByPrimaryKey(SchoolCategory record);

    int deleteBySchoolId(Integer id);
}