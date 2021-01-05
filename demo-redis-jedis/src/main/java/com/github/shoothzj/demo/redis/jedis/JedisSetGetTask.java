package com.github.shoothzj.demo.redis.jedis;

import com.github.shoothzj.demo.base.pf.PfTask;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @author hezhangjian
 */
@Slf4j
public class JedisSetGetTask implements PfTask {

    private final Jedis jedis;

    public JedisSetGetTask(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void task() {
        final String key = UUID.randomUUID().toString();
        jedis.set(key, UUID.randomUUID().toString());
        String value = jedis.get(key);
        log.debug("value is [{}]", value);
    }
}
