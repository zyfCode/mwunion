package com.sungan.ad.commons;

/**
 * Created by zhangyf18255 on 2017/5/7.
 */
public enum  EnumClientType {
    PC("0","pc"),
    IOS("1","IOS"),
    ANDROID("2","安卓"),
    OTHRES("3","其他"),
    ;
    private String code;
    private String desc;

    EnumClientType(String code,String desc){
        this.code = code;
        this.desc = desc;
    }
    public static EnumClientType match(String code){
        EnumClientType[] values = EnumClientType.values();
        for(EnumClientType type:values){
            if(type.getCode().equals(code)){
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
