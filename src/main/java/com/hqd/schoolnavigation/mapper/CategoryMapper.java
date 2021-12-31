package com.hqd.schoolnavigation.mapper;

import com.hqd.schoolnavigation.domain.Category;
import com.hqd.schoolnavigation.domain.CategoryExample;
import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    List<Category> selectCategoryOrderBySort(String parent);
}