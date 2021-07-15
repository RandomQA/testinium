package com.gittigidiyor.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static{
        String path = "configuration.properties";
        try {
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public static String getProperty(String key){
        return properties.getProperty(key);
    }

}
