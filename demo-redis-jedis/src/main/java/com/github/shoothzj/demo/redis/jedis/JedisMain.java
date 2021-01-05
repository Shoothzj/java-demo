package com.github.shoothzj.demo.redis.jedis;

import com.github.shoothzj.demo.base.config.RedisConfig;
import com.github.shoothzj.demo.base.pf.PfThread;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @author hezhangjian
 */
@Slf4j
public class JedisMain {

    public static void main(String[] args) {
        System.out.println("jedis main");
        Jedis jedis = new Jedis(RedisConfig.getRedisHost(), RedisConfig.getRedisPort());
        new PfThread(new JedisSetGetTask(jedis)).run();
    }

}
