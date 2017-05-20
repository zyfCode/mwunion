package com.sungan.ad.service.plat.settle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 文件名称必须是UUID_yyyyMMddHHmmss
 * Created by zhangyf18255 on 2017/5/4.
 */
@Component("logLoaderLocal")
public class LogLoaderLocal implements   LogLoader{
    private static final Logger logger = LoggerFactory.getLogger(LogLoaderLocal.class);

    @Value("${log.file.path}")
    private String logFile;


    @Override
    public List<String> listFile() {
        File file = new File(logFile);
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                File file = new File(dir,name);
                if (file.isDirectory()) {
                    return false;
                }
                File okFile = new File(dir,name+".ok");
                if (!okFile.exists()) {  //没有.ok文件认为是没上传完的文件
                    return false;
                }
                if (name.endsWith(".ok")) { //.ok 文件忽略
                    return false;
                }
                if (name.endsWith(".done")) { //.done 文件是已经处理完的文件
                    return false;
                }
                return true;
            }
        });
        if(list==null){
            return null;
        }
        return Arrays.asList(list);
    }

    @Override
    public BufferedReader getReader(String fileName) {
        File file = new File(logFile,fileName);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            return reader;
        } catch (Exception e) {
           logger.error("文件{}处理异常",fileName,e);
        }
        return null;
    }

    @Override
    public void finishe(String fileName) {
        File file = new File(logFile,fileName);
        File newFile = new File(logFile,fileName+DONE);
        try {
            if(file.exists()){
                boolean success = file.renameTo(newFile);
                if(!success){
                    logger.warn("文件[{}]重全名[{}]失败!",file,newFile);
                }
            }
        } catch (Exception e) {
            logger.error("文件{}重命名-->{}异常",file,newFile,e);
        }
    }
}
