package com.github.shoothzj.demo.pulsar;

import com.github.shoothzj.demo.pulsar.constant.PulsarConstant;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionType;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MultipleConsumerScene {

    public static void main(String[] args) throws InterruptedException, PulsarClientException {
        MultipleConsumerScene multipleConsumerScene = new MultipleConsumerScene();
        multipleConsumerScene.testPatternTopic();
    }

    public void testPatternTopic() throws PulsarClientException, InterruptedException {
        String topic = "test" + UUID.randomUUID().toString();
        System.out.println(String.format("topic is %s", topic));
        PulsarClient pulsarClient = PulsarClient.builder()
                .serviceUrl(PulsarConstant.SERVICE_URL)
                .build();
        Consumer<String> consumer1 = pulsarClient.newConsumer(Schema.STRING)
                .topic(topic)
                .subscriptionName("my-sub")
                .subscriptionType(SubscriptionType.Key_Shared)
                .subscribe();

        Consumer<String> consumer2 = pulsarClient.newConsumer(Schema.STRING)
                .topic(topic)
                .subscriptionName("my-sub")
                .subscriptionType(SubscriptionType.Key_Shared)
                .subscribe();

        Producer<String> producer1 = pulsarClient.newProducer(Schema.STRING)
                .topic(topic)
                .create();

        Thread.sleep(2000);
        final int messages = 10;
        new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    MessageId messageId1 = producer1.send("Message sent by producer-1 -> " + i);
                    System.out.println(String.format("producer send %s", messageId1));
                    i++;
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (PulsarClientException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 100000; i++) {
            Message<String> received1 = consumer1.receive(3, TimeUnit.SECONDS);
            Message<String> received2 = consumer2.receive(3, TimeUnit.SECONDS);
            Thread.sleep(200);
            System.out.println(received1);
            System.out.println(received2);
        }

    }

}
