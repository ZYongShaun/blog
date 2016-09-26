package com.shaun.blog.domain.impl;

import com.shaun.blog.dao.ArticleGroupDao;
import com.shaun.blog.domain.ArticleGroupDomainService;
import com.shaun.blog.domain.BaseDomain;
import com.shaun.blog.po.ArticleGroup;
import com.shaun.blog.po.ArticleGroupRepository;
import com.shaun.blog.po.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shaun on 16/9/26.
 */
@Service
public class ArticleGroupDomainServiceImpl extends BaseDomain implements ArticleGroupDomainService {

    @Autowired
    ArticleGroupDao articleGroupDao;

    public int insertSelective(ArticleGroup record) {
        setBasicProp(record);
        return articleGroupDao.insertSelective(record);
    }

    public List<ArticleGroup> select(ArticleGroupRepository example) {
        return articleGroupDao.selectByExample(example);
    }

    public int updateSelective(ArticleGroup record) {
        setBasicProp(record);
        return articleGroupDao.updateByPrimaryKeySelective(record);
    }

    public int delete(ArticleGroupRepository example) {
        return articleGroupDao.deleteByExample(example);
    }

    public List<ArticleGroup> getAllGroup() {
        ArticleGroupRepository articleGroupRepository = new ArticleGroupRepository();
        return select(articleGroupRepository);
    }
}
