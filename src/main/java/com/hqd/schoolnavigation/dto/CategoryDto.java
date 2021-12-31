package com.hqd.schoolnavigation.dto;

import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class CategoryDto {
    private String id;


    private String parent;

    private String name;

    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
