package com.sungan.ad.commons.service.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息
 * Created by zhangyf18255 on 2017/4/11.
 */
public class EventQueen {
    private static  final Logger logger = LoggerFactory.getLogger(EventQueen.class);
    private EventQueen() {
    }
    private static final Map<EnumEventType,List<EventListener>> pool = new LinkedHashMap<EnumEventType,List<EventListener>>();

    /**
     * 添加事件
     * @param type
     * @param context
     */
    public static void addEvent(EnumEventType type,EvenContext context){
        List<EventListener> eventListeners = pool.get(type);
        if(eventListeners!=null){
            for (EventListener listener:eventListeners){
                listener.handler(context);
            }
        }else{
            if(logger.isWarnEnabled()){
                logger.warn("事件CODE：{}={}无消费者",type.getCode(),type.getRemark());
            }
        }
    }

    synchronized static void register(EventListener listener) throws  Exception{
        EnumEventType listenType = listener.getListenType();
        if(listenType==null){
            Exception exception =new Exception("事件类型为空");
            if(logger.isErrorEnabled()){
                logger.error("",exception);
            }
            throw exception;
        }

        List<EventListener> eventListeners = pool.get(listenType);
        if(eventListeners==null){
            eventListeners = new ArrayList<EventListener>();
        }
        for(EventListener tem:eventListeners){
            if(listener.getClass()==tem.getClass()){
                if(logger.isErrorEnabled()){
                    logger.error("{}重复注册",listener.getClass().getName());
                }
                throw new Exception("重复注册："+listener.getClass().getName());
            }
        }
        eventListeners.add(listener);
        pool.put(listenType,eventListeners);
    }
}
