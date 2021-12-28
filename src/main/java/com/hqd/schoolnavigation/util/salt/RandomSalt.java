package com.hqd.schoolnavigation.util.salt;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Arrays;

public class RandomSalt {
    @Test
   public void getSalt()
   {

       SecureRandom random = new SecureRandom();
       byte bytes[] = new byte[24];
       random.setSeed(System.currentTimeMillis());  //使用系统时间作为种子
       random.nextBytes(bytes);
       String salt = org.apache.commons.codec.binary.Base64.encodeBase64String(bytes);
       System.out.println("salt:" + salt);
       System.out.println("bytes:" + Arrays.toString(bytes));
   }
}
