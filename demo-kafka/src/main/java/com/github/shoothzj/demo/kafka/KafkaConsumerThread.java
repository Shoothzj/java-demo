package com.github.shoothzj.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class KafkaConsumerThread extends Thread {

    private final String topic;

    private final KafkaConsumer<String, String> consumer;

    public KafkaConsumerThread(String kafkaAddr, String topic) {
        this.topic = topic;
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddr);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, topic);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 5000);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        setName("consumer" + topic);
    }

    @Override
    public void run() {
        while (true) {
            try {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(500);
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    log.info("receive a record, offset is [{}]", consumerRecord.offset());
                }
            } catch (Exception e) {
                log.error("exception happended, exception is ", e);
            }
        }
    }
}