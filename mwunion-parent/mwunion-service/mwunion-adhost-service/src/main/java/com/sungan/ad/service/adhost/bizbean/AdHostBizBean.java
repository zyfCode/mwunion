package com.sungan.ad.service.adhost.bizbean;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.commons.service.event.EnumEventType;
import com.sungan.ad.commons.service.event.EvenContext;
import com.sungan.ad.commons.service.event.EventQueen;
import com.sungan.ad.dao.AdHostAccountDAO;
import com.sungan.ad.dao.AdHostAccountSerialDAO;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.dao.model.AdHostAccountSerial;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountSerialType;
import com.sungan.ad.exception.AdRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/4/23.
 */
public class AdHostBizBean extends AdHost{
    private AdHostAccountDAO adHostAccountDAO;
    private AdHostAccountSerialDAO adHostAccountSerialDAO;
    private static final Logger logger = LoggerFactory.getLogger(AdHostBizBean.class);

    public  AdHostBizBean(AdHost host){
        AdCommonsUtil.beanCopyWithoutNull(host,this);
    }

    private AdHostAccount adHostAccount;

    public AdHostAccount getAccount(){
        if(adHostAccount!=null){
            return  adHostAccount;
        }
        AdHostAccount condition = new AdHostAccount();
        String adhostId = this.getAdhostId();
        if(StringUtils.isBlank(adhostId)){
            if(logger.isErrorEnabled()){
                logger.error("广告主[{}]主键为空",this.getAdhostName());
            }
            throw new AdRuntimeException("数据异常,请联系管理员");
        }
        condition.setAdHostId(adhostId);
        List<AdHostAccount> query = (List<AdHostAccount>) adHostAccountDAO.query(condition);
        if(query==null||query.isEmpty()){
            if(logger.isWarnEnabled()){
                logger.warn("获取不到[{}]广告主[{}]的账户信息",this.getAdhostId(),this.getAdhostName());
            }
            return null;
        }
        AdHostAccount adHostAccount = query.get(0);
        return adHostAccount;
    }

    public static final String HOSTACCOUNT_ADDMONEYKEY="HOSTACCOUNT_ADDMONEYKEY";

    public void addMoney(BigDecimal addMoney){
        AdHostAccount account = this.getAccount();
        if(account==null){
            throw new AdRuntimeException("无账号信息");
        }
        BigDecimal accountAmount = account.getAccountAmount();
        accountAmount = accountAmount.add(addMoney);
        account.setAccountAmount(accountAmount);
        this.adHostAccountDAO.update(account);
        if(logger.isInfoEnabled()){
            logger.info("[充值业务]用户[{}]允值[{}]成功",this.getAdhostName(),addMoney);
        }
        //添加充值记录
        AdHostAccountSerial serial = new AdHostAccountSerial();
        serial.setAccountAmount(addMoney);
        serial.setAccountId(account.getAccountId());
        serial.setAdHostId(account.getAdHostId());
        serial.setCreateTime(new Date());
        serial.setSerialId(IdGeneratorFactory.nextId());
        serial.setSerilType(EnumAdHostAccountSerialType.PAY.getKey());
        serial.setUpdateTime(new Date());
        this.adHostAccountSerialDAO.insert(serial);
        //充值成功后，添加管理台展示信息
        EvenContext context = new EvenContext();
        context.setTarget(this);
        context.put(HOSTACCOUNT_ADDMONEYKEY,addMoney);
        EventQueen.addEvent(EnumEventType.ADD_ADHOST_ACCOUNT_ADDMONEY,context);
    }

    public void setAdHostAccountDAO(AdHostAccountDAO adHostAccountDAO) {
        this.adHostAccountDAO = adHostAccountDAO;
    }

    public void setAdHostAccountSerialDAO(AdHostAccountSerialDAO adHostAccountSerialDAO) {
        this.adHostAccountSerialDAO = adHostAccountSerialDAO;
    }
}
