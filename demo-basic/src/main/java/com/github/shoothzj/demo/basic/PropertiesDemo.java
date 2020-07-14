package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class PropertiesDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("1", "2");
        properties.put("3", "4");
        System.out.println(properties.size());
    }

}
