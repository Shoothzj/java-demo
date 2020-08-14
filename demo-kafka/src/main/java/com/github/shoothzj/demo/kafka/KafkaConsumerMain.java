package com.github.shoothzj.demo.kafka;

import com.github.shoothzj.demo.base.config.KafkaConfigConstant;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class KafkaConsumerMain {

    public static void main(String[] args) {
        //parse param
        String kafkaAddr = System.getenv(KafkaConfigConstant.KAFKA_ADDR);
        String topic = System.getenv(KafkaConfigConstant.KAFKA_TOPIC);

        new KafkaConsumerThread(kafkaAddr, topic).run();
    }

}
