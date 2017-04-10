package com.sungan.ad.commons.service.event;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
public abstract class EventListener  implements InitializingBean{

    public abstract EnumEventType getListenType();

    public abstract void handler(Object context);

    @Override
    public void afterPropertiesSet() throws Exception {
        EventQueen.register(this);
    }
}



