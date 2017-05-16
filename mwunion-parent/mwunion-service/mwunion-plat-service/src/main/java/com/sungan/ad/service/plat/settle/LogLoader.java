package com.sungan.ad.service.plat.settle;

import java.io.BufferedReader;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public interface LogLoader {
    public static final String OK = ".ok";
    public static final String DONE = ".done";

    List<String> listFile();
    BufferedReader getReader(String fileNme);
    void finishe(String fileNme);
}
