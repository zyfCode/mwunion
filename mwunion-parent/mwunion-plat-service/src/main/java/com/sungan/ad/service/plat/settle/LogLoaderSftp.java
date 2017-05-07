package com.sungan.ad.service.plat.settle;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public class LogLoaderSftp implements LogLoader {
    @Override
    public List<String> listFile() {
        return null;
    }

    @Override
    public BufferedReader getReader(String file) {
        return null;
    }

    @Override
    public void finishe(String file) {

    }
}
