package com.hqd.schoolnavigation.service;

import com.hqd.schoolnavigation.domain.Category;
import com.hqd.schoolnavigation.domain.CategoryExample;
import com.hqd.schoolnavigation.dto.CategoryDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.CategoryMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class CategoryService {
    private  static  final  String TopParent="00000000";
    @Resource
    private CategoryMapper categoryMapper;
    private CategoryExample categoryExample;
    public void addTopLevelCategory(CategoryDto categoryDto){
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        if (category.getParent()==null)
        {
            category.setParent(TopParent);
        }
        categoryMapper.insert(category);
    }
    public void addSubLevelCategory(CategoryDto categoryDto)
    {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        if (!isExistParent(category.getParent()))
        {
            throw new MyException("上级目录有误");
        }
        categoryMapper.insert(category);
    }


    public void deleteCategory(String id)
    {
        if (categoryMapper.selectByPrimaryKey(id)==null)
        {
            throw new MyException("找不到该分类");
        }
        categoryMapper.deleteByPrimaryKey(id);
    }
    public boolean isExistParent(String parent)
    {

        categoryExample=new CategoryExample();
        categoryExample.createCriteria().andIdEqualTo(parent);
        categoryExample.or();
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        if (categories.isEmpty())
        {
            return false;
        }
        return true;
    }
    public List getTopLevelCategory()
    {
        return getCategoryByParent(TopParent);
    }
    public List getSubLevelCategory(String parent)
    {
        return getCategoryByParent(parent);
    }
    public List getCategoryByParent(String parent)
    {

        List<Category> categories = categoryMapper.selectCategoryOrderBySort(parent);
        return categories;
    }

}
