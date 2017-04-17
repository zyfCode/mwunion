package com.sungan.ad.commons.annexes;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhangyf18255 on 2017/4/16.
 */
public class AnnexesUtil {
    private static  AnnexesHandler handler;

    /**
     * 前缀名称
     * @param prefixName
     * @param in
     * @return
     */
    public static AnexesInfo upload(String prefixName,String oldName,InputStream in){
        AnexesInfo upload = handler.upload(prefixName,oldName, in);
        return upload;
    }
    public static AnexesInfo upload(,String oldName,InputStream in){
        AnexesInfo upload = handler.upload(oldName,in);
        return upload;
    }
    public static  String getUrl(String name){
        String url = handler.getUrl(name);
        return url;
    }
    public static OutputStream download(String name){
        OutputStream download = handler.download(name);
        return download;
    }

    public static void deleteFile(String name){
        handler.deleteFile(name);
    }

    public static void setHandler(AnnexesHandler handler) {
        AnnexesUtil.handler = handler;
    }
}
