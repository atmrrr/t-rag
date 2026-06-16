package com.rag.system.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;

public class PageUtil {

    public static <T, R> IPage<R> pageChange(IPage<T> iPage, Function<T, R> function){

        List<R> collect = iPage.getRecords().stream().map(function).toList();

        IPage<R> changePage = new Page<>();
        changePage.setCurrent(iPage.getCurrent());
        changePage.setSize(iPage.getSize());
        changePage.setTotal(iPage.getTotal());
        changePage.setRecords(collect);

        return changePage;

    }

}
