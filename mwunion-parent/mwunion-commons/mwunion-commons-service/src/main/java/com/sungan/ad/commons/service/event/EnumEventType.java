package com.sungan.ad.commons.service.event;

/**
 * Created by zhangyf18255 on 2017/4/11.
 */
public enum  EnumEventType {
    /**
     * 添加站长
     */
    ADD_STMARSTER("0001","添加站长"),
    /**
     * 添加广告主
     */
    ADD_ADHOST("1001","添加广告主"),
    /**
     * 添加站点
     */
    ADD_STMARSTER_SITE("1002", "添加站点"),
    ;
    private String code;
    private String remark;

    private EnumEventType(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }

    public static EnumEventType match(String key){
        EnumEventType[] values = EnumEventType.values();
        for(EnumEventType engine:values){
            if(engine.getCode().equals(key)){
                return engine;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
