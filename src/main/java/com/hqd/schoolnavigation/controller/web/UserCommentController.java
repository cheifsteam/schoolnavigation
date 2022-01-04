package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.CommentDto;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserCommentController {
    @Resource
    public CommentService commentService;
    @Resource
    public HttpSession httpSession;
    @PostMapping("/web/user/addComment")
    public AjaxResult addComment(@RequestBody CommentDto commentDto)
    {
        Integer userId = (Integer) httpSession.getAttribute("id");
        commentDto.setUserId(userId);
        commentService.addComment(commentDto);
        return AjaxResult.success("添加成功");
    }

    @PostMapping("/web/user/getCommentByUserId")
    public AjaxResult getCommentByUserId(@RequestBody PageDto pageDto)
    {
        Integer userId = (Integer) httpSession.getAttribute("id");
        commentService.getCommentByUserId(userId,pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/web/user/getCommentBySchoolId/{schoolId}")
    public AjaxResult getCommentBySchoolId(@PathVariable Integer schoolId,@RequestBody PageDto pageDto)
    {
        commentService.getCommentBySchoolId(schoolId,pageDto);
        return AjaxResult.success("获取成功",pageDto);

    }

}
