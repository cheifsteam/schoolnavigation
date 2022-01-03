package com.hqd.schoolnavigation.service;

import com.hqd.schoolnavigation.domain.Category;
import com.hqd.schoolnavigation.domain.CategoryExample;
import com.hqd.schoolnavigation.domain.School;
import com.hqd.schoolnavigation.domain.SchoolExample;
import com.hqd.schoolnavigation.dto.CategoryDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.CategoryMapper;
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
        category.setId(UUIDUtils.getUUID());
        categoryMapper.insert(category);
    }

    public void updataCategory(CategoryDto categoryDto) {
        CategoryExample example = new CategoryExample();
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void addSubLevelCategory(CategoryDto categoryDto)
    {
        Category category = BeanCopyUtils.copyBean(categoryDto, Category.class);
        if (!isExistParent(category.getParent()))
        {
            throw new MyException("上级目录有误");
        }
        category.setId(UUIDUtils.getUUID());
        categoryMapper.insert(category);
    }

    @Transactional
    public void deleteCategory(String id)
    {
        if (categoryMapper.selectByPrimaryKey(id)==null)
        {
            throw new MyException("找不到该分类");
        }

        try {
            deleteChildren(id);
            categoryMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
           throw new MyException("若想删除该分类请删除该分类的所有学校");
        }
    }

    /**
     * 删除子类分类
     */
    public void deleteChildren(String id)
    {
        Category category = categoryMapper.selectByPrimaryKey(id);

        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentEqualTo(category.getId());
        List<Category> categories = categoryMapper.selectByExample(example);

        if(categories == null) {
            return;
        }

        for(int i = 0; i < categories.size(); i++)
        {
            CategoryExample example1 = new CategoryExample();
            example1.createCriteria().andParentEqualTo(categories.get(i).getId());
            categoryMapper.deleteByExample(example1);
        }
        categoryMapper.deleteByExample(example);

    }

//    /**
//     * 删除二级分类
//     */
//    public void deleteChildren(String id)
//    {
//        Category category = categoryMapper.selectByPrimaryKey(id);
//        if("00000000".equals(category.getParent())) {
//            CategoryExample example = new CategoryExample();
//            example.createCriteria().andParentEqualTo(category.getId());
//            categoryMapper.deleteByExample(example);
//        }
//    }

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

    public List getAllCategory()
    {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        return categories;
    }


}
