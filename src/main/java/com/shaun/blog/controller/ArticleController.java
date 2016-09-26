package com.shaun.blog.controller;

import com.shaun.blog.domain.ArticleDomainService;
import com.shaun.blog.frame.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shaun on 16/4/4.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleDomainService articleDomainService;

    @RequestMapping(value = "/find")
    @ResponseBody
    public JsonResult find(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(required = true)  int id){
        return new JsonResult(1,"", articleDomainService.findArticleById(id));
    }
}
