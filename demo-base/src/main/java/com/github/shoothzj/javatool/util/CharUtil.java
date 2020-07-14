package com.github.shoothzj.javatool.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hezhangjian
 */
public class CharUtil {

    private static final Logger log = LoggerFactory.getLogger(CharUtil.class);

    private static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean isUpper(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    public static boolean isDown(char ch) {
        return ch >= 'a' && ch <= 'z';
    }


}
