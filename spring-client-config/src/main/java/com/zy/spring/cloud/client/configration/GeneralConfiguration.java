package com.zy.spring.cloud.client.configration;

/**
 * @AUTHOR zhangy
 * 2019-09-15  14:40
 */
public class GeneralConfiguration {

    public static void main(String[] args) {
        System.out.println(System.getProperty("age"));
        Integer.getInteger("age");
    }
}
