package com.shaun.blog.dao;

import com.shaun.blog.frame.IBaseDao;
import com.shaun.blog.po.Article;
import com.shaun.blog.po.ArticleRepository;

/**
 * Created by shaun on 16/9/26.
 */
public interface ArticleDao extends IBaseDao<Article, Integer, ArticleRepository> {
}