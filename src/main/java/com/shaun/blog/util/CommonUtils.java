package com.shaun.blog.util;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-10
 * Time: 上午1:34
 * To change this template use File | Settings | File Templates.
 */
public class CommonUtils {


    public static Integer getFirstIndex(int pageSize,int pageIndex){
        int rowIndex=((pageIndex-1)*pageSize);
        return rowIndex;
    }


    /**
     * 得到属性文件
     * @param propertyName
     * @return
     */
    public static ResourceBundle getProperty(String propertyName){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(propertyName);
        return resourceBundle;
    }


    public static String getPropertyValue(String propertyName,String key){
        return  getProperty(propertyName).getString(key);
    }


/*    public String uploadImage(MultipartFile file) {
        try {
            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = getRandomFileName(suffix);
            File destFile = new File(configService.getImgPath() + fileName);
            LOGGER.debug("filename={}", fileName);
            file.transferTo(destFile);
            return fileName.replace('\\', '/');
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("图片上传失败：{}:{}", e.getClass().getName(), e.getMessage());
            throw new ServiceRuntimeException(ServiceExceptionType.IMAGE_PROCESS_FAILED);
        }
    }*/


    public static String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
