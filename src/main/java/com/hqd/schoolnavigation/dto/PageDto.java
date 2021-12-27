package com.hqd.schoolnavigation.dto;


/**
 * @author 屈燃希
 * @version 1.0
 * @project
 */
public class PageDto<T> {
    private int page;
    private int pageSize;
    private T data;
    private int total;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
