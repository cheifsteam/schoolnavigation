package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.Redis.RedisCache;
import com.hqd.schoolnavigation.domain.Admin;
import com.hqd.schoolnavigation.dto.AdminDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.AdminService;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class AdminController {
    @Autowired
    public AdminService adminService;
    @Resource
    public RedisCache redisCache;
    @PostMapping("/admin/admin/add")
    public AjaxResult addAdmin(@RequestBody AdminDto adminDto){
        adminService.addAdmin(adminDto);
        return AjaxResult.success();
    }
    @PostMapping("/admin/admin/delete/{id}")
    public AjaxResult deleteAdmin(@PathVariable  Integer id)
    {
        adminService.deleteAdmin(id);
        return AjaxResult.success();
    }
    @PostMapping("/admin/admin/getAll")
    public AjaxResult getAllAdmin(@RequestBody  PageDto pageDto)
    {
        adminService.getAllAdmins(pageDto);
        return AjaxResult.success(pageDto);
    }
    @PostMapping("/admin/admin/login")
    public AjaxResult AdminLogin(@RequestBody AdminDto adminDto)
    {
         String token = adminService.AdminLogin(adminDto);
        return AjaxResult.success("操作成功",token);

    }
    @PostMapping("/admin/admin/signOut")
    public AjaxResult AdminSignOut()
    {
        adminService.SignOut();
        return AjaxResult.success("登出成功");
    }
    @PostMapping("/admin/admin/updatePassword")
    public AjaxResult UpdatePassword(@RequestBody  AdminDto adminDto){
        adminService.UpdatePassword(adminDto);
        return AjaxResult.success("修改密码成功");
    }
    @PostMapping("/admin/admin/updateImg")
    public AjaxResult UpdateImg(@RequestBody  AdminDto adminDto)
    {
        adminService.UpdateImg(adminDto);
        return AjaxResult.success("修改头像成功");
    }

}
