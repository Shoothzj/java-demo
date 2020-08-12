package com.github.shoothzj.demo.kafka.pf;

import com.github.shoothzj.demo.kafka.KafkaConstant;
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
public class PfProducerMain {

    public static final Random random = new Random();

    public static void main(String[] args) {
        log.info("begin to start");
        //解析参数
        int workerThread = Integer.parseInt(System.getenv(PfProducerConstant.WORKER_THREAD));
        int producerNum = Integer.parseInt(System.getenv(PfProducerConstant.PRODUCER_NUM));
        int ratePerThread = Integer.parseInt(System.getenv(PfProducerConstant.RATE_PER_THREAD));
        int messageByte = Integer.parseInt(System.getenv(PfProducerConstant.MESSAGE_BYTE));
        String kafkaAddr = System.getenv(KafkaConstant.KAFKA_ADDR);
        String topic = System.getenv(KafkaConstant.TOPIC);

        ArrayList<KafkaProducer<String, String>> producerList = new ArrayList<>(producerNum);
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
                        ProducerRecord<String, String> record = getRecord(topic, messageByte);
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

    private static ProducerRecord<String, String> getRecord(String topic, int messageByte) {
        StringBuilder messageBuilder = new StringBuilder(messageByte);
        for (int i = 0; i < messageByte; i++) {
            messageBuilder.append('a' + random.nextInt(26));
        }
        return new ProducerRecord<>(topic, 0, "key", messageBuilder.toString());
    }

}
