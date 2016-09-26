package com.shaun.blog.domain;

import com.shaun.blog.frame.IService;
import com.shaun.blog.po.Article;
import com.shaun.blog.po.ArticleRepository;

import java.util.List;

/**
 * Created by shaun on 16/4/4.
 */
public interface ArticleDomainService extends IService<Article, ArticleRepository> {
    List<Article> findArticleById(int id);
}
