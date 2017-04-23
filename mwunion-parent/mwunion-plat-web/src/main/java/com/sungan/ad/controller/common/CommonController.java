package com.sungan.ad.controller.common;

import com.hundsun.jresplus.ui.upload.util.JsonUtil;
import com.sungan.ad.commons.annexes.AnexesInfo;
import com.sungan.ad.commons.annexes.AnnexesUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangyf18255 on 2017/4/19.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("/upload")
    @ResponseBody
    public Object doUpload(String prefix, MultipartHttpServletRequest request){
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        Set<Map.Entry<String, List<MultipartFile>>> entries = multiFileMap.entrySet();
        JSONObject jsonObj = new JSONObject();
        try {
            String oldName = null;
            AnexesInfo upload = null;
            for (Map.Entry<String, List<MultipartFile>> entry:entries) {
                List<MultipartFile> value = entry.getValue();
                if(value==null||value.isEmpty()){
                    continue;
                }
                MultipartFile fileItem = value.get(0);
                oldName = fileItem.getOriginalFilename();
                InputStream inputStream = fileItem.getInputStream();
                upload = null;
                if(StringUtils.isNotBlank(prefix)){
                    upload = AnnexesUtil.upload(prefix,oldName, inputStream);
                }else {
                    upload = AnnexesUtil.upload(oldName, inputStream);
                }
            }

            jsonObj = JsonUtil.responseJson("10000", "上传成功", true);
            JSONObject jsonSucc = new JSONObject();
            jsonSucc.put("fileName", upload.getName());
            jsonSucc.put("oldFileName", oldName);
            jsonSucc.put("filePath", upload.getUrl());
            jsonObj.put("success", jsonSucc);
        } catch (Exception e) {
            if(logger.isWarnEnabled()){
                logger.warn("",e);
            }
            jsonObj = JsonUtil.responseJson("10002", "删除失败，文件不存在", false);
        }
        return jsonObj;
    }
}
