package com.hqd.schoolnavigation.util.security;

import org.springframework.util.DigestUtils;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class Encrypt {
    public static String MD5Encrypt(String source)
    {
        return DigestUtils.md5DigestAsHex(source.getBytes());
    }
}
