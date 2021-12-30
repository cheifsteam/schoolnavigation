package com.hqd.schoolnavigation.util.security;

import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class Encrypt {
    public static String MD5Encrypt(String source)
    {
        try {
            return DigestUtils.md5DigestAsHex(source.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
