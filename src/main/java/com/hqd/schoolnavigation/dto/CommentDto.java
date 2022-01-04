package com.hqd.schoolnavigation.dto;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class CommentDto {
    private Integer id;

    private Integer userId;

    private String content;
    private Integer schoolId;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
