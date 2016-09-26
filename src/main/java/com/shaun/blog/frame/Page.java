package com.shaun.blog.frame;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-3-12
 * Time: 上午8:23
 * To change this template use File | Settings | File Templates.
 */
public class Page {

    private Integer pageIndex;
    private Integer pageSize;

    public Integer getFirstIndex(int pageSize,int pageIndex){
        int rowIndex=((pageIndex-1)*pageSize);
        return rowIndex;
    }


    public Page(int pageIndex,int pageSize){
        this.pageIndex=pageIndex;
        this.pageSize=pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
