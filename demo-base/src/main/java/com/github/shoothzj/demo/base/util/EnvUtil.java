package com.github.shoothzj.demo.base.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class EnvUtil {

    /**
     * 用户路径
     * e.g. Mac: /Users/akka
     */
    private static final String userHome = System.getProperty("user.home");
    /**
     * 操作系统名称
     * e.g. Mac: Mac OS X
     */
    private static final String osName = System.getProperty("os.name");
    /**
     * os的版本
     * e.g. 10.15.2
     */
    private static final String osVersion = System.getProperty("os.version");

    public static String getUserHome() {
        return userHome;
    }

    public static String getOsName() {
        return osName;
    }

    public static String getOsVersion() {
        return osVersion;
    }

    public static String getHostName() {
        return System.getenv("HOSTNAME");
    }

    public static String getServiceName() {
        return System.getenv("SERVICE_NAME");
    }

    public static void listAll() {
        log.info("userHome is {}", userHome);
        log.info("osName is {}", osName);
        log.info("osVersion is {}", osVersion);
    }
}
