package com.github.shoothzj.demo.redis.lettuce;

import com.github.shoothzj.demo.base.config.RedisConfig;
import com.github.shoothzj.demo.base.pf.PfThread;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author hezhangjian
 */
@Slf4j
public class LettuceMain {

    public static void main(String[] args) {
        System.out.println("lettuce main");
        final RedisURI redisUri = RedisURI.builder().withHost(RedisConfig.getRedisHost()).withPort(RedisConfig.getRedisPort()).build();
        final RedisClient redisClient = RedisClient.create(redisUri);
        final StatefulRedisConnection<String, String> connection = redisClient.connect(new StringCodec(StandardCharsets.UTF_8), redisUri);
        final RedisAsyncCommands<String, String> asyncCommands = connection.async();
        final RedisCommands<String, String> syncCommands = connection.sync();
        new PfThread(new LettuceSetGetTask(asyncCommands, syncCommands)).run();
    }

}
