package com.sungan.ad.dao;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.dao.bean.AdHostAccountPayOrderQueryBean;

/**
 * Created by zhangyf18255 on 2017/4/30.
 */
public interface AdHostAccountPayOrderQueryDAO {
    AdPager<AdHostAccountPayOrderQueryBean> queryPage(AdHostAccountPayOrderQueryBean condition, int pageIndex, int rows);
}
