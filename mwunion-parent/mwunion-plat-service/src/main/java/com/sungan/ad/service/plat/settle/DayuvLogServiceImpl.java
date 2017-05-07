package com.sungan.ad.service.plat.settle;

import com.sungan.ad.dao.DayuvLogDAO;
import com.sungan.ad.dao.log.DayuvLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public class DayuvLogServiceImpl implements DayuvLogService {
    @Autowired
    private DayuvLogDAO dayuvLogDAO;

    @Override
    public List<DayuvLog> query(String adHostId, String orderId) {
        DayuvLog condition = new DayuvLog();
        condition.setAdHostId(adHostId);
        condition.setAdOrderId(orderId);
        List<DayuvLog> query = (List<DayuvLog>) dayuvLogDAO.query(condition);
        return  query;
    }

    @Override
    public int insertBatch(List<DayuvLog> datas) {
        int insert = dayuvLogDAO.insert(datas);
        return insert;
    }

    @Override
    public void clearData(String adHostId) {

    }
}
