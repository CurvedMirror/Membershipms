package com.jie.pojo;

import java.util.List;

/**
 * @author jie
 * @date 2019/5/30 -10:51
 */
public class Page<T> {

    private int pageNo;
    private int pageSize;
    private int pageCount;
    private List<T> list ;
    private int count;

    public Page(int pageNo, int pageSize, int count) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.count = count;
        this.pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
