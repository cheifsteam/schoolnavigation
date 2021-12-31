package com.hqd.schoolnavigation.util.security;

import java.util.UUID;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class UUIDUtils {
    public  static  String getUUID(){
         String UUIDStr = UUID.randomUUID().toString().replaceAll("-","");

         return UUIDStr.substring(0,8);//获取前8位UUID

    }
}
