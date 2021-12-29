package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.Admin;
import com.hqd.schoolnavigation.domain.AdminExample;
import com.hqd.schoolnavigation.dto.AdminDto;
import com.hqd.schoolnavigation.dto.PageDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.AdminMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import com.hqd.schoolnavigation.util.jwt.WebTokenUtil;
import com.hqd.schoolnavigation.util.security.Encrypt;
import com.hqd.schoolnavigation.util.security.SecurityRandom;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    private AdminExample adminExample;
    @Resource
    private WebTokenUtil webTokenUtil;
    //添加管理员
    public void addAdmin(AdminDto adminDto) {
        Admin admin = BeanCopyUtils.copyBean(adminDto, Admin.class);
        System.out.println("fafas");
        if (getAdminByName(admin.getAdminName())!=null) {

            throw new MyException("该管理员已经存在");

        }
        else {

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
    public String AdminLogin(AdminDto adminDto){
         Admin admin = getAdminByName(adminDto.getAdminName());
        if (admin==null){
            throw new MyException("账号错误");
        }
        if ((admin.getPassword().equals(Encrypt.MD5Encrypt(admin.getSalt()+adminDto.getPassword()))))
        {
            throw new MyException("密码错误");
        }
        String token=createWebToken(admin);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Token", token);
        return token;
    }
    public Admin getAdminByName(String name)
    {
        adminExample=new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria().andAdminNameEqualTo(name);
        adminExample.or(criteria);

        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.isEmpty())
        {
            return null;
        }
        return admins.get(0);
    }
    public String createWebToken(Admin admin){
        Instant now = Instant.now();
        String secretKey= WebTokenUtil.getSecretKey();
        String token = webTokenUtil.create(secretKey, String.valueOf(admin.getId()), now, 5);
        return token;
    }
}
