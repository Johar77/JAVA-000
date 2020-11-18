package com.johar.jeektime.springdb.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: PageResult
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020/11/17 00:02
 * @Since: 1.0.0
 */
@Data
public class PageResult<T> {

    private long totalNum;

    private int pageSize;

    private int pageNum;

    public List<T> data;

    public PageResult() {
        this.data = new ArrayList<>();
    }

    public PageResult(long totalNum, int pageSize, int pageNum, List<T> data) {
        this.totalNum = totalNum;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.data = data;
    }
}