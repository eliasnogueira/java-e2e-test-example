package com.eliasnogueira.workshop.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    /**
     * Return a value from conf/config.properties given a property
     * @param property an existing property from config/config.properties
     * @return the value of a property
     */
    public static String getValueFromConf(String property) {
        Properties properties;
        String value = null;
        try {
            properties = new Properties();
            properties.load(new FileReader(new File("conf/config.properties")));

            value =  properties.getProperty(property);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
