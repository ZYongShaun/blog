package com.shaun.blog.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-27
 * Time: 上午3:42
 * To change this template use File | Settings | File Templates.
 */
public class GsonUtils {

    private static Gson gson = null;
    private static Gson htmlGson = null;

    private GsonUtils() {
        // Exists only to defeat instantiation.
    }


    /**
     * 添加一个synchronized线程安全
     * @return
     */
    public static synchronized  Gson getInstance() {
        if (gson == null) {
            gson =   new GsonBuilder().create();
        }
        return gson;
    }

    public static synchronized  Gson getHtmlInstance() {
        if (htmlGson == null) {
            htmlGson =   new GsonBuilder().disableHtmlEscaping().create();
        }
        return htmlGson;
    }
}
