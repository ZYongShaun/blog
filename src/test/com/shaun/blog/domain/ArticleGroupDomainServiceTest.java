package com.shaun.blog.domain;

import com.shaun.blog.BaseTest;
import com.shaun.blog.po.ArticleGroup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by shaun on 16/9/26.
 */
public class ArticleGroupDomainServiceTest extends BaseTest{

    @Autowired
    ArticleGroupDomainService articleGroupDomainService;

    @Test
    public void testInsert(){
        ArticleGroup articleGroup = new ArticleGroup();
        articleGroup.setArticlesGroupName("测试2");
        articleGroup.setDescription("第2个");

        articleGroupDomainService.insertSelective(articleGroup);
    }
}
