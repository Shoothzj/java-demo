package com.github.shoothzj.demo.kafka.forward;

import com.github.shoothzj.demo.base.test.module.TestDeviceDto;
import com.github.shoothzj.demo.base.test.util.TestDataUtil;
import com.github.shoothzj.javatool.service.JacksonService;
import com.github.shoothzj.javatool.util.EnvUtil;
import com.github.shoothzj.demo.base.config.KafkaConfigConstant;
import com.github.shoothzj.demo.base.config.PfProducerConstant;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hezhangjian
 */
@Slf4j
public class SimuKafkaSender {

    public static final Random random = new Random();

    public static void main(String[] args) {
        log.info("begin to start");
        //解析参数
        int workerThread = EnvUtil.getIntVar("worker.thread", PfProducerConstant.WORKER_THREAD, 1);
        int producerNum = EnvUtil.getIntVar("producer.num", PfProducerConstant.PRODUCER_NUM, 1);
        int ratePerThread = EnvUtil.getIntVar("rate.per.thread", PfProducerConstant.RATE_PER_THREAD, 1);
        String kafkaAddr = EnvUtil.getStringVar("kafka.addr", KafkaConfigConstant.KAFKA_ADDR, "localhost:9092");
        String topic = EnvUtil.getStringVar("kafka.topic", KafkaConfigConstant.KAFKA_TOPIC, "test");

        ArrayList<KafkaProducer<String, String>> producerList = Lists.newArrayListWithCapacity(producerNum);
        for (int i = 0; i < producerNum; i++) {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddr);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            producerList.add(new KafkaProducer<>(props));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(workerThread);
        executorService.execute(() -> {
            RateLimiter limiter = RateLimiter.create(ratePerThread);
            while (true) {
                if (limiter.tryAcquire()) {
                    try {
                        ProducerRecord<String, String> record = getRecord(topic);
                        producerList.get(random.nextInt(producerNum)).send(record, (recordMetadata, e) -> {
                            if (e != null) {
                                log.error("exception is ", e);
                            } else {
                                log.info("send record to [{}]", record.topic());
                            }
                        });
                    } catch (Exception e) {
                        log.error("producer exception ", e);
                    }
                }
            }
        });
    }

    private static ProducerRecord<String, String> getRecord(String topic) {
        TestDeviceDto testDeviceDto = TestDataUtil.generateTestDeviceDto();
        return new ProducerRecord<>(topic, testDeviceDto.getDeviceId(), JacksonService.toJson(testDeviceDto));
    }

}
