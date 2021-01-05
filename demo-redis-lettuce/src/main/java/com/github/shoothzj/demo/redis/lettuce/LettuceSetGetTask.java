package com.github.shoothzj.demo.redis.lettuce;

import com.github.shoothzj.demo.base.pf.PfTask;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author hezhangjian
 */
@Slf4j
public class LettuceSetGetTask implements PfTask {

    private RedisAsyncCommands<String, String> asyncCommands;

    private RedisCommands<String, String> syncCommands;

    public LettuceSetGetTask(RedisAsyncCommands<String, String> asyncCommands, RedisCommands<String, String> syncCommands) {
        this.asyncCommands = asyncCommands;
        this.syncCommands = syncCommands;
    }

    @Override
    public void task() {
        final String key = UUID.randomUUID().toString();
        this.syncCommands.set(key, UUID.randomUUID().toString());
        final String value = this.syncCommands.get(key);
        log.debug("value is [{}]", value);
    }


}
