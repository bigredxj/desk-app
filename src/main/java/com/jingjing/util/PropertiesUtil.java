package com.jingjing.util;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

@Slf4j
public class PropertiesUtil {
    public static Properties properties = new Properties();

    static {
        try {
            // 使用InPutStream流读取properties文件
            FileInputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir") +
                    File.separator + "conf" + File.separator + "app.properties"));
            properties.load(inputStream);
        }catch (Exception e){
            log.error("",e);
        }
    }

    public static String getProperty(String str) {
        return properties.getProperty(str);
    }
}
