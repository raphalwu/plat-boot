package com.xxx.plat.base.dto.page;


import com.xxx.plat.base.dto.Queryable;

public interface Page<Q extends Queryable<?>> {
    int startIndex();

    int pageSize();

    int currentPage();

    Q getQuery();
}
