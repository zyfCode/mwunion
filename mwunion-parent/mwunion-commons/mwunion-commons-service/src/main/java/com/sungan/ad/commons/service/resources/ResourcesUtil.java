package com.sungan.ad.commons.service.resources;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
public class ResourcesUtil {
    private static final  Logger logger = LoggerFactory.getLogger(ResourcesUtil.class);
    private ResourcesUtil(){}

    public static String encodeJs(String content){

        byte[] bytes = new byte[]{};
        try {
            bytes = content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("",e);
        }
        byte[] resultByte = Base64.encodeBase64(bytes);
        try {
            String str = new String(resultByte, "UTF-8");
            return str;
        } catch (UnsupportedEncodingException e) {
            logger.error("",e);
        }
        return null;
    }
    public static String decodeJs(String content){
        try {
            byte[] bytes = Base64.decodeBase64(content.getBytes("UTF-8"));
            String result = new String(bytes,"UTF-8");
            return result;
        } catch (IOException e) {
          logger.error("",e);
        }
        return null;
    }

    public static Map<String,String> getJsCode(){
        String[] codeNames = new String[]{"贴片-中部.js","贴片-顶.js","贴片-底.js"};
        Map<String,String> result = new LinkedHashMap<>();
        for(String name:codeNames) {
            try {
                BufferedReader reader = null;
                try {
                    String path="code/" + name;
                    InputStream in = ResourcesUtil.class.getClassLoader().getResourceAsStream(path);
                    reader  = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    StringBuffer buf = new StringBuffer();
                    String line = null;
                    while((line=reader.readLine())!=null){
                        buf.append(line).append("\r\n");
                    }
                    String s = ResourcesUtil.encodeJs(buf.toString());
                    result.put(name,s);
                } finally {
                    if(reader!=null){
                        reader.close();
                    }
                }
            } catch (Exception e) {
                logger.error("",e);
            }
        }
        return result;
    }

}
