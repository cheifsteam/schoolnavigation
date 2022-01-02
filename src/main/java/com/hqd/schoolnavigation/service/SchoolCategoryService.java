package com.hqd.schoolnavigation.service;

import com.hqd.schoolnavigation.domain.Category;
import com.hqd.schoolnavigation.domain.SchoolCategory;
import com.hqd.schoolnavigation.domain.SchoolCategoryExample;
import com.hqd.schoolnavigation.dto.AdminDto;
import com.hqd.schoolnavigation.dto.CategoryDto;
import com.hqd.schoolnavigation.dto.SchoolCategoryDto;
import com.hqd.schoolnavigation.mapper.SchoolCategoryMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import com.hqd.schoolnavigation.util.security.UUIDUtils;
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
public class SchoolCategoryService {
    @Resource
    public SchoolCategoryMapper schoolCategoryMapper;
    public SchoolCategoryExample schoolCategoryExample;

    @Transactional
    public void addCategory(Integer schoolId, List<CategoryDto> categoryList){
        SchoolCategoryExample example = new SchoolCategoryExample();
        example.createCriteria().andCategoryIdEqualTo(schoolId.toString());
        schoolCategoryMapper.deleteByExample(example);


        for (int i = 0; i <categoryList.size(); i++) {
//            addSchoolCategory(schoolId,categoryList.get(i).getId());
//            addSchoolCategory(schoolId,categoryList.get(i).getParent());

            CategoryDto categoryDto = categoryList.get(i);
            SchoolCategory schoolCategory = new SchoolCategory();
            schoolCategory.setId(UUIDUtils.getUUID());
            schoolCategory.setSchoolId(schoolId);
            schoolCategory.setCategoryId(categoryDto.getId());
            schoolCategoryMapper.insert(schoolCategory);
        }
    }

    public void addSchoolCategory(Integer schoolId,String categoryId)
    {
        SchoolCategoryDto schoolCategoryDto = new SchoolCategoryDto(UUIDUtils.getUUID(),schoolId, categoryId);
        final SchoolCategory schoolCategory = BeanCopyUtils.copyBean(schoolCategoryDto, SchoolCategory.class);
        schoolCategoryMapper.insert(schoolCategory);
    }

    public void updateCategory(Integer schoolId,List<CategoryDto>categoryList){

        deleteCategory(schoolId);
        addCategory(schoolId,categoryList);
    }
    public void deleteCategory(Integer schoolId)
    {
        schoolCategoryMapper.deleteBySchoolId(schoolId);
    }


    public List<SchoolCategory> listBySchool(Integer schoolId) {
        SchoolCategoryExample example = new SchoolCategoryExample();
        example.createCriteria().andSchoolIdEqualTo(schoolId);
        List<SchoolCategory> schoolCategoryList = schoolCategoryMapper.selectByExample(example);
        System.out.println(schoolCategoryList.toString());
        return schoolCategoryList;
    }
}
