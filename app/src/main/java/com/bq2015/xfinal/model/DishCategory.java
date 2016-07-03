package com.bq2015.xfinal.model;

import java.io.Serializable;

/**
 *
 * Created by bq2015 on 2016/7/1.
 */
public class DishCategory implements Serializable {
  private String clientId;//	1
  private String dishTypeId;//	1
  private String name;//	招牌推荐

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(String dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
