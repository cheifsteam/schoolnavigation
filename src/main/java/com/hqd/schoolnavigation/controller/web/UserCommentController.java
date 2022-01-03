package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.CommentDto;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserCommentController {
    @Resource
    public CommentService commentService;
    @PostMapping("/web/user/addComment")
    public AjaxResult addComment(@RequestBody CommentDto commentDto)
    {
        commentService.addComment(commentDto);
        return AjaxResult.success("添加成功");
    }
    @DeleteMapping("/web/user/deleteComment/{Id}")
    public AjaxResult deleteComment(@PathVariable Integer Id)
    {
        commentService.deleteComment(Id);
        return AjaxResult.success("删除成功");
    }
    @PostMapping("/web/user/getCommentByUserId/{userId}")
    public AjaxResult getCommentByUserId(@PathVariable Integer userId, PageDto pageDto)
    {
        commentService.getCommentByUserId(userId,pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }

}
