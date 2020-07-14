package com.github.shoothzj.demo.basic;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        new Thread(new ConsumerImpl("consumer1", 2)).start();
        new Thread(new ConsumerImpl("consumer2", 3)).start();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
