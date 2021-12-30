package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.Redis.RedisCache;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.service.UserService;
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
    @PostMapping("/web/user/add")
    public AjaxResult addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return AjaxResult.success();
    }
    @PostMapping("/web/user/delete/{id}")
    public AjaxResult deleteUser(@PathVariable Integer id)
    {
        userService.deleteUser(id);
        return AjaxResult.success();
    }
    @GetMapping("/web/user/getAll")
    public AjaxResult getAllUsers(@RequestBody PageDto pageDto)
    {
        userService.getAllUsers(pageDto);
        return AjaxResult.success(pageDto);
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
    public AjaxResult UpdatePassword(UserDto userDto){
        userService.UpdatePassword(userDto);
        return AjaxResult.success("修改密码成功");
    }
    @PostMapping("/web/user/updateImg")
    public AjaxResult UpdateImg(UserDto userDto)
    {
        userService.UpdateImg(userDto);
        return AjaxResult.success("修改头像成功");
    }
//    @PostMapping("/web/user/register")
//    public AjaxResult Register(UserDto userDto){
//
//    }
}