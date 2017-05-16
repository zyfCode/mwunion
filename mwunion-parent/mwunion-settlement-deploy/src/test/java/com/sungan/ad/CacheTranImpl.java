package com.sungan.ad;

import com.sungan.ad.common.dao.UpdateByCondition;
import com.sungan.ad.dao.PlatAccountDAO;
import com.sungan.ad.dao.model.PlatAccount;
import com.sungan.ad.dao.sys.PlatAccountWrapper;
import com.sungan.ad.dao.sys.SystemParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/14.
 */
@Service
public class CacheTranImpl implements CacheTran {
    @Autowired
    public PlatAccountDAO platAccountDAO;
    @Autowired
    @Qualifier("hibernateTemprate")
    protected HibernateTemplate template;


    @Override
    public void testTan() {
        List<PlatAccount> query = (List<PlatAccount>) platAccountDAO.query();
        PlatAccount account = query.get(0);
        System.out.println(account.getVersion());
        account.setNowAmount(BigDecimal.ONE);
        UpdateByCondition<PlatAccount> condition = new UpdateByCondition<PlatAccount>(account,true);
        platAccountDAO.updateHql(condition);
//        PlatAccount account1 = platAccountDAO.find(account.getId());
//        template.getSessionFactory().getCurrentSession().clear();
        template.getSessionFactory().getCurrentSession().evict(account);
        PlatAccount account1 =  ((List<PlatAccount>) platAccountDAO.query()).get(0);
        System.out.println(account1.getVersion());
//        PlatAccountWrapper platAccountWrapper = SystemParamUtil.getPlatAccount();
//        PlatAccount platAccount = platAccountWrapper.;getPlatAccount();
//        platAccount.setNowAmount(BigDecimal.ONE);
//        int i = platAccountWrapper.updatePlatAccount();
//        System.out.print(i);
//         platAccountWrapper = SystemParamUtil.getPlatAccount();
//         platAccount = platAccountWrapper.getPlatAccount();
//        platAccount.setNowAmount(BigDecimal.ONE);
//         i = platAccountWrapper.updatePlatAccount();
//        System.out.print(i);
//        if(i<1){
//            throw new RuntimeException("");
//        }
    }

}
