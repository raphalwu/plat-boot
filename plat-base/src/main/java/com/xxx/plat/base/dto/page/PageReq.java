package com.xxx.plat.base.dto.page;


import com.xxx.plat.base.dto.Queryable;

public class PageReq<Q extends Queryable<?>> implements Page<Q> {
    private int startIndex;
    private int pageSize;
    private Q query;

    public PageReq() {
        this.startIndex = 0;
        this.pageSize = 20;
    }

    public PageReq(Q query) {
        this.startIndex = 0;
        this.pageSize = 20;
        this.query = query;
    }

    public PageReq(int startIndex, Q query) {
        this();
        this.startIndex = startIndex;
        this.query = query;
    }

    public PageReq(int startIndex, int pageSize, Q query) {
        this(startIndex, query);
        this.query = query;
    }

    @Override
    public Q getQuery() {
        return this.query;
    }

    public void setQuery(Q query) {
        this.query = query;
    }

    @Override
    public int startIndex() {
        return this.startIndex;
    }

    @Override
    public int pageSize() {
        return this.pageSize;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int currentPage() {
        return this.startIndex / this.pageSize + 1;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }
}
