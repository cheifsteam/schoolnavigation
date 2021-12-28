package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.Admin;
import com.hqd.schoolnavigation.domain.AdminExample;
import com.hqd.schoolnavigation.dto.AdminDto;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.AdminMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import com.hqd.schoolnavigation.util.security.Encrypt;
import com.hqd.schoolnavigation.util.security.SecurityRandom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class AdminService {
    @Resource
    public AdminMapper adminMapper;
    private AdminExample adminExample;
    //添加管理员
    public void addAdmin(AdminDto adminDto) {
        Admin admin = BeanCopyUtils.copyBean(adminDto, Admin.class);
        if (!isExistAdmin(admin.getAdminName())) {
            throw new MyException("该管理员已经存在");
        } else {
            admin.setSalt(SecurityRandom.getRandom());
            String encryptPassword = Encrypt.MD5Encrypt(admin.getPassword() + admin.getSalt());
            admin.setPassword(encryptPassword);
            adminMapper.insert(admin);
        }
    }
    //删除管理员
    public void deleteAdmin(Integer id)
    {
        adminMapper.deleteByPrimaryKey(id);
    }
    //获取所有管理员
    public void getAllAdmins(PageDto pageDto)
    {
        adminExample=new AdminExample();
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        List<AdminDto> adminDtos = BeanCopyUtils.copyListProperties(admins, AdminDto::new);
        PageInfo<AdminDto> pageInfo=new PageInfo<>(adminDtos);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setData(adminDtos);
    }
    public boolean isExistAdmin(String name)
    {
        adminExample=new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria().andAdminNameEqualTo(name);
        adminExample.or(criteria);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size()==0)
        {
            return true;
        }
        return false;
    }
}
