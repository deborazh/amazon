package com.amazon.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    public static Properties readProperties(String fileName)  {
        fileName = fileName.trim();
        Properties property = null;
        try {
            InputStream fileReader = new FileInputStream(fileName);
            property = new Properties();
            property.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return property;
    }
}
