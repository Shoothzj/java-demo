package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hezhangjian
 */
@Slf4j
public class AuxHelp {

    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();

    public void xxx() {
        map.put("key", "value");
    }

}
