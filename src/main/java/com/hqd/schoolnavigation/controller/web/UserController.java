package com.hqd.schoolnavigation.controller.web;

import com.hqd.schoolnavigation.Redis.RedisCache;
import com.hqd.schoolnavigation.constant.Constants;
import com.hqd.schoolnavigation.domain.User;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.Map;

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
    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) { // windos系统
                registry.addResourceHandler("/img/user/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_WIN_PATH + "\\img\\user\\");
            } else { // MAC、Linux系统
                registry.addResourceHandler("/img/user/**")
                        .addResourceLocations("file:" + Constants.RESOURCE_MAC_PATH + "/img/user/");
            }
        }
    }
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
         Map<String, Object> data = userService.userLogin(userDto);
        return AjaxResult.success("登录成功",data);

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
    @PostMapping("/web/user/updateInfo")
    public AjaxResult UpdateInfo(@RequestBody UserDto userDto)
    {
        userService.updateInfo(userDto);
        return AjaxResult.success("修改用户信息成功");
    }
    @PostMapping("/web/user/updateImg")
    public AjaxResult UpdateImg(@RequestParam("file") MultipartFile avatorFile, @RequestParam("dir") String dir)
    {
        userService.updateImg(avatorFile,dir);
        return AjaxResult.success("修改头像成功");
    }
    @PostMapping("/web/user/register")
    public AjaxResult Register(@RequestBody  UserDto userDto){
    userService.Register(userDto);
    return AjaxResult.success("注册成功");
    }
    @PostMapping("/web/user/get/{id}")
    public AjaxResult getUserById(@PathVariable String id)
    {
        final User userById = userService.getUserById(Integer.valueOf(id));
        return AjaxResult.success("获取成功",userById);
    }
}
