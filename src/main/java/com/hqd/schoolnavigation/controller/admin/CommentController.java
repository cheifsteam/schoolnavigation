package com.hqd.schoolnavigation.controller.admin;

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
public class CommentController {
    @Resource
    public CommentService commentService;

    @DeleteMapping("/admin/user/deleteComment/{Id}")
    public AjaxResult deleteComment(@PathVariable Integer Id)
    {
        commentService.deleteComment(Id);
        return AjaxResult.success("删除成功");
    }
    @PostMapping("/admin/user/getCommentByUserId/{userId}")
    public AjaxResult getCommentByUserId(@PathVariable Integer userId,@RequestBody PageDto pageDto)
    {
        commentService.getCommentByUserId(userId,pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/admin/user/getAllComment")
    public AjaxResult getAllComment(@RequestBody PageDto pageDto)
    {
        commentService.getAllComment(pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/admin/user/getCommentBySchoolId/{schoolId}")
    public AjaxResult getCommentBySchoolId(@PathVariable Integer schoolId,@RequestBody PageDto pageDto)
    {
        commentService.getCommentBySchoolId(schoolId,pageDto);
        return AjaxResult.success("获取成功",pageDto);

    }

}
