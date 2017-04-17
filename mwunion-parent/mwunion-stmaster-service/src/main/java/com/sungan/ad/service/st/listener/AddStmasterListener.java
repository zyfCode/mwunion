package com.sungan.ad.service.st.listener;

import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.commons.service.event.EnumEventType;
import com.sungan.ad.commons.service.event.EventListener;
import com.sungan.ad.dao.StmasterAccountDAO;
import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.dao.model.StmasterAccount;
import com.sungan.ad.dao.model.StmasterPlatAccount;
import com.sungan.ad.service.st.StmasterAccountService;
import com.sungan.ad.service.st.StmasterPlatAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
@Component
public class AddStmasterListener extends EventListener implements MuService {
    @Autowired
    private StmasterPlatAccountService stmasterPlatAccountService;
    @Override
    public EnumEventType getListenType() {
        return EnumEventType.ADD_STMARSTER;
    }

    @Override
    public void handler(Object context) {
        Stmaster stmaster = (Stmaster) context;
        StmasterPlatAccount acouunt = new StmasterPlatAccount();
        acouunt.setAccountId(IdGeneratorFactory.nextId());
        Date date = new Date();
        acouunt.setStmasterId(stmaster.getStmasterId());
        acouunt.setUserId(stmaster.getUserId());
        acouunt.setAccountAmount(BigDecimal.ZERO);
        acouunt.setCurrentDayAmount(BigDecimal.ZERO);
        acouunt.setClearAmount(BigDecimal.ZERO);
        acouunt.setCreateTime(date);
        acouunt.setUserId(stmaster.getUserId());
        acouunt.setUpdateTime(date);
        acouunt.setVersion(0);
        stmasterPlatAccountService.insert(acouunt);
    }
}
