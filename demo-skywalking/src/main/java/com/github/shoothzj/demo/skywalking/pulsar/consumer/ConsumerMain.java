package com.github.shoothzj.demo.skywalking.pulsar.consumer;

import com.github.shoothzj.demo.skywalking.pulsar.PulsarConst;
import com.github.shoothzj.javatool.util.CommonUtil;
import com.github.shoothzj.javatool.util.LogUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.ConsumerBuilder;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.PulsarClient;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class ConsumerMain {

    @SneakyThrows
    public static void main(String[] args) {
        LogUtil.configureLog();
        PulsarClient pulsarClient = PulsarClient.builder()
                .operationTimeout(1, TimeUnit.SECONDS)
                .serviceUrl("http://127.0.0.1:8080")
                .build();
        try {
            final ConsumerBuilder<byte[]> consumerBuilder = pulsarClient.newConsumer();
            Consumer<byte[]> consumer = consumerBuilder.topic(PulsarConst.TOPIC).subscriptionName(UUID.randomUUID().toString()).messageListener(new MessageListener<byte[]>() {
                @Override
                public void received(Consumer<byte[]> consumer, Message<byte[]> message) {
                    log.info("message is [{}]", message);
                }
            }).subscribe();
        } catch (Throwable e) {
            log.error("exception ", e);
        }
        while (true) {
            CommonUtil.sleep(TimeUnit.SECONDS, 1);
        }
    }

}
