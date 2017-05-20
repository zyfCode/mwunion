package com.sungan.ad.commons;

/**
 * Created by zhangyf18255 on 2017/5/7.
 */
public class ClientUtil {
    private ClientUtil clientUtil;

    /**
     * 获取客户端口类型
     * @param agent
     * @return
     */
    public static EnumClientType getTypeByAgent(String agent){
        String[] pcAgentArr = new String[]{"Windows","MacOS"};
        String[] androidArr = new String[]{"Android","Linux"};
        String[] IOSArr= new String[]{"iPhone","ipad"};
        for(String agen:pcAgentArr){
            if(agent.toUpperCase().indexOf(agen.toUpperCase())>0){
                return EnumClientType.PC;
            }
        }
        for(String agen:androidArr){
            if(agent.toUpperCase().indexOf(agen.toUpperCase())>0){
                return EnumClientType.ANDROID;
            }
        }
        for(String agen:IOSArr){
            if(agent.toUpperCase().indexOf(agen.toUpperCase())>0){
                return EnumClientType.IOS;
            }
        }
        return EnumClientType.OTHRES;
    }

}
