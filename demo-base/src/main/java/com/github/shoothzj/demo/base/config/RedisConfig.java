package com.github.shoothzj.demo.base.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class RedisConfig {

    public static String getRedisHost() {
        return System.getenv("REDIS_HOST");
    }

    public static int getRedisPort() {
        return Integer.parseInt(System.getenv("REDIS_PORT"));
    }

}
