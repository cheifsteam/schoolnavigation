package com.hqd.schoolnavigation.service;

import com.github.pagehelper.PageHelper;
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
import com.hqd.schoolnavigation.util.upload.Upload;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> userLogin(UserDto userDto){
        User user = getUserByPhoneNumber(userDto.getPhoneNumber());
        if (user==null){
            throw new MyException("账号错误");
        }
        String s=Encrypt.MD5Encrypt(userDto.getPassword()+user.getSalt());
        if(!(user.getPassword().equals(s)))
        {
            throw new MyException("密码错误");
        }
        String token=createWebToken(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Set-Token", token);
        HashMap<String,Object> hashMap=new HashMap();
        hashMap.put("id",user.getId());
        hashMap.put("nickname",user.getNickname());
        hashMap.put("phoneNumber",user.getPhoneNumber());
        hashMap.put("img",user.getImg());
        hashMap.put("token",token);
        return hashMap;
    }
    public void addUser(UserDto userDto){
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        if (getUserByPhoneNumber(user.getPhoneNumber())!=null) {

            throw new MyException("用户已经存在");

        }
        else {
            user.setSalt(SecurityRandom.getRandom());
            String encryptPassword = Encrypt.MD5Encrypt(user.getPassword()+user.getSalt());
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
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()){
          return null;
        }
        return users.get(0);
    }
    public void getAllUsers(PageDto pageDto){
        userExample=new UserExample();
        PageHelper.startPage(pageDto.getPage(), pageDto.getPageSize());
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo=new PageInfo<>(users);
        List<UserDto> userDtos = BeanCopyUtils.copyListProperties(users,UserDto::new);
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
            throw new MyException("用户不存在");
        }
        String s=Encrypt.MD5Encrypt(userDto.getPassword()+user.getSalt());
        if(!(user.getPassword().equals(s)))
        {
            throw new MyException("密码错误");
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
        String encryptPassword=Encrypt.MD5Encrypt(userDto.getNewPassword()+user.getSalt());
        user.setPassword(encryptPassword);
        userMapper.updateByPrimaryKeySelective(user);
    }
    public String updateImg(MultipartFile file,String dir){
        Integer id = (Integer) httpSession.getAttribute("id");
        User user = userMapper.selectByPrimaryKey(id);
        if (user==null)
        {
            throw new MyException("用户不存在");
        }
        String dest="D:\\java项目\\school\\schoolnavigation\\src\\img\\user";
        try {
            System.out.println("upload start = " + System.currentTimeMillis());
            String videoUrl = Upload.uploadFile(file, dest);
            user.setImg(dir+videoUrl);
            userMapper.updateByPrimaryKeySelective(user);
            System.out.println("upload end = " + System.currentTimeMillis());
            return videoUrl;
        } catch (Exception var3) {
            throw new MyException(var3.getMessage());
        }
    }
    public void Register(UserDto userDto){
        if (getUserByPhoneNumber(userDto.getPhoneNumber())!=null)
        {
            throw new MyException("手机号已经被注册");
        }
        if (userDto.getPhoneNumber().length()!=11||userDto.getPhoneNumber()==null)
        {
            throw new MyException("手机号长度为11位");
        }
        if (userDto.getPassword().length()<6||userDto.getPassword()==null)
        {
            throw new MyException("密码长度不能少于6位");
        }
        if (!(userDto.getPasswordConfirm().equals(userDto.getPassword())))
        {
            throw new MyException("两次输入的密码不一致");
        }
        if (userDto.getNickname()==null)
        {
            throw new MyException("呢称不能为空");
        }
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        user.setSalt(SecurityRandom.getRandom());
        String encryptPassword = Encrypt.MD5Encrypt(user.getPassword()+user.getSalt());
        user.setPassword(encryptPassword);
        userMapper.insert(user);
    }

    public void updateInfo(UserDto userDto)
    {
        final User user = BeanCopyUtils.copyBean(userDto, User.class);
        userMapper.updateByPrimaryKeySelective(user);


    }
    public User getUserById(Integer id)
    {
        final User user = userMapper.selectByPrimaryKey(id);
        return user;

    }
}
