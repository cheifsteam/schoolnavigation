package com.hqd.schoolnavigation.util.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class Upload{
    public static String uploadFile(MultipartFile file, String resSort) throws Exception {
        String shortPath =  file.getOriginalFilename();
        File dest = new File(resSort, shortPath);
        if (!dest.getParentFile().exists()) {
            boolean rel = dest.getParentFile().mkdirs();
            if (!rel) {
                throw new Exception("文件夹创建失败");
            }
        }
        InputStream is = file.getInputStream();
        OutputStream os = new FileOutputStream(dest);
        try {
            byte[] buffer = new byte[8 * 1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
        return shortPath;
    }
}
