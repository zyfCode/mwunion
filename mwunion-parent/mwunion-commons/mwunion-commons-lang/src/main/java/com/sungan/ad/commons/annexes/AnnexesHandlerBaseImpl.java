package com.sungan.ad.commons.annexes;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

/**
 * Created by zhangyf18255 on 2017/4/16.
 */
@Component
public class AnnexesHandlerBaseImpl extends AnnexesAbastractHandler {

    @Value("${ad.annexe.urlprefix}")
    private String urlPreFix;

    @Value("${ad.annexe.path}")
    private String annaxPath;

    public String getAnnaxPath() {
        return annaxPath;
    }

    public String getUrlPreFix() {
        return urlPreFix;
    }

    public void setUrlPreFix(String urlPreFix) {
        this.urlPreFix = urlPreFix;
    }

    @Override
    public AnexesInfo upload(String prefixName,String oldName, InputStream in) {
        String uuids = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = null;
        String type = "";
        if(oldName.contains(".")){
            String str = oldName.split("\\.")[1];
            type = "."+str;
        }
        if(StringUtils.isNotBlank(prefixName)){
            fileName = prefixName+"_"+uuids+type;
        }else{
            fileName = uuids+type;
        }
        File file = new File(annaxPath,fileName);
        try {
            FileOutputStream out = new FileOutputStream(file);
            BufferedOutputStream bufOut = new BufferedOutputStream(out);
            try {
                byte [] bufarr = new byte[2*1024];
                int length = -1;
                while((length=in.read(bufarr))!=-1){
                    bufOut.write(bufarr,0,length);
                }
                bufOut.flush();
            } finally {
                bufOut.close();
            }
            AnexesInfo upload = new AnexesInfo();
            upload.setAnexNo(uuids);
            upload.setName(fileName);
            upload.setUrl(urlPreFix+""+fileName);
            return upload;
        } catch (Exception e) {
            throw new RuntimeException("",e);
        }
    }

    @Override
    public AnexesInfo upload(String oldName,InputStream in) {
        AnexesInfo upload = this.upload("", oldName, in);
        return upload;
    }

    @Override
    public String getUrl(String name) {
        if(urlPreFix!=null&&!urlPreFix.endsWith("/")){
            urlPreFix = urlPreFix+"/";
        }
//        Base32 base32 = new Base32();
//        base32.encode()
        return urlPreFix+""+name;
    }

    @Override
    public InputStream download(String name) {
        File file = new File(annaxPath,name);
        try {
            InputStream fileInputStream = new FileInputStream(file);
            return fileInputStream;
        } catch (FileNotFoundException e) {
            throw  new RuntimeException("",e);
        }
    }

    @Override
    public void deleteFile(String name) {
        File file = new File(annaxPath,name);
        if(file.exists()){
            file.delete();
        }
    }
}
