package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.Comment;
import com.hqd.schoolnavigation.domain.CommentExample;
import com.hqd.schoolnavigation.dto.CommentDto;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.CommentMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class CommentService {
    @Resource
    private CommentMapper commentMapper;
    private CommentExample commentExample;
    @Resource
    private SchoolService schoolService;
    @Resource
    private HttpSession httpSession;
    public void addComment(CommentDto commentDto)
    {
        if (schoolService.getSchoolBySchoolId(commentDto.getSchoolId())==null)
        {
            throw new MyException("该学校不存在");
        }
        Comment comment = BeanCopyUtils.copyBean(commentDto, Comment.class);
        commentMapper.insert(comment);
    }
    public void deleteComment(Integer id)
    {
        commentMapper.deleteByPrimaryKey(id);
    }
    public void getAllComment(PageDto pageDto)
    {
        commentExample=new CommentExample();
        commentList(commentExample,pageDto);

    }
    public void getCommentByUserId(Integer userId,PageDto pageDto)
    {
        commentExample=new CommentExample();
        final CommentExample.Criteria criteria = commentExample.createCriteria().andUserIdEqualTo(userId);
        commentExample.or(criteria);
        commentList(commentExample,pageDto);
    }
    public void commentList(CommentExample commentExample,PageDto pageDto)
    {
        PageHelper.startPage(pageDto.getPage(),pageDto.getPageSize());
        final List<Comment> comments = commentMapper.selectByExample(commentExample);
        final List<CommentDto> list = BeanCopyUtils.copyListProperties(comments, CommentDto::new);
        PageInfo<Comment> pageInfo=new PageInfo<>(comments);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setData(list);

    }
    public void getCommentBySchoolId(Integer schoolId,PageDto pageDto)
    {
        if (schoolService.getSchoolBySchoolId(schoolId)==null)
        {
            throw new MyException("该学校不存在");
        }
        commentExample=new CommentExample();
        final CommentExample.Criteria criteria = commentExample.createCriteria().andSchoolIdEqualTo(schoolId);
        commentExample.or(criteria);
        commentList(commentExample,pageDto);
    }

}
