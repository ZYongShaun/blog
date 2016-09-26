package com.shaun.blog.frame;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-8
 * Time: 上午6:22
 * To change this template use File | Settings | File Templates.
 */
public interface IService<T,E>  {

    /**
     * 插入实体
     * @param record
     * @return
     */
    int insertSelective(T record);


    /**
     * 选择实体
     * @param example
     * @return
     */
    List<T> select(E example);


    /**
     * 更新实体
     * @param record
     * @return
     */
    int updateSelective(T record);


    /**
     * 删除实体
     * @param example
     * @return
     */
    int delete(E example);





}
