package com.hqd.schoolnavigation.controller.admin;

import com.hqd.schoolnavigation.domain.Admin;
import com.hqd.schoolnavigation.dto.AdminDto;
import com.hqd.schoolnavigation.dto.AjaxResult;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@RestController
public class AdminController {
    @Autowired
    public AdminService adminService;
    @PostMapping("/admin/admin/add")
    public AjaxResult addAdmin(@RequestBody  AdminDto adminDto){
        adminService.addAdmin(adminDto);
        return AjaxResult.success();
    }
    @PostMapping("/admin/admin/delete{id}")
    public AjaxResult deleteAdmin(@PathVariable  Integer id)
    {
        adminService.deleteAdmin(id);
        return AjaxResult.success();
    }
    @GetMapping("/admin/admin/getAll")
    public AjaxResult getAllAdmin(@RequestBody  PageDto pageDto)
    {
        adminService.getAllAdmins(pageDto);
        return AjaxResult.success(pageDto);
    }
}
