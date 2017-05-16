package test;

import com.sungan.ad.cmmon.test.BaseTest;
import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.dao.bean.AdHostAccountPayOrderQueryBean;
import com.sungan.ad.service.adhost.AdHostAccountPayOrderService;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderQueryBeanVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhangyf18255 on 2017/4/30.
 */
public class TestAdHost extends BaseTest {
    @Autowired
    private AdHostAccountPayOrderService adHostAccountPayOrderService;

    @Test
    public void testQuery(){
        AdHostAccountPayOrderQueryBean condition = new AdHostAccountPayOrderQueryBean();
        condition.setAdhostName("dsada");
        AdPager<AdHostAccountPayOrderQueryBeanVo> adHostAccountPayOrderQueryBeanVoAdPager = adHostAccountPayOrderService.queryPager(condition, 1, 10);
        System.out.println(adHostAccountPayOrderQueryBeanVoAdPager);
    }


}
