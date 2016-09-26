package com.shaun.blog.interceptor;

import com.shaun.blog.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created with IntelliJ IDEA.
 * User: shaun
 * Date: 2015-01-15
 * Time: 16:37
 */
@Service
public class VerifyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(VerifyFilter.class);

    private static final String PRO_FILENAME = "project_res";
    private static final String NOT_FILTER = "notfilter";
    private static final String CONTENTTYPE_CHARSET = "application/json; charset=UTF-8";//
    private static final String TICKET_ERROR_JSON = "{\"code\":-10003, \"msg\":\"ticket错误，请重新登录\"}";

    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        chain.doFilter(request, response);
    }


    public void destroy() {
        // TODO Auto-generated method stub
    }

    private boolean checkUrl(String logUrl){
        String[] notFilter = CommonUtils.getPropertyValue(PRO_FILENAME,NOT_FILTER).split(";");

        //url以css和js结尾的不进行拦截
        if (logUrl.endsWith(".css") || logUrl.endsWith(".js") || logUrl.endsWith(".png") || logUrl.endsWith(".jpg")) {
            return false;
        }
        //含有notFilter中的任何一个则不进行拦截
        for (String s : notFilter) {
            if (logUrl.indexOf(s) != -1) {
                return false;
            }
        }
        return true;
    }
}
