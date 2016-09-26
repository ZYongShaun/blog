package com.shaun.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: shaun
 * Date: 2015-01-15
 * Time: 16:36
 */
public class CookieVerifyInterceptor implements HandlerInterceptor {

    private static final String TICKET = "ticket";

    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                             Object arg2) throws Exception {
        // TODO Auto-generated method stub
        String ticket= getCookieValue(arg0.getCookies()) ;

        return false;
    }



    private String getCookieValue(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase(TICKET)) {
                return cookie.getValue();
            }
        }
        return "";
    }
}