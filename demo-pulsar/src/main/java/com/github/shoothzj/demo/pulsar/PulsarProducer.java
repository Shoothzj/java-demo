package com.github.shoothzj.demo.pulsar;

import com.github.shoothzj.demo.base.util.CommonUtil;
import com.github.shoothzj.demo.pulsar.constant.PulsarConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.ProducerBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.Schema;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class PulsarProducer {

    public static void main(String[] args) throws Exception {
        String topic = "test" + UUID.randomUUID().toString();
        System.out.println(String.format("topic is %s", topic));
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PulsarConstant.SERVICE_URL)
                .build();
        ProducerBuilder<String> producerBuilder = pulsarClient.newProducer(Schema.STRING).enableBatching(false);
        Producer<String> producer = producerBuilder.topic(topic).create();
        while (true) {
            producer.send("xxx");
            CommonUtil.sleep(TimeUnit.SECONDS, 10);
        }
    }

}
