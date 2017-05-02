package com.sungan.ad.common.interceptor;

import java.io.Serializable;

/**
 * Created by zhangyf18255 on 2017/5/2.
 */
public class InterceptorBean implements Serializable {
    private String id;
    private Integer dayint;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDayint() {
        return dayint;
    }

    public void setDayint(Integer dayint) {
        this.dayint = dayint;
    }
}
