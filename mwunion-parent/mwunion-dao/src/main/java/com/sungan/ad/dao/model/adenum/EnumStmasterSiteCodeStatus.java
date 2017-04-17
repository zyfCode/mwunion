package com.sungan.ad.dao.model.adenum;

import com.sungan.ad.common.dict.DictHandlerImpl;
import com.sungan.ad.common.dict.EnumCommon;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.exception.AdRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
public enum EnumStmasterSiteCodeStatus {

    INUSED("1","生效中"),
    INVALID("2","禁用")
    ;
    public static final String DICT_KEY="STMASTER_SITECODE_STATUS";
    public static final String DICT_NAME="CODE状态";
    private String key;
    private String label;


    private EnumStmasterSiteCodeStatus(String key, String label) {
        this.key = key;
        this.label = label;
        try {
            Class.forName(EnumStmasterSiteCodeStatus.EnumCommonImpl.class.getName());
        } catch (Exception e) {
            throw new AdRuntimeException("",e);
        }
    }

    public static EnumStmasterSiteCodeStatus match(String key){
        EnumStmasterSiteCodeStatus[] values = EnumStmasterSiteCodeStatus.values();
        for(EnumStmasterSiteCodeStatus engine:values){
            if(engine.getKey().equals(key)){
                return engine;
            }
        }
        return null;
    }

    public String getKey(){
        return this.key;
    }
    public String getLabel(){
        return this.label;
    }

    private static class EnumCommonImpl implements EnumCommon {
        static{
            DictHandlerImpl.register(new EnumStmasterSiteCodeStatus.EnumCommonImpl());
        }
        @Override
        public String getEnumPk() {
            return DICT_KEY;
        }

        @Override
        public String getDictName() {
            return DICT_NAME;
        }

        @Override
        public List<DictItem> getItems() {
            EnumStmasterSiteCodeStatus[] values = EnumStmasterSiteCodeStatus.values();
            List<DictItem> result = new ArrayList<DictItem>();
            for(EnumStmasterSiteCodeStatus status:values){
                DictItem item = new DictItem(status.getKey(), status.getLabel());
                result.add(item);
            }
            return result;
        }

        @Override
        public DictItem getItem(String key) {
            List<DictItem> items = this.getItems();
            for(DictItem item:items){
                if(item.getKey().equals(key)){
                    return item;
                }
            }
            return null;
        }
    }
}
