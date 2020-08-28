package com.github.shoothzj.demo.pulsar;

import com.github.shoothzj.javatool.util.CommonUtil;
import com.github.shoothzj.javatool.util.LogUtil;
import com.github.shoothzj.demo.pulsar.constant.PulsarConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionType;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class PulsarAtLeastOnceConsumer {

    public static void main(String[] args) throws Exception {
        LogUtil.configureLog();
        String topic = "test" + UUID.randomUUID().toString();
        System.out.println(String.format("topic is %s", topic));
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PulsarConstant.SERVICE_URL)
                .build();
        Consumer<String> consumer = pulsarClient.newConsumer(Schema.STRING)
                .topic(topic)
                .subscriptionName("my-sub")
                .subscriptionType(SubscriptionType.Failover)
                .subscribe();
    }

}
