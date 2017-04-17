package com.sungan.ad.commons.annexes;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by zhangyf18255 on 2017/4/16.
 */
public abstract class AnnexesAbastractHandler implements AnnexesHandler,InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        AnnexesUtil.setHandler(this);
    }
}
