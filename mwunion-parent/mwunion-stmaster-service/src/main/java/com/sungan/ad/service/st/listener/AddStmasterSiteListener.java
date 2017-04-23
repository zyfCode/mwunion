package com.sungan.ad.service.st.listener;

import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.commons.service.event.EnumEventType;
import com.sungan.ad.commons.service.event.EvenContext;
import com.sungan.ad.commons.service.event.EventListener;
import com.sungan.ad.commons.service.resources.ResourcesUtil;
import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.dao.model.StmasterPlatAccount;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.dao.model.StmasterSiteCode;
import com.sungan.ad.dao.model.adenum.EnumStmasterSiteCodeStatus;
import com.sungan.ad.service.st.StmasterPlatAccountService;
import com.sungan.ad.service.st.StmasterService;
import com.sungan.ad.service.st.StmasterSiteCodeService;
import com.sungan.ad.service.st.common.JSCodeUrlFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
@Component
public class AddStmasterSiteListener extends EventListener implements MuService {
    @Autowired
    private StmasterSiteCodeService stmasterSiteCodeService;
    @Autowired
    private StmasterService stmasterService;
    @Override
    public EnumEventType getListenType() {
        return EnumEventType.ADD_STMARSTER_SITE;
    }

    @Override
    public void handler(EvenContext context) {
        StmasterSite site = (StmasterSite) context.getTarget();
        Stmaster st = stmasterService.find(site.getStId());
        Map<String, String> jsCode = ResourcesUtil.getJsCode();
        Set<Map.Entry<String, String>> entries = jsCode.entrySet();
        for(Map.Entry<String, String> entry:entries){
            String key = entry.getKey();
            String value = entry.getValue();
            String id = IdGeneratorFactory.nextId();
            StmasterSiteCode code = new StmasterSiteCode();
            code.setCodeStatus(EnumStmasterSiteCodeStatus.INUSED.getKey());
            code.setCreateTime(new Date());
            code.setSiteCodeId(id);
            code.setSiteCodeContent(value);
            code.setSiteCodeName(key);
            String url = JSCodeUrlFactory.getJsURL(st,site);
            code.setSiteCodeUrl(url);
            code.setSiteId(site.getSiteId());
            code.setStId(site.getStId());
            stmasterSiteCodeService.insert(code);
        }
    }
}
