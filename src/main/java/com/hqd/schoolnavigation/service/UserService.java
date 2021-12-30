package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageInfo;
import com.hqd.schoolnavigation.domain.*;
import com.hqd.schoolnavigation.domain.User;
import com.hqd.schoolnavigation.domain.User;
import com.hqd.schoolnavigation.dto.*;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.dto.UserDto;
import com.hqd.schoolnavigation.excpetion.MyException;
import com.hqd.schoolnavigation.mapper.UserMapper;
import com.hqd.schoolnavigation.util.copyUtils.BeanCopyUtils;
import com.hqd.schoolnavigation.util.jwt.WebTokenUtil;
import com.hqd.schoolnavigation.util.security.Encrypt;
import com.hqd.schoolnavigation.util.security.SecurityRandom;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.List;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
@Service
public class UserService {
    private UserExample userExample;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WebTokenUtil webTokenUtil;
    @Resource
    private HttpSession httpSession;
    public  String userLogin(UserDto userDto){
        User user = getUserByPhoneNumber(userDto.getPhoneNumber());
        if (user==null){
            throw new MyException("账号错误");
        }
        if ((user.getPassword().equals(Encrypt.MD5Encrypt(user.getSalt()+userDto.getPassword()))))
        {
            throw new MyException("密码错误");
        }
        String token=createWebToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Token", token);
        return token;

    }
    public void addUser(UserDto userDto){
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        if (getUserByPhoneNumber(user.getPhoneNumber())!=null) {

            throw new MyException("用户已经存在");

        }
        else {
            user.setSalt(SecurityRandom.getRandom());
            String encryptPassword = Encrypt.MD5Encrypt(user.getPassword() + user.getSalt());
            user.setPassword(encryptPassword);
            userMapper.insert(user);
        }
    }
    public void deleteUser(Integer id){

        userMapper.deleteByPrimaryKey(id);
    }
    public User getUserByPhoneNumber(String phoneNumber){
        userExample=new UserExample();
        final UserExample.Criteria criteria = userExample.createCriteria().andPhoneNumberEqualTo(phoneNumber);
        userExample.or(criteria);
        final List<User> users = userMapper.selectByExample(userExample);
        if (users==null){
            throw new MyException("用户不存在");
        }
        return users.get(0);
    }
    public void getAllUsers(PageDto pageDto){
        userExample=new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        List<UserDto> userDtos = BeanCopyUtils.copyListProperties(users, UserDto::new);
        PageInfo<UserDto> pageInfo=new PageInfo<>(userDtos);
        pageDto.setTotal((int) pageInfo.getTotal());
        pageDto.setData(userDtos);
    }
    public String createWebToken(User user){
        Instant now = Instant.now();
        String secretKey= WebTokenUtil.getSecretKey();
        String token = webTokenUtil.create(secretKey, String.valueOf(user.getId()), now, 5);
        return token;
    }
    public void SignOut(){
        Integer userid = (Integer) httpSession.getAttribute("id");
        webTokenUtil.deleteToken(userid);
    }
    public void UpdatePassword(UserDto userDto){
        Integer id = (Integer) httpSession.getAttribute("id");
        User user = userMapper.selectByPrimaryKey(id);
        if (user==null)
        {
            throw new MyException("管理员不存在");
        }
        if (!(user.getPassword().equals(Encrypt.MD5Encrypt(user.getSalt()+userDto.getPassword())))){
            throw new MyException("旧密码错误");
        }
        if (user.getPassword().equals(Encrypt.MD5Encrypt(user.getSalt()+userDto.getNewPassword())))
        {
            throw new MyException("新密码与旧密码一致");
        }
        if (!(userDto.getNewPassword().equals(userDto.getPasswordConfirm())))
        {
            throw new MyException("两次输入密码不一致");
        }
        //重设混淆盐
        user.setSalt(SecurityRandom.getRandom());
        user.setPassword(userDto.getNewPassword()+user.getSalt());
        userMapper.updateByPrimaryKeySelective(user);
    }
    public void  UpdateImg(UserDto userDto){
        Integer id = (Integer) httpSession.getAttribute("id");
        User user = userMapper.selectByPrimaryKey(id);
        if (user==null)
        {
            throw new MyException("管理员不存在");
        }
        if (userDto.getImg()==null)
        {
            throw new MyException("新头像不能为空");
        }
        user.setImg(userDto.getImg());
        userMapper.updateByPrimaryKeySelective(user);
    }
//    public void Register(UserDto userDto){
//
//    }

}
