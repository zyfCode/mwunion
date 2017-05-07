/**
 * Hangzhou Hundsun Cloud Financing Network Technology Co.,Ltd.
 * Copyright 2015-2015 | www.hsjry.com . All rights reserved .
 */
package com.sungan.ad.commons;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 说明:金额工具类
 * @author ljf <liangjf@hundsun.com>
 * @date 2015-9-10 下午7:11:46 
 * @version V1.0
 */
public class MoneyUtil {

	/**千分位格式串*/
	public final static String FORMATTHOUSANDS = ",###,###.##";
	
	private static final String UNIT = "万仟佰拾亿仟佰拾万仟佰拾元角分";  
    private static final String DIGIT = "零壹贰叁肆伍陆柒捌玖";  
    private static final double MAX_VALUE = 9999999999999.99D;
    
	/**
	 * 金额格式化
	 * @param amount
	 * @return
	 */
	public static String formatMoney(BigDecimal amount, String... format) {
		String formatName = "#0.00";
		if(format.length > 0) {
			formatName = format[0];
		}
		DecimalFormat fmt = new DecimalFormat(formatName);
		return fmt.format(amount);
	}
	
	/**
	 * 金额保留小数位，四舍五入
	 * @param amount
	 * @param n
	 * @return
	 */
	public static BigDecimal formatMoneyScale(BigDecimal amount,int n) {
		return amount.setScale(n,BigDecimal.ROUND_HALF_UP);
	}
	/**把金额转成字符串并加上单位元*/
	public static String moneyToStr(BigDecimal money){
		money=money.setScale(2, BigDecimal.ROUND_HALF_UP);
		String moneyStr=money.toString()+"元";
		return moneyStr;
	}
	
    /**
     * 金额转换成大写
     * @param money
     * @return
     */
    public static String changeCH(BigDecimal money) {  
     double v = money.doubleValue();
     if (v < 0 || v > MAX_VALUE){  
         return "参数非法!";  
     }  
     long l = Math.round(v * 100);  
     if (l == 0){  
         return "零元整";  
     }  
     String strValue = l + "";  
     // i用来控制数  
     int i = 0;  
     // j用来控制单位  
     int j = UNIT.length() - strValue.length();  
     String rs = "";  
     boolean isZero = false;  
     for (; i < strValue.length(); i++, j++) {  
      char ch = strValue.charAt(i);  
      if (ch == '0') {  
       isZero = true;  
       if (UNIT.charAt(j) == '亿' || UNIT.charAt(j) == '万' || UNIT.charAt(j) == '元') {  
        rs = rs + UNIT.charAt(j);  
        isZero = false;  
       }  
      } else {  
       if (isZero) {  
        rs = rs + "零";  
        isZero = false;  
       }  
       rs = rs + DIGIT.charAt(ch - '0') + UNIT.charAt(j);  
      }  
     }  
     if (!rs.endsWith("分")) {  
      rs = rs + "整";  
     }  
     rs = rs.replaceAll("亿万", "亿");  
     return rs;  
    } 
    
    public static BigDecimal null2Zero(BigDecimal amt){
		if(null == amt){
			amt = BigDecimal.ZERO;
		}
		return amt;
	}
    
    public static void main(String[] args) {
		System.out.println(MoneyUtil.null2Zero(BigDecimal.ZERO));
	}
}
