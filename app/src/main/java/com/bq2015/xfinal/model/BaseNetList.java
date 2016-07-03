package com.bq2015.xfinal.model;

import java.util.List;

/**
 * Description:date下面一个list:[]的base
 * Created by: bq2015
 * Date 2016/4/7
 */
public class BaseNetList<T> {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}