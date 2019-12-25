package com.jxkj.test;

import com.jxkj.utils.StringUtil;

public class StringTest {
    public static void main(String[] args) {
        String s="";
        if (StringUtil.isEmpty(s)){
            System.out.println("bu neg wei kong");
        }else {
            System.out.println("shi xiao");
        }
    }
}
