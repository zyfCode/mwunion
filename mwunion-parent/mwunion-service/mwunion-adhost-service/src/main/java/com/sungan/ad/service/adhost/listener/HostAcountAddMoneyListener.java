package com.sungan.ad.service.adhost.listener;

import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.commons.service.event.EnumEventType;
import com.sungan.ad.commons.service.event.EvenContext;
import com.sungan.ad.commons.service.event.EventListener;
import com.sungan.ad.dao.PlatMesgDAO;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.dao.model.PlatMesg;
import com.sungan.ad.dao.model.adenum.EnumUserMsgStatus;
import com.sungan.ad.service.adhost.bizbean.AdHostBizBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/4/23.
 */
@Component
public class HostAcountAddMoneyListener extends EventListener {
    @Autowired
    private PlatMesgDAO platMesgDAO;
    @Override
    public EnumEventType getListenType() {
        return EnumEventType.ADD_ADHOST_ACCOUNT_ADDMONEY;
    }

    private static final  long availableTime = 7*60*60*1000;

    @Override
    public void handler(EvenContext context) {
        //添加系统公告
        AdHost target = (AdHost) context.getTarget();
        Object money = context.get(AdHostBizBean.HOSTACCOUNT_ADDMONEYKEY);

        PlatMesg mesg = new PlatMesg();
        mesg.setCreateMan("SYSTEM");
        mesg.setCreateTime(new Date());
        mesg.setInvalidTime(new Date(System.currentTimeMillis()+ availableTime));
        String content = new StringBuffer().append("[广告值]用户[").append(target.getAdhostName()).append("]充值￥").append(money).append("元").toString();
        mesg.setMsgContent(content);
        mesg.setMsgId(IdGeneratorFactory.nextId());
        mesg.setMsgStatus(EnumUserMsgStatus.UNSHOW.getKey());
        mesg.setShowTime(new Date());
        mesg.setUpdateTime(new Date());
        platMesgDAO.insert(mesg);
    }
}
