package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class ConcurrentMapDemo {

    static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) throws InterruptedException {
        AuxHelp auxHelp = new AuxHelp();
        while (true) {
            auxHelp.xxx();
            map.put("key", "value");
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

}
