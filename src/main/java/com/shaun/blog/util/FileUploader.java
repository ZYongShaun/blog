package com.shaun.blog.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-13
 * Time: 上午8:45
 * To change this template use File | Settings | File Templates.
 */
public class FileUploader   {


    protected static Logger logger = LoggerFactory.getLogger(FileUploader.class);


    public static final String HEADIMG = "headimg";

    public static final String AMRAUDIO = "amr_audio";
    public static final String MP3AUDIO = "mp3_audio";
    public static final String CHATIMG = "chat_img";

    public static final String USER_IMAGEPATH="userimgurl";
    public static final String AUDIO_PATH= "audiopath";
    public static final String WISHIMG_PATH= "wishingurl";

    public static final String PROPERTIES_NAME = "project_res";
    private static final String STRS ="abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 生成文件名称
     * @return
     */
    public String getfileName() {
        return RandomStringUtils.random(5,STRS);
    }

    /**
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    public String saveFile(HttpServletRequest request,MultipartFile file,String Path) throws IOException {
        String fileName=getfileName()+"."+getExtensionName(file);
        File destFile = new File(getContextPath(request,Path) + File.separator + fileName);
        file.transferTo(destFile);
        return  fileName.replace("\\","/");
    }
    /**
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    public String saveFile(HttpServletRequest request,MultipartFile file,String Path,String fileName) throws IOException {
        fileName=fileName+"."+getExtensionName(file);
        File destFile = new File(getContextPath(request,Path) + File.separator + fileName);
        file.transferTo(destFile);
        return  fileName.replace("\\","/");
    }
    /**
     * 得到系统路径
     * @return
     */
    public   String getContextPath(HttpServletRequest request,String imgPath){
        StringBuffer sb=new StringBuffer();
        sb.append(request.getSession().getServletContext().getRealPath("/"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(
                PROPERTIES_NAME, Locale.getDefault());
        return sb.append(resourceBundle.getString(imgPath)).toString();
    }


    /**
     * 截取文件扩展名
     * @param
     * @return
     */
    private String  getExtensionName(MultipartFile file) {
        String exname=  FilenameUtils.getExtension(file.getOriginalFilename());

        logger.info("######文件扩展名{}"+exname);
        if(exname==null||"".equals(exname)){
            if(file.getContentType().contains("mp3")){
                exname="mp3";
            }else{
                exname="jpg";
            }
        }
        return  exname;
    }
}
