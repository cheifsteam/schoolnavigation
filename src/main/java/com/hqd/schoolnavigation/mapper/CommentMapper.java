package com.hqd.schoolnavigation.mapper;

import com.hqd.schoolnavigation.domain.Comment;
import com.hqd.schoolnavigation.domain.CommentExample;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}