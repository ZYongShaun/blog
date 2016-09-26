package com.shaun.blog.domain;

import com.shaun.blog.frame.IService;
import com.shaun.blog.po.ArticleGroup;
import com.shaun.blog.po.ArticleGroupRepository;

import java.util.List;

/**
 * Created by shaun on 16/9/26.
 */
public interface ArticleGroupDomainService extends IService<ArticleGroup, ArticleGroupRepository>{
    List<ArticleGroup> getAllGroup();
}
