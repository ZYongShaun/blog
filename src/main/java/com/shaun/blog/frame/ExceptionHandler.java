package com.shaun.blog.frame;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-9
 * Time: 上午8:30
 * To change this template use File | Settings | File Templates.
 */

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class ExceptionHandler implements HandlerExceptionResolver {

    private static final String CONTENTTYPE_CHARSET = "application/json; charset=UTF-8";//
    private static final int HTTPCODE_ERROR = 500;  //500服务器错误
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    /**
     * 负责收集抛出异常，输出给前端接口
     */
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse response, Object o, Exception e) {
        ObjectMapper om = new ObjectMapper();
        response.setContentType(CONTENTTYPE_CHARSET);
        JsonResult result = new JsonResult();
        if (e instanceof ServiceRuntimeException) {
            result.setCode(((ServiceRuntimeException) e).getErrorCode());
            result.setMsg(e.getMessage());
            logger.error(String.format("##系统异常，异常说明{0}##", e.getMessage()));
        }else if (e instanceof RuntimeException) {
            result.setCode(-1001);
            result.setMsg(e.getMessage());
            logger.error(String.format("##系统异常，异常说明{0}##", e.getMessage()));
        } else if (e instanceof SQLException) {
            result.setCode(-1002);
            result.setMsg(e.getMessage());
            logger.error(String.format("##SQL异常，异常说明{0}##", e.getMessage()));
        } else if (e instanceof IOException) {
            result.setCode(-1003);
            result.setMsg(e.getMessage());
            logger.error(String.format("##IO异常，异常说明{0}##", e.getMessage()));
        }
        try {
            om.writeValue(response.getWriter(), result);
        } catch (JsonGenerationException e1) {
            e1.printStackTrace();
        } catch (JsonMappingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            response.setStatus(HTTPCODE_ERROR);
        }
        return null;
    }
}
