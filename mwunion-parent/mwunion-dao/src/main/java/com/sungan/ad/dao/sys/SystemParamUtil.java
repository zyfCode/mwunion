package com.sungan.ad.dao.sys;

import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.PlatAccountDAO;
import com.sungan.ad.dao.model.PlatAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
@Component
public class SystemParamUtil implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(SystemParamUtil.class);
    private static SystemParamUtil util;
    @Autowired
    private PlatAccountDAO platAccountDAO;

    public static BigDecimal getStmasterHitPrice(){ //TODO 需要改成从系统属性获取
       return new BigDecimal(0.1);
    }

    public static BigDecimal getStmasterShowPrice(){ //TODO 需要改成从系统属性获取
       return new BigDecimal(0.1);
    }

    public static PlatAccountWrapper getPlatAccount(){
        PlatAccount account = null;
        List<PlatAccount> query = (List<PlatAccount>) util.platAccountDAO.query();
        if(query==null||query.size()<1){
            account = new PlatAccount();
            account.setNowAmount(BigDecimal.ZERO);
            account.setCreateTime(new Date());
            account.setPayAmount(BigDecimal.ZERO);
            account.setToalAmount(BigDecimal.ZERO);
            account.setUpdateTime(new Date());
            account.setVersion(1);
            account.setId(IdGeneratorFactory.nextId());
            util.platAccountDAO.insert(account);
        }else {
            if (query.size() > 1){
                logger.warn("查询到多个平台账号!");
            }
            account = query.get(0);
        }
        PlatAccountWrapper wrapper = new PlatAccountWrapper(account,util.platAccountDAO);
        return wrapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        util = this;
    }

}
