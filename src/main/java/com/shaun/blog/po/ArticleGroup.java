package com.shaun.blog.po;

import com.shaun.blog.frame.BaseBean;

public class ArticleGroup extends BaseBean{
    private Integer articlesGroupId;

    private String articlesGroupName;

    private String description;

    public Integer getArticlesGroupId() {
        return articlesGroupId;
    }

    public void setArticlesGroupId(Integer articlesGroupId) {
        this.articlesGroupId = articlesGroupId;
    }

    public String getArticlesGroupName() {
        return articlesGroupName;
    }

    public void setArticlesGroupName(String articlesGroupName) {
        this.articlesGroupName = articlesGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}