package com.sungan.ad.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/4/12.
 */
public class DateUtil {

    private DateUtil (){}


    /**
     *精确天
     * @param date
     * @return
     */
    public static Integer dateToIntDay(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String format1 = format.format(date);
        Integer integer = Integer.valueOf(format1);
        return integer;
    }

    public static  Integer dateToIntDayNow(){
        Date date = new Date();
        return dateToIntDay(date);
    }

    public static Integer getHour(){
        int i = Calendar.getInstance().get(Calendar.HOUR);
        return i;
    }

    public static Integer getHour(Date date){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = instance.get(Calendar.HOUR);
        return i;
    }

    public static Date integerToDate(String timeStr){
        if(timeStr!=null||!timeStr.matches("\\d+")){
            throw new RuntimeException("日期格式错误");
        }
        SimpleDateFormat format = null;
        if(timeStr.length()==8){
            format =  new SimpleDateFormat("yyyyMMdd");
        }else if(timeStr.length()==10){
            format =  new SimpleDateFormat("yyyyMMddHH");
        }
        try {
            Date parse = format.parse(timeStr);
            return parse;
        } catch (ParseException e) {
           throw new RuntimeException("",e);
        }
    }


    /**
     *精确到小时
     * @param date
     * @return
     */
    public static Integer dateToIntHour(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHH");
        String format1 = format.format(date);
        Integer integer = Integer.valueOf(format1);
        return integer;
    }

    public static  Integer dateToIntHourNow(){
        Date date = new Date();
        return dateToIntHour(date);
    }


}
