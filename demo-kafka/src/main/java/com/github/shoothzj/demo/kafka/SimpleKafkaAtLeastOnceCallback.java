package com.github.shoothzj.demo.kafka;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author hezhangjian
 */
@Slf4j
public class SimpleKafkaAtLeastOnceCallback implements KafkaAtLeastOnceCallback{
    @Override
    public CompletableFuture<Boolean> callback(String key, String content) {
        return null;
    }
}
