package com.fucode.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换
 * @author sx
 * @version 2020年7月24日
 */
public class DateUtils {
    /**
     * 声明格式转换对象
     */
    private static final SimpleDateFormat SDF=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将String转换为java.util.Date
     * @throws ParseException
     */
    public static java.util.Date StringToUtilDate(String s1) throws ParseException {
        return SDF.parse(s1);
    }

    /**
     * 将java.util.Date转换为java.sql.Date
     * @param d1
     * @return java.sql.Date
     */
    public static java.sql.Date utilDateTosqlDate(java.util.Date d1) {
        return new java.sql.Date(d1.getTime());
    }

    public static java.util.Date sqlDateTosqlDate(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }

    /**
     * 将java.util.Date转换为String
     * @param d2
     * @return String
     */
    public static String utilDateToString(java.util.Date d2) {
        return SDF.format(d2);
    }

    // public static Date getNow(){
    //     Date date = new Date();
    //     String date1 = SDF.format(date);
    //
    // }
}
