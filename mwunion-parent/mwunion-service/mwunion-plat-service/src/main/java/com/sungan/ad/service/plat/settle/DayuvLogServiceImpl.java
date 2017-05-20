package com.sungan.ad.service.plat.settle;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.DayuvLogDAO;
import com.sungan.ad.dao.log.DayuvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
@Service
public class DayuvLogServiceImpl implements DayuvLogService {
    @Autowired
    private DayuvLogDAO dayuvLogDAO;

    @Override
    public List<DayuvLog> query(String stId, String orderId) {
        DayuvLog condition = new DayuvLog();
        condition.setStId(stId);
        condition.setAdOrderId(orderId);
        List<DayuvLog> query = (List<DayuvLog>) dayuvLogDAO.query(condition);
        return  query;
    }

    @Override
    public int insertBatch(List<DayuvLog> datas) {
        List<DayuvLog> newList = new ArrayList<>();
        for(DayuvLog log:datas){
            log.setId(IdGeneratorFactory.nextId());
            log.setUpdateTime(new Date());
            log.setCreateTime(new Date());
            DayuvLog newLog = new DayuvLog();
            AdCommonsUtil.beanCopyWithoutNull(log,newLog);
            //clientAgent过大，不保存此字段
            newLog.setClientAgent(null);
            newList.add(newLog);
        }
        int insert = dayuvLogDAO.insert(newList);
        return insert;
    }

    @Override
    public void clearData(String adHostId) {

    }
}
