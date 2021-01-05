package com.github.shoothzj.demo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * @author hezhangjian
 */
@Slf4j
public class ZooKeeperConnection {

    public static void main(String[] args) {
        Configurator.setRootLevel(Level.DEBUG);
        final String zkAddrs = System.getProperty("zookeeper.addr", "127.0.0.1:2181");
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkAddrs)
                .sessionTimeoutMs(10_000).retryPolicy(retryPolicy).build();
        client.start();
        while (true) {

        }
    }

}
