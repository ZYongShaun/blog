package com.shaun.blog.frame;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-9
 * Time: 上午8:30
 * To change this template use File | Settings | File Templates.
 */
public class JsonResult {
    private int code;
    private String msg;
    private Object data;

    public JsonResult() {
        code = 1;
        msg = "SUCCESS";
        data = null;
    }

    public JsonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
