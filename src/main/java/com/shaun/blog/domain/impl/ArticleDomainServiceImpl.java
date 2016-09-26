package com.shaun.blog.domain.impl;

import com.shaun.blog.dao.ArticleDao;
import com.shaun.blog.domain.ArticleDomainService;
import com.shaun.blog.domain.BaseDomain;
import com.shaun.blog.po.Article;
import com.shaun.blog.po.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shaun on 16/4/4.
 */
@Service
public class ArticleDomainServiceImpl extends BaseDomain implements ArticleDomainService {

    @Autowired
    ArticleDao articleDao;

    public int insertSelective(Article record) {
        setBasicProp(record);
        return articleDao.insertSelective(record);
    }

    public List<Article> select(ArticleRepository example) {
        return articleDao.selectByExample(example);
    }

    public int updateSelective(Article record) {
        setBasicProp(record);
        return articleDao.updateByPrimaryKeySelective(record);
    }

    public int delete(ArticleRepository example) {
        return articleDao.deleteByExample(example);
    }

    public List<Article> findArticleById(int id) {
        ArticleRepository articleRepository = new ArticleRepository();
        articleRepository.or().andArticleIdEqualTo(id);
        return select(articleRepository);
    }
}
