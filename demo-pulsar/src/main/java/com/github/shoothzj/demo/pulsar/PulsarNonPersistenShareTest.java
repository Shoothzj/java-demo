package com.github.shoothzj.demo.pulsar;

import com.github.shoothzj.demo.pulsar.constant.PulsarConstant;
import org.apache.pulsar.client.api.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PulsarNonPersistenShareTest {

    @Test
    public void testPatternTopic() throws PulsarClientException, InterruptedException {
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PulsarConstant.SERVICE_URL)
                .build();
        final String topic1 = "non-persistent://public/default/testPatternTopic1-" + UUID.randomUUID().toString();
        final String topic2 = "non-persistent://public/default/testPatternTopic2-" + UUID.randomUUID().toString();
        Pattern pattern = Pattern.compile("public/default/.*");
        Consumer<String> consumer = pulsarClient.newConsumer(Schema.STRING)
                .topicsPattern(pattern)
                .subscriptionName("my-sub")
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .subscriptionTopicsMode(RegexSubscriptionMode.AllTopics)
                .subscribe();

        Producer<String> producer1 = pulsarClient.newProducer(Schema.STRING)
                .topic(topic1)
                .create();

        Producer<String> producer2 = pulsarClient.newProducer(Schema.STRING)
                .topic(topic2)
                .create();

        Thread.sleep(2000);
        final int messages = 10;
        for (int i = 0; i < messages; i++) {
            MessageId messageId1 = producer1.send("Message sent by producer-1 -> " + i);
            MessageId messageId2 = producer2.send("Message sent by producer-2 -> " + i);
            System.out.println(String.format("%s $=%s", messageId1, messageId2));
        }

        for (int i = 0; i < messages * 2; i++) {
            Message<String> received = consumer.receive(3, TimeUnit.SECONDS);
            assertNotNull(received);
        }

        consumer.close();
        producer1.close();
        producer2.close();
    }

}
