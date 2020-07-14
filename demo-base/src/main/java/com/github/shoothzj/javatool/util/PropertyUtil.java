package com.github.shoothzj.javatool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);

    private static final String propertyFile = "shoot.properties";

    private static final Properties properties;

    private static final Properties testProperties;

    public static volatile boolean testEnabled = false;

    public static boolean isTestLoaded = false;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(FileUtil.getFilePath(propertyFile)));
        } catch (Exception e) {
            log.error("load properties error, exception is {}", e);
            throw new RuntimeException();
        }
        testProperties = new Properties();
        try {
            testProperties.load(new FileInputStream(System.getProperty("user.home") + "/test.properties"));
            isTestLoaded = true;
        } catch (Exception e) {
            log.error("Test properties load error, do nothing {}", e);
        }
    }

    public static Double readDouble(String key) {
        String value = readString(key);
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return Double.parseDouble(value);
        }
    }

    public static Float readFloat(String key) {
        String value = readString(key);
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return Float.parseFloat(value);
        }
    }

    public static Integer readInt(String key) {
        String value = readString(key);
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

    public static Short readShort(String key) {
        String value = readString(key);
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return Short.parseShort(value);
        }
    }

    public static Byte readByte(String key) {
        String value = readString(key);
        if (StringUtil.isEmpty(value)) {
            return null;
        } else {
            return Byte.parseByte(value);
        }
    }

    public static String readString(String key) {
        if (isTestLoaded && testEnabled) {
            String value = (String) testProperties.get(key);
            if (StringUtil.isNotEmpty(value)) {
                return value;
            }
        }
        return (String) properties.get(key);
    }

}
