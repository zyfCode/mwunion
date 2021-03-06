package com.sungan.ad.service.plat.settle;

import com.sungan.ad.dao.log.DayuvLog;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
@Component
public class LogWorker {
    private static final Logger logger = LoggerFactory.getLogger(LogWorker.class);
    private static  final int batchSize = 300000;
    @Autowired
    @Qualifier("logLoaderLocal")
    private LogLoader logLoader;

    @Autowired
    private UvLogCalculation uvLogCalculation;

    @Autowired
    private DayuvLogService dayuvLogService;
    public void Work(){
        List<String> fileNameList = logLoader.listFile();
        for(String fileName:fileNameList) {
            BufferedReader reader = logLoader.getReader(fileName);
            List<DayuvLog> datas = new ArrayList<DayuvLog>();
            String line = null;
            try {
                while((line=reader.readLine())!=null){
                    JSONObject jsonObject = JSONObject.fromObject(line);
                    DayuvLog target = (DayuvLog) JSONObject.toBean(jsonObject, DayuvLog.class);
                    datas.add(target);
                    if(datas.size()>= batchSize){
                        try {
                            this.handle(datas);
                        } finally {
                            datas = new ArrayList<DayuvLog>();
                        }
                    }
                }
                this.handle(datas);
            } catch (IOException e) {
                logger.error("解析文件{}异常",fileName,e);
            }finally{
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
                logLoader.finishe(fileName);
            }
        }
    }


    protected  void handle(List<DayuvLog> datas){
        Map<String,List<DayuvLog>> spbyStId= new HashMap<String,List<DayuvLog>>();
        for(DayuvLog log :datas){
            String stID = log.getStId();
            if(StringUtils.isBlank(stID)){
                continue;
            }
            List<DayuvLog> dayuvLogs = spbyStId.get(stID);
            if(dayuvLogs==null){
                dayuvLogs = new ArrayList<>();
                spbyStId.put(log.getStId(),dayuvLogs);
            }
            dayuvLogs.add(log);
        }
        if(logger.isInfoEnabled()){
            logger.info("处理{}日志信息",spbyStId.keySet());
        }
        Set<Map.Entry<String, List<DayuvLog>>> entries = spbyStId.entrySet();
        for(Map.Entry<String, List<DayuvLog>> kv:entries){
            String key = kv.getKey();//key=stmasterId
            Map<UVlogKey, UVCount> settleLog = this.getSettleLog(key);
            List<DayuvLog> value = kv.getValue();
            List<DayuvLog> enAble = new ArrayList<>();
            for(DayuvLog log:value) {
                //一个站长 一个IP 一个订单 1个UV只计费一次
                UVlogKey uVlogKeyUv = new UVlogKey(log.getUvIp(),log.getStId(), log.getAdOrderId(),log.getUvKey());
                if(settleLog.get(uVlogKeyUv)!=null){
                    continue;
                }
                //一个站长 一个IP 一个定单，最多计费5次
                UVlogKey uVlogKey = new UVlogKey(log.getUvIp(),log.getStId(), log.getAdOrderId());
                UVCount uvCount = settleLog.get(uVlogKey);
                if(uvCount!=null&&uvCount.getUv()>5){
                    continue;
                }else if(uvCount!=null){
                    uvCount.add();
                }else{
                    uvCount = new UVCount();
                    settleLog.put(uVlogKey,uvCount);
                }
                enAble.add(log);
                settleLog.put(uVlogKeyUv,new UVCount());
            }
            long begin = System.currentTimeMillis();
            dayuvLogService.insertBatch(enAble);    //需要改成异步
            long end = System.currentTimeMillis();
            if(logger.isInfoEnabled()){
                logger.info("站长{}指插入{}条数据完成,耗时{}",new Object[]{key,enAble,(end-begin)});
            }
            uvLogCalculation.settleLog(enAble,key);
        }
//        dayuvLogService.query()
    }

    private   Map<UVlogKey,UVCount> getSettleLog(String stId){  //分成计算站长 订单 IP 维度和计算站长 订单 IP 维度 UV维度
        Map<UVlogKey,UVCount> cache = new LinkedHashMap<>();
        long begin = System.currentTimeMillis();
        if(logger.isInfoEnabled()){
            logger.info("站长{}计费记录",stId);
        }

        List<DayuvLog> query = dayuvLogService.query(stId, null);
        if(logger.isInfoEnabled()){
            long end = System.currentTimeMillis();
            long t = end-begin;
            logger.info("站长{}查询出{}计费记录,耗时",new Object[]{stId,query.size(),t});
        }
        for(DayuvLog log:query){
            UVlogKey key1 = new UVlogKey(log.getUvIp(),log.getStId(), log.getAdOrderId());  //站长 订单 IP 维度
            UVCount uvCount1 = cache.get(key1);
            if(uvCount1!=null){
                uvCount1.add();
            }else{
                uvCount1 = new UVCount();
                cache.put(key1,uvCount1);
            }


            UVlogKey key = new UVlogKey(log.getUvIp(),log.getStId(), log.getAdOrderId(),log.getUvKey());//站长 订单 IP 维度 UV维度
            UVCount uvCount = cache.get(key);
            if(uvCount!=null){
                uvCount.add();
            }else{
                uvCount = new UVCount();
                cache.put(key,uvCount);
            }
        }
        return cache;
    }
   class UVCount{
       private int uv;
       void add(){
           uv++;
       }

       public int getUv() {
           return uv;
       }
   }

    class UVlogKey{
        private String ip;
        private String stId;
        private String orderId;
        private String uv;

        public UVlogKey(String ip, String stId, String orderId) {
            this.ip = ip;
            this.stId = stId;
            this.orderId = orderId;
        }

        public UVlogKey(String ip, String stId, String orderId, String uv) {
            this.ip = ip;
            this.stId = stId;
            this.orderId = orderId;
            this.uv = uv;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UVlogKey uVlogKey = (UVlogKey) o;

            if (ip != null ? !ip.equals(uVlogKey.ip) : uVlogKey.ip != null) return false;
            if (stId != null ? !stId.equals(uVlogKey.stId) : uVlogKey.stId != null) return false;
            if (orderId != null ? !orderId.equals(uVlogKey.orderId) : uVlogKey.orderId != null) return false;
            return uv != null ? uv.equals(uVlogKey.uv) : uVlogKey.uv == null;
        }

        @Override
        public int hashCode() {
            int result = ip != null ? ip.hashCode() : 0;
            result = 31 * result + (stId != null ? stId.hashCode() : 0);
            result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
            result = 31 * result + (uv != null ? uv.hashCode() : 0);
            return result;
        }

        public String getStId() {
            return stId;
        }

        public void setStId(String stId) {
            this.stId = stId;
        }

        public String getUv() {
            return uv;
        }

        public void setUv(String uv) {
            this.uv = uv;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }


        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
















