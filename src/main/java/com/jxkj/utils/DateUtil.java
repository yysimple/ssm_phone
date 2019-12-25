package com.jxkj.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getTime(){
        //设置时间样式
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate=new Date(System.currentTimeMillis());//获取当前时间
        //将时间类型转换成字符串
        String str=formatter.format(curDate);
        return str;
    }
}
