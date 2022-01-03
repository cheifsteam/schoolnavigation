package com.hqd.schoolnavigation.dto;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */

public class UserDto {
    private Integer id;

    private String phoneNumber;

    private String password;

    private String nickname;
    private String token;

    public UserDto() {
    }

    public UserDto(Integer id, String nickname, String token) {
        this.id = id;
        this.nickname = nickname;
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String salt;
    private String img;
    private String passwordConfirm;
    private String newPassword;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
