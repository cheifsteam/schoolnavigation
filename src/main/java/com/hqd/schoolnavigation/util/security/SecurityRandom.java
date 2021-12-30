package com.hqd.schoolnavigation.util.security;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */

import java.security.SecureRandom;

public class SecurityRandom {

   public static String getRandom()
   {
       SecureRandom random = new SecureRandom();
       byte bytes[] = new byte[8];
       random.setSeed(200);  //使用系统时间作为种子
       random.nextBytes(bytes);
       String securityNum = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
       return securityNum;
   }
}
