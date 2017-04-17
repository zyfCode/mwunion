package com.sungan.ad.commons.annexes;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhangyf18255 on 2017/4/16.
 */
public interface AnnexesHandler {
    /**
     * 上传文件
     * @param prefixName
     * @param in
     * @return
     */
    AnexesInfo upload(String prefixName,String oldName,InputStream in);

    /**
     * 上传文件
     * @param in
     * @return
     */
    AnexesInfo upload(String oldName,InputStream in);

    /**
     * 获取文件URL
     * @param name
     * @return
     */
    String getUrl(String name);

    /**
     * 下载
     * @param name
     */
    OutputStream download(String name);

    /**
     * 删除文件
     * @param name
     */
    void deleteFile(String name);
}
