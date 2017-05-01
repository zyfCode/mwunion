package com.sungan.ad.service.adhost.listener;

import com.sungan.ad.commons.IdGenerator;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.commons.service.event.EnumEventType;
import com.sungan.ad.commons.service.event.EvenContext;
import com.sungan.ad.commons.service.event.EventListener;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.service.adhost.AdHostAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/4/23.
 */
@Component
public class AddAdHostEventListener extends EventListener {
    @Autowired
    private AdHostAccountService service;

    @Override
    public EnumEventType getListenType() {
        return EnumEventType.ADD_ADHOST;
    }

    @Override
    public void handler(EvenContext context) {
        AdHost host = (AdHost) context.getTarget();
        AdHostAccount account = new AdHostAccount();
        account.setAccountAmount(BigDecimal.ZERO);
        String id = IdGeneratorFactory.nextId();
        account.setAccountId(id);
        account.setAdHostId(host.getAdhostId());
        account.setCreateTime(new Date());
        account.setFrozenAmount(BigDecimal.ZERO);
        account.setUpdateTime(new Date());
        account.setUseAmount(BigDecimal.ZERO);
        account.setVersion(1);
        service.insert(account);
    }
}
