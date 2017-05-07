package com.sungan.ad.service.plat.settle;

import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.log.DayuvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public interface UvLogCalculation extends MuService{
    void settleLog(List<DayuvLog> logList,String adHostId);
}
