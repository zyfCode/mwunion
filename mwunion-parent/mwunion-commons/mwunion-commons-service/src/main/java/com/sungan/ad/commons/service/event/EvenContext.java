package com.sungan.ad.commons.service.event;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by zhangyf18255 on 2017/4/23.
 */
public class EvenContext extends LinkedHashMap {
    private Object target;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
