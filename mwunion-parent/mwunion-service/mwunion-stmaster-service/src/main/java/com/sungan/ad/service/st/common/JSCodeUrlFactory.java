package com.sungan.ad.service.st.common;

import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.dao.model.StmasterSite;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
public class JSCodeUrlFactory {
    private  JSCodeUrlFactory(){}
    public static String getJsURL(Stmaster st, StmasterSite site){
        return "http://www.baidu.com/";
    }
}
