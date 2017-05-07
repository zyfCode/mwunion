package com.sungan.ad.service.plat.settle;

import com.sungan.ad.commons.LogService;
import com.sungan.ad.dao.log.DayuvLog;

import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public interface DayuvLogService extends LogService {

    /**
     * 查询已经计费记录
     * @param adHostId
     * @param orderId
     * @return
     */
    List<DayuvLog> query(String adHostId,String orderId);


    /**
     * 批量插入
     * @param datas
     * @return
     */
    int insertBatch(List<DayuvLog> datas);

    /**
     * 清除数据
     * @param adHostId
     */
    void clearData(String adHostId);
}
