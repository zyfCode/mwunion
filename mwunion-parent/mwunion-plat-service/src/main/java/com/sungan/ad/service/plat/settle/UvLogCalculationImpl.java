package com.sungan.ad.service.plat.settle;

import com.sungan.ad.common.dao.UpdateByCondition;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.*;
import com.sungan.ad.dao.log.DayuvLog;
import com.sungan.ad.dao.model.*;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderPayType;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderStatus;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderType;
import com.sungan.ad.dao.model.adenum.EnumStmasterSiteHourStatus;
import com.sungan.ad.dao.sys.PlatAccountWrapper;
import com.sungan.ad.dao.sys.SystemParamUtil;
import com.sungan.ad.exception.AdRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public class UvLogCalculationImpl implements UvLogCalculation {
    private static final Logger logger = LoggerFactory.getLogger(UvLogCalculationImpl.class);
    @Autowired
    private DayuvLogService dayuvLogService;
    @Autowired
    private AdHostAccountAdOrderDAO adHostAccountAdOrderDAO;

    @Autowired
    private AdHostAccountAdOrderAttriDAO attriDAO;

    @Autowired
    private AdHostAccountDAO adHostAccountDAO;

    @Autowired
    private TransactionHelper transactionHelper;

    @Autowired
    private StmasterSiteHourDAO siteHourDAO;

    @Autowired
    private AdHostDAO adHostDAO;

    @Override
    public void settleLog(List<DayuvLog> logList,String adHostId) {
        if(logList==null||logList.size()<1){
            return;
        }
        AdHostAccountAdOrder adHostAccountAdOrder = null;
        Map<String,List<DayuvLog>> orderMap = new HashMap<>();
        for(DayuvLog log:logList){
            if(adHostAccountAdOrder==null){
                adHostAccountAdOrder = adHostAccountAdOrderDAO.find(log.getAdOrderId());
            }
            List<DayuvLog> dayuvLogs = orderMap.get(log.getAdOrderId());
            if(dayuvLogs==null){
                dayuvLogs = new ArrayList<>();
                orderMap.put(log.getAdOrderId(),dayuvLogs);
            }
            dayuvLogs.add(log);
        }
        Set<Map.Entry<String, List<DayuvLog>>> entries = orderMap.entrySet();
        int ignores = 0;
        for(Map.Entry<String,List<DayuvLog>> kv:entries){
            List<DayuvLog> value = kv.getValue();
            String key = kv.getKey();
            ignores =ignores+this.adHostCount(key, value, EnumAdHostAccountAdOrderType.match(adHostAccountAdOrder.getAdOrderType()));
        }

        this.stMasterCount(adHostId,ignores,logList,EnumAdHostAccountAdOrderType.match(adHostAccountAdOrder.getAdOrderType()));
    }

    /**
     * 广告主计费
     * @param logs
     */
    private int adHostCount(final String orderId,List<DayuvLog> logs,EnumAdHostAccountAdOrderType type){
        int ignores = 0;
        if(logs==null||logs.size()<1){
            return ignores;
        }
        AdHostAccountAdOrderAttri condition =  new AdHostAccountAdOrderAttri();
        condition.setAdOrderId(orderId);
        List<AdHostAccountAdOrderAttri> query = (List<AdHostAccountAdOrderAttri>) attriDAO.query(condition);
        if(query==null||query.size()<1){
            logger.error("{}订单属性不存在,业务终止",orderId);
             throw new AdRuntimeException("数据异常");
        }
        AdHostAccountAdOrderAttri orderAttri = query.get(0);
        final AdHostAccountAdOrder adHostAccountAdOrder = adHostAccountAdOrderDAO.find(orderId);
        final AdHostAccount accountCondition = new AdHostAccount();
        accountCondition.setAdHostId(adHostAccountAdOrder.getAdHostId());
        List<AdHostAccount> hostAccountList = (List<AdHostAccount>) adHostAccountDAO.query(accountCondition);
        AdHostAccount adHostAccount = hostAccountList.get(0);

        String payType = adHostAccountAdOrder.getPayType();
        BigDecimal newAamount = BigDecimal.ZERO;
        if(type==EnumAdHostAccountAdOrderType.AD_HIT){
            BigDecimal hitPrice = orderAttri.getHitPrice();
            newAamount = hitPrice.multiply(new BigDecimal(logs.size()));
        }else if((type==EnumAdHostAccountAdOrderType.AD_SHOW)){
            BigDecimal showPrice = orderAttri.getShowPrice();
            newAamount =  showPrice.multiply(new BigDecimal(logs.size())).divide(new BigDecimal(1000));
        }
        //更新站长账号
        if(payType.equals(EnumAdHostAccountAdOrderPayType.SETTLE_PREPAID.getKey())){  //预付费时，账号冻结金额减少，账号使用金额增加，平账号增加金额增加
            BigDecimal frozenAmount = adHostAccount.getFrozenAmount();
            if(frozenAmount.compareTo(newAamount)<0){
                newAamount =frozenAmount;
                ignores = logs.size();  //对站长不计费
            }
            frozenAmount = frozenAmount.subtract(newAamount);
            if(frozenAmount.compareTo(BigDecimal.ZERO)==0){
                adHostAccountAdOrder.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.DONED.getKey());
            }
            adHostAccount.setFrozenAmount(frozenAmount);
            adHostAccount.setUseAmount(adHostAccount.getUseAmount().add(newAamount));
            UpdateByCondition<AdHostAccount> undateCon = new UpdateByCondition<AdHostAccount>(adHostAccount,true);
            int i = adHostAccountDAO.updateHql(undateCon);
            for (int j=0;j<3&&i<1;j++) {
                HelperBiz<AdHostAccount> helper = new HelperBiz<AdHostAccount>() {
                    @Override
                    public AdHostAccount doNewTransaction() {
                        List<AdHostAccount> hostAccountList = (List<AdHostAccount>) adHostAccountDAO.query(accountCondition);
                        AdHostAccount adHostAccount = hostAccountList.get(0);
                        return adHostAccount;
                    }
                };
                AdHostAccount newAdHostAccount = transactionHelper.doInNewTran(helper);
                frozenAmount = newAdHostAccount.getFrozenAmount().subtract(newAamount);
                adHostAccount.setUseAmount(newAdHostAccount.getUseAmount().add(newAamount));
                if(frozenAmount.compareTo(BigDecimal.ZERO)<=0){
                    adHostAccountAdOrder.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.DONED.getKey());
                }
                i = adHostAccountDAO.updateHql(undateCon);
            }
        }else if(payType.equals(EnumAdHostAccountAdOrderPayType.SETTLE_REALTIME.getKey())){
            BigDecimal accountAmount = adHostAccount.getAccountAmount();
            if(accountAmount.compareTo(newAamount)<0){
                newAamount =accountAmount;
                ignores = logs.size();  //对站长不计费
            }
            accountAmount = accountAmount.subtract(newAamount);
            if(accountAmount.compareTo(BigDecimal.ZERO)==0){
                adHostAccountAdOrder.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.STOP.getKey());
            }
            adHostAccount.setAccountAmount(accountAmount);
            adHostAccount.setUseAmount(adHostAccount.getUseAmount().add(newAamount));
            UpdateByCondition<AdHostAccount> undateCon = new UpdateByCondition<AdHostAccount>(adHostAccount,true);
            int i = adHostAccountDAO.updateHql(undateCon);
            for (int j=0;j<3&&i<1;j++) {
                HelperBiz<AdHostAccount> helper = new HelperBiz<AdHostAccount>() {
                    @Override
                    public AdHostAccount doNewTransaction() {
                        List<AdHostAccount> hostAccountList = (List<AdHostAccount>) adHostAccountDAO.query(accountCondition);
                        AdHostAccount adHostAccount = hostAccountList.get(0);
                        return adHostAccount;
                    }
                };
                AdHostAccount newAdHostAccount = transactionHelper.doInNewTran(helper);
                accountAmount = newAdHostAccount.getAccountAmount().subtract(newAamount);
                adHostAccount.setUseAmount(newAdHostAccount.getUseAmount().add(newAamount));
                if(accountAmount.compareTo(BigDecimal.ZERO)<=0){
                    adHostAccountAdOrder.setAdOrderStaus(EnumAdHostAccountAdOrderStatus.STOP.getKey());
                }
                i = adHostAccountDAO.updateHql(undateCon);
            }
        }

        this.updatePlatAccount(newAamount);

        //平台账号金额增加 广告主账号金额减少(金额和冻结金额)  订单消费金额增加
        this.updateAccountAdOrder(adHostAccountAdOrder,newAamount);
        return ignores;
    }


    private void updateAccountAdOrder(final AdHostAccountAdOrder adHostAccountAdOrder,final BigDecimal newAmount){
        BigDecimal adHostAmount = adHostAccountAdOrder.getAdHostAmount();
        adHostAmount = newAmount.add(adHostAmount);
        adHostAccountAdOrder.setAdHostAmount(adHostAmount);

        UpdateByCondition<AdHostAccountAdOrder> updateCon = new UpdateByCondition<AdHostAccountAdOrder>(adHostAccountAdOrder,true);
        int i = adHostAccountAdOrderDAO.updateHql(updateCon);
        //如果失败，在新事务查询出最新的数据(注意，不要在当前事务中查询)
        for(int j=0;i<1&&j<3;j++) {  //重试3次
            AdHostAccountAdOrder newOrder = transactionHelper.doInNewTran(new HelperBiz<AdHostAccountAdOrder>() {
                @Override
                public AdHostAccountAdOrder doNewTransaction() {
                    AdHostAccountAdOrder newOrder = adHostAccountAdOrderDAO.find(adHostAccountAdOrder.getAdOrderId());
                    return newOrder;
                }
            });
            adHostAccountAdOrder.setVersion(newOrder.getVersion());
            adHostAmount = newAmount.add(newOrder.getAdHostAmount());
            adHostAccountAdOrder.setAdHostAmount(adHostAmount);
            adHostAccountAdOrder.setVersion(newOrder.getVersion());
            i = adHostAccountAdOrderDAO.updateHql(updateCon);
        }
        if(i<1){
            logger.error("更新{}订单,金额{}异常",adHostAccountAdOrder.getAdOrderId(),adHostAccountAdOrder.getAdHostAmount());
            throw new RuntimeException("更新订单异常!");
        }
    }


    private void updatePlatAccount(BigDecimal newAamount){
        PlatAccountWrapper platAccountWrapper = SystemParamUtil.getPlatAccount();
        PlatAccount platAccount = platAccountWrapper.getPlatAccount();
        BigDecimal total = platAccount.getToalAmount().add(newAamount);
        platAccount.setToalAmount(total);
        BigDecimal nowAmount = platAccount.getNowAmount().add(newAamount);
        platAccount.setNowAmount(nowAmount);
        int i = platAccountWrapper.updatePlatAccount();
        for(int j=0;i<1&&j<3;j++) {  //重试3次
            HelperBiz<PlatAccountWrapper> helper = new HelperBiz<PlatAccountWrapper>() {
                @Override
                public PlatAccountWrapper doNewTransaction() {
                    PlatAccountWrapper newWrapper = SystemParamUtil.getPlatAccount();
                    return newWrapper;
                }
            } ;
            PlatAccountWrapper newWropper = transactionHelper.doInNewTran(helper);
            platAccount.setVersion(newWropper.getPlatAccount().getVersion());
            platAccount.setToalAmount(newWropper.getPlatAccount().getToalAmount().add(newAamount));
            platAccount.setNowAmount(newWropper.getPlatAccount().getNowAmount().add(newAamount));
            i = platAccountWrapper.updatePlatAccount();
        }
        if(i<1){
            logger.error("更新平台账户异常");
            throw new RuntimeException("更新平台账户异常");
        }
    }
    private void updateStPlatAccount(BigDecimal newAamount){
        PlatAccountWrapper platAccountWrapper = SystemParamUtil.getPlatAccount();
        PlatAccount platAccount = platAccountWrapper.getPlatAccount();
        BigDecimal nowAmount = platAccount.getNowAmount().subtract(newAamount);
        platAccount.setNowAmount(nowAmount);
        int i = platAccountWrapper.updatePlatAccount();
        for(int j=0;i<1&&j<3;j++) {  //重试3次
            HelperBiz<PlatAccountWrapper> helper = new HelperBiz<PlatAccountWrapper>() {
                @Override
                public PlatAccountWrapper doNewTransaction() {
                    PlatAccountWrapper newWrapper = SystemParamUtil.getPlatAccount();
                    return newWrapper;
                }
            } ;
            PlatAccountWrapper newWropper = transactionHelper.doInNewTran(helper);
            platAccount.setVersion(newWropper.getPlatAccount().getVersion());
            platAccount.setNowAmount(newWropper.getPlatAccount().getNowAmount().subtract(newAamount));
            i = platAccountWrapper.updatePlatAccount();
        }
        if(i<1){
            logger.error("更新平台账户异常");
            throw new RuntimeException("更新平台账户异常");
        }
    }

    /**
     * 站长计费
     *
     * @param ignoresCount  忽略总数,有些订单存在已结清的情况，这些订单的日志将不在计费
     * @param logs
     * @param type
     */
    private void stMasterCount(String adHostId,int ignoresCount,List<DayuvLog> logs,EnumAdHostAccountAdOrderType type){
        int count = logs.size()-ignoresCount;
        if(count<=0){
            return;
        }else if(ignoresCount>0){
            logs = logs.subList(ignoresCount,logs.size());
        }
        Map<String,List<DayuvLog>> hourCat = new HashMap<String,List<DayuvLog>>();
        AdHost adHost = adHostDAO.find(adHostId);
        if(adHost==null){
            return;
        }
        for (DayuvLog log:logs) {
            Integer accesTimeInt = log.getAccesTimeInt();
            List<DayuvLog> dayuvLogs = hourCat.get(accesTimeInt.toString());
            if(dayuvLogs==null){
                dayuvLogs = new ArrayList<>();
                hourCat.put(accesTimeInt.toString(),dayuvLogs);
            }
            dayuvLogs.add(log);
        }
        BigDecimal stmasterShowPrice = SystemParamUtil.getStmasterShowPrice();
        BigDecimal stmasterHitPrice = SystemParamUtil.getStmasterHitPrice();
        Set<Map.Entry<String, List<DayuvLog>>> entries = hourCat.entrySet();
        BigDecimal logAcount = BigDecimal.ZERO;
        BigDecimal priceTpe = BigDecimal.ZERO;
        if(type==EnumAdHostAccountAdOrderType.AD_HIT){
            priceTpe = stmasterHitPrice;
        }else if((type==EnumAdHostAccountAdOrderType.AD_SHOW)){
            priceTpe = stmasterShowPrice;
        }
        for(Map.Entry<String, List<DayuvLog>> entry:entries){
            String key = entry.getKey();
            Integer dateIntHour = Integer.valueOf(key);
            List<DayuvLog> value = entry.getValue();
            if(value.size()<1){
                continue;
            }
            Set<String> ipSet = new HashSet<>();
            for(DayuvLog log:value){
                ipSet.add(log.getUvIp());
            }
            BigDecimal multiply = new BigDecimal(adHost.getPriceLev()).multiply(priceTpe).multiply(new BigDecimal(value.size())).divide(new BigDecimal(10));
            StmasterSiteHour hour = new StmasterSiteHour();
            hour.setStSerialId(IdGeneratorFactory.nextId());
            hour.setRecordHour(dateIntHour);
            hour.setSettlementTime(new Date());
            hour.setCreateTime(new Date());
            hour.setHitCount(value.size());
            hour.setHourStatus(EnumStmasterSiteHourStatus.UNCLEAR.getKey());
            hour.setRecordDay(dateIntHour/100);
            hour.setIpCount(ipSet.size());
            hour.setSettlementAmount(multiply);
            hour.setSettlementNo(IdGeneratorFactory.nextId());
            hour.setShowCount(0);
            hour.setAdHostId(adHostId);
            hour.setUpdateTime(new Date());
            siteHourDAO.insert(hour);
            logAcount = logAcount.add(multiply);
        }
        this.updateStPlatAccount(logAcount);
    }
}
