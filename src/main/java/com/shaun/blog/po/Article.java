package com.shaun.blog.po;

import com.shaun.blog.frame.BaseBean;

public class Article extends BaseBean{
    private Integer articleId;

    private Byte articlesGroupId;

    private String title;

    private String description;

    private String picUrl;

    private String content;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Byte getArticlesGroupId() {
        return articlesGroupId;
    }

    public void setArticlesGroupId(Byte articlesGroupId) {
        this.articlesGroupId = articlesGroupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}