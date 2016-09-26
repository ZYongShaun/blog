package com.shaun.blog.domain;

import com.shaun.blog.frame.BaseBean;

import java.util.Date;

/**
 * Created by shaun on 16/9/26.
 */
public class BaseDomain {

    public void setBasicProp(BaseBean baseBean){
        if(baseBean.getCreateTime() == null){
            baseBean.setCreateTime(new Date());
        }
        if (baseBean.getUpdateTime() == null){
            baseBean.setUpdateTime(new Date());
        }
    }
}
