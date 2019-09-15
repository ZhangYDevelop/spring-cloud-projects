package com.zy.spring.cloud.client.configration;

import java.io.IOException;
import java.util.Properties;

/**
 * @AUTHOR zhangy
 * 2019-09-15  14:35
 */
public class PropertyDemo {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("user.name", "zhangyu");
        properties.setProperty("age", "26");
        properties.storeToXML(System.out, "this is a comment", "utf-8");
    }
}
