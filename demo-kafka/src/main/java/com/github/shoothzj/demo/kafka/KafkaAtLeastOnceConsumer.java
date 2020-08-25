package com.github.shoothzj.demo.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author hezhangjian
 */
@Slf4j
public class KafkaAtLeastOnceConsumer extends Thread {

    private final String topic;

    private final KafkaConsumer<String, String> consumer;

    private final KafkaAtLeastOnceCallback kafkaAtLeastOnceCallback;

    private final Map<Integer, PriorityQueue<Long>> map= new ConcurrentHashMap<>();

    private final Map<Integer, Long> firstPosMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer, Long> nextPositionMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer, ReentrantReadWriteLock> lockMap = new ConcurrentHashMap<>();

    public KafkaAtLeastOnceConsumer(String kafkaAddr, String topic, KafkaAtLeastOnceCallback kafkaAtLeastOnceCallback) {
        this.topic = topic;
        this.kafkaAtLeastOnceCallback = kafkaAtLeastOnceCallback;
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
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    log.info("receive a record, offset is [{}]", consumerRecord.offset());
                    ReentrantReadWriteLock readWriteLock = lockMap.putIfAbsent(consumerRecord.partition(), new ReentrantReadWriteLock());
                    firstPosMap.putIfAbsent(consumerRecord.partition(), consumerRecord.offset());
                    processRecord(consumerRecord, readWriteLock);
                }
            } catch (Exception e) {
                log.error("exception happended, exception is ", e);
            }
        }
    }

    private void processRecord(ConsumerRecord<String, String> consumerRecord, ReentrantReadWriteLock reentrantReadWriteLock) {
        while (true) {
            try {
                CompletableFuture<Boolean> completableFuture = kafkaAtLeastOnceCallback.callback(consumerRecord.key(), consumerRecord.value());
                completableFuture.handle((aBoolean, throwable) -> {
                    if (!aBoolean || throwable != null) {
                        processRecord(consumerRecord, reentrantReadWriteLock);
                    }
                    long offset = consumerRecord.offset();
                    int partition = consumerRecord.partition();
                    PriorityQueue<Long> integers = map.get(partition);
                    reentrantReadWriteLock.writeLock().lock();
                    try {
                        //这是kafka启动后收到的第一个的回调，当然要提交
                        if (offset == firstPosMap.get(partition)) {
                            prepareAndCommit(partition, offset, integers);
                        } else if (offset == nextPositionMap.get(partition)) {
                            //是预想中的消息，提交
                            prepareAndCommit(partition, offset, integers);
                        } else {
                            integers.add(offset);
                        }
                    } finally {
                        reentrantReadWriteLock.writeLock().unlock();
                    }
                    return null;
                });
                break;
            } catch (Exception e) {
                log.error("fatal error, submit work exception ", e);
            }
        }
    }

    private void prepareAndCommit(int partition, long offset, PriorityQueue<Long> integers) {
        long newOffset = offset;
        while (true) {
            Long peek = integers.peek();
            if (peek == null) {
                break;
            }
            if (peek <= newOffset + 1) {
                integers.poll();
                newOffset = Math.max(peek, newOffset);
            } else {
                break;
            }
        }
        Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
        offsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(newOffset));
        //commit没送达也可，待下次commit
        consumer.commitAsync(offsets, (offsets1, exception) -> {
            if (exception != null) {
                log.error("offset is [{}], commit fail [{}]", offsets1, exception);
                return;
            }
            log.debug("offset commit success [{}]", offsets1);
        });
    }

}
