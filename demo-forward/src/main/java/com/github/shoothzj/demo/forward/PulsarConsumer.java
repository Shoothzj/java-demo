package com.github.shoothzj.demo.forward;

import com.github.shoothzj.javatool.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.util.concurrent.TimeUnit;

/**
 *
 * 启动的时候,并不会消费以前就有的消息
 * @author hezhangjian
 */
@Slf4j
public class PulsarConsumer {

    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://127.0.0.1:6650")
                .build();
        Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .topic("my-topic2")
                .subscriptionName("my-subscription22")
                .subscribe();
        while (true) {
            // Wait for a message
            Message msg = consumer.receive();
            try {
                // Do something with the message
                log.info("Message received: [{}]", msg.getMessageId());
                CommonUtil.sleep(TimeUnit.SECONDS, 10);
                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
    }

}
