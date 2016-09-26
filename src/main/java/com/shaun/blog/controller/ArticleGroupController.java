package com.shaun.blog.controller;

import com.shaun.blog.domain.ArticleGroupDomainService;
import com.shaun.blog.frame.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shaun on 16/9/26.
 */
@Controller
@RequestMapping("/articleGroup")
public class ArticleGroupController {

    @Autowired
    ArticleGroupDomainService articleGroupDomainService;

    @RequestMapping(value = "/findAll")
    @ResponseBody
    public JsonResult findAll(HttpServletRequest request, HttpServletResponse response){
        return new JsonResult(1,"", articleGroupDomainService.getAllGroup());
    }
}
