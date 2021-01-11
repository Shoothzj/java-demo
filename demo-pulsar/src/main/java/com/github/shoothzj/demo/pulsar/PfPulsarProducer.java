package com.github.shoothzj.demo.pulsar;

import com.github.shoothzj.demo.pulsar.constant.PulsarConstant;
import com.github.shoothzj.javatool.util.CommonUtil;
import com.github.shoothzj.javatool.util.EnvUtil;
import com.github.shoothzj.javatool.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.ProducerBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author hezhangjian
 */
@Slf4j
public class PfPulsarProducer {

    public static void main(String[] args) throws Exception {
        Random random = new Random();
        LogUtil.configureLog();
        log.info("begin to start");
        final int pulsarSendDelayMs = EnvUtil.getIntVar("pulsar.send.delay.ms", "PULSAR_SEND_DELAY_MS", 0);
        final String pulsarAddr = EnvUtil.getStringVar("pulsar.addr", "PULSAR_ADDR", PulsarConstant.SERVICE_HTTP_URL);
        String topic = "test" + UUID.randomUUID().toString();
        System.out.println(String.format("topic is %s", topic));
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(pulsarAddr)
                .build();
        ProducerBuilder<String> producerBuilder = pulsarClient.newProducer(Schema.STRING).enableBatching(true);
        Producer<String> producer = producerBuilder.topic(topic).create();
        while (true) {
            try {
                StringBuilder messageBuilder = new StringBuilder(1024);
                for (int i = 0; i < 1024; i++) {
                    messageBuilder.append('a' + random.nextInt(26));
                }
                final CompletableFuture<MessageId> completableFuture = producer.sendAsync(messageBuilder.toString());
                completableFuture.exceptionally(throwable -> {
                    log.error("exec error ", throwable);
                    retrySend(messageBuilder.toString(), producer);
                    return (MessageId) throwable;
                }).thenAccept(messageId -> log.info("messageId is [{}]", messageId));
                if (pulsarSendDelayMs != 0) {
                    CommonUtil.sleep(TimeUnit.MICROSECONDS, pulsarSendDelayMs);
                }
            } catch (Exception e) {
                log.error("exception is ", e);
            }
        }
    }

    private static void retrySend(String message, Producer<String> producer) {
        try {
            final CompletableFuture<MessageId> completableFuture = producer.sendAsync(message);
            completableFuture.exceptionally(new Function<Throwable, MessageId>() {
                @Override
                public MessageId apply(Throwable throwable) {
                    log.error("exec error ", throwable);
                    return (MessageId) throwable;
                }
            }).thenAccept(new Consumer<MessageId>() {
                @Override
                public void accept(MessageId messageId) {
                    log.info("messageId is [{}]", messageId);
                }
            });
        } catch (Exception e) {
            log.error("retry failed ", e);
        }
    }

}
