package com.hqd.schoolnavigation.service;

import com.hqd.schoolnavigation.domain.Category;
import com.hqd.schoolnavigation.domain.SchoolCategory;
import com.hqd.schoolnavigation.domain.SchoolCategoryExample;
import com.hqd.schoolnavigation.dto.SchoolCategoryDto;
import com.hqd.schoolnavigation.mapper.SchoolCategoryMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import com.hqd.schoolnavigation.util.security.UUIDUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class SchoolCategoryService {
    @Resource
    public SchoolCategoryMapper schoolCategoryMapper;
    public SchoolCategoryExample schoolCategoryExample;
    public void addCategory(Integer schoolId, List<Category> categoryList){
        for (int i = 0; i <categoryList.size(); i++) {
            addSchoolCategory(schoolId,categoryList.get(i).getId());
            addSchoolCategory(schoolId,categoryList.get(i).getParent());
        }
    }
    public void addSchoolCategory(Integer schoolId,String categoryId)
    {
        SchoolCategoryDto schoolCategoryDto = new SchoolCategoryDto(UUIDUtils.getUUID(),schoolId, categoryId);
        final SchoolCategory schoolCategory = BeanCopyUtils.copyBean(schoolCategoryDto, SchoolCategory.class);
        schoolCategoryMapper.insert(schoolCategory);
    }
    public void updateCategory(Integer schoolId,List<Category>categoryList){

        deleteCategory(schoolId);
        addCategory(schoolId,categoryList);
    }
    public void deleteCategory(Integer schoolId)
    {
        schoolCategoryMapper.deleteBySchoolId(schoolId);
    }
}
