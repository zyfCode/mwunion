package com.sungan.ad.dao.impl;

import com.sungan.ad.common.dao.AdCommonQuery;
import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.dao.AdHostAccountPayOrderQueryDAO;
import com.sungan.ad.dao.bean.AdHostAccountPayOrderQueryBean;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.dao.model.AdHostAccountPayOrder;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyf18255 on 2017/4/30.
 */
@Component
public class AdHostAccountPayOrderQueryDAOImpl extends AdCommonQuery<AdHostAccountPayOrderQueryBean> implements AdHostAccountPayOrderQueryDAO {


    public AdPager<AdHostAccountPayOrderQueryBean> queryPage(AdHostAccountPayOrderQueryBean condition,int pageIndex,int rows){
        final String alia1 = this.getAlia(AdHostAccountPayOrder.class);
        String alia2 = this.getAlia(AdHost.class);

        String hqlSuffix = new StringBuffer().append(" from ")
                .append(AdHostAccountPayOrder.class.getName())
                .append(" as ").append(alia1).append(",")
                .append(AdHost.class.getName()).append(" as ").append(alia2)
                .append(" where ").append( alia1+".adHostId="+alia2+".adhostId " )
                .toString();
        HQLHandler handler = new HQLHandler() {
            @Override
            public String handleHql(String orgHql) {
                orgHql = orgHql+" order by "+alia1+".createTime desc";
                return orgHql;
            }
        };
        AdPager<AdHostAccountPayOrderQueryBean> pager = super.queryPager(AdHostAccountPayOrderQueryBean.class, condition, pageIndex, rows, hqlSuffix,handler);
        return pager;
    }

}
