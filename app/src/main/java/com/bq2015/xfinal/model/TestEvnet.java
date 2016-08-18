package com.bq2015.xfinal.model;

/**
 * @version V1.0
 * @Description: 事件测试，根据id过滤事件
 * @author: KyLin (SRS)
 * @date: 2016/8/18 11:40
 */
public class TestEvnet {
    private String id;
    private String name;

    public TestEvnet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
