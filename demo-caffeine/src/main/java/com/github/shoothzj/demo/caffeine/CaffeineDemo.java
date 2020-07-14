package com.github.shoothzj.demo.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.shoothzj.demo.caffeine.module.CacheKey;
import com.github.shoothzj.demo.caffeine.module.CacheValue;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class CaffeineDemo {

    public static void main(String[] args) {
        log.info("Hello");
        LoadingCache<CacheKey, CacheValue> graphs = Caffeine.newBuilder()
                .maximumSize(30_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> createExpensiveGraph(key));
    }

    private static CacheValue createExpensiveGraph(CacheKey cacheKey) {
        return new CacheValue();
    }

}
