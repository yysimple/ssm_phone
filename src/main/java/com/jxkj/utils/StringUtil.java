package com.jxkj.utils;

public class StringUtil {

    public static boolean isEmpty(String arg){
        if (arg == null && "".equals(arg)){
            return true;
        }
        return false;
    }
}
