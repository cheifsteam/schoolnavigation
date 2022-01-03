package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.Redis.RedisCache;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @Resource
    public RedisCache redisCache;
    @PostMapping("/admin/user/add")
    public AjaxResult addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return AjaxResult.success();
    }
    @DeleteMapping("/admin/user/delete/{id}")
    public AjaxResult deleteUser(@PathVariable Integer id)
    {
        userService.deleteUser(id);
        return AjaxResult.success();
    }
    @PostMapping("/admin/user/getAll")
    public AjaxResult getAllUsers(@RequestBody(required = false) PageDto pageDto)
    {
        userService.getAllUsers(pageDto);
        return AjaxResult.success("获取成功",pageDto);
    }
    @PostMapping("/web/user/login")
    public AjaxResult userLogin(@RequestBody UserDto userDto)
    {
        String token = userService.userLogin(userDto);
        return AjaxResult.success("操作成功",token);

    }
    @PostMapping("/web/user/signOut")
    public AjaxResult AdminSignOut()
    {
        userService.SignOut();
        return AjaxResult.success("登出成功");
    }
    @PostMapping("/web/user/updatePassword")
    public AjaxResult UpdatePassword(@RequestBody  UserDto userDto){
        userService.UpdatePassword(userDto);
        return AjaxResult.success("修改密码成功");
    }
    @PostMapping("/web/user/updateImg")
    public AjaxResult UpdateImg(@RequestBody UserDto userDto)
    {
        userService.UpdateImg(userDto);
        return AjaxResult.success("修改头像成功");
    }
    @PostMapping("/web/user/register")
    public AjaxResult Register(@RequestBody  UserDto userDto){
    userService.Register(userDto);
    return AjaxResult.success("注册成功");
    }
}
