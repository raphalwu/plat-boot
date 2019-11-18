package com.xxx.plat.base.dto.page;


import java.util.List;

public class PageResp<T> {

    private List<T> datas;
    private long totalCount = 0L;
    private long totalPages = 0L;
    private long currentPage = 1L;
    private long pageSize = 20L;
    private long startIndex = 1L;

    public PageResp() {
    }

    public long getStartIndex() {
        return this.startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
