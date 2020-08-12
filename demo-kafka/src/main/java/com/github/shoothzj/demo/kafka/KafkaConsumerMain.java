package com.github.shoothzj.demo.kafka;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class KafkaConsumerMain {

    public static void main(String[] args) {
        //parse param
        String kafkaAddr = System.getenv(KafkaConstant.KAFKA_ADDR);
        String topic = System.getenv(System.getenv(KafkaConstant.TOPIC));

        new KafkaConsumerThread(kafkaAddr, topic).run();
    }

}
