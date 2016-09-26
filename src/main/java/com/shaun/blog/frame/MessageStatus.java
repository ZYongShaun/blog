package com.shaun.blog.frame;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-7
 * Time: 上午2:29
 * To change this template use File | Settings | File Templates.
 */
public class MessageStatus {

    private static Map<Integer,String> map = new HashMap<Integer,String>();

    /**
     * 消息编号
     */
    public static final int MS_SUCCESS = 1;
    public static final int LOGIN_ERROR=-100;
    public static final int NOT_LOGIN=-101;
    public static final int USERNO_REPEAT=-102;
    public static final int RELATION_REPEAT=-103;
    public static final int COMPANY_FAIL=-104;
    public static final int IMAGE_FAIL=-200;
    public static final int HTTP_ERROR_STATUS=-404;


    /**
     * 获取
     * @param statusCode
     * @return
     */
    public static String getStatusText(int statusCode) {
         return map.get(statusCode);
    }


    static {
        map.put(MS_SUCCESS, "SUCCESS");
        map.put(LOGIN_ERROR,"登陆名或密码错误！请重新登陆");
        map.put(NOT_LOGIN,"用户未登录!");
        map.put(HTTP_ERROR_STATUS,"网络访问错误");
        map.put(USERNO_REPEAT,"用户编号重复！请重写设置");
        map.put(RELATION_REPEAT,"数据重复！请重写设置");
        map.put(COMPANY_FAIL,"公司不存在！请重写设置");
        map.put(IMAGE_FAIL,"图片上传失败！");
    }
}
