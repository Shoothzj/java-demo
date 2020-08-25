package com.github.shoothzj.demo.kafka;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author hezhangjian
 */
public interface KafkaAtLeastOnceCallback {

    CompletableFuture<Boolean> callback(String key, String content);

}
