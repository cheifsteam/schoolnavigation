package com.hqd.schoolnavigation.dto;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class SchoolCategoryDto {
    private String id;

    private Integer schoolId;

    private String categoryId;

    public SchoolCategoryDto(String id, Integer schoolId, String categoryId) {
        this.id=id;
        this.schoolId = schoolId;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
