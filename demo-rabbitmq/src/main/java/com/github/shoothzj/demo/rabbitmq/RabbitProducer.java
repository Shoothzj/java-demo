package com.github.shoothzj.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

import static com.github.shoothzj.demo.rabbitmq.DemoConstant.EXCHANGE_NAME;
import static com.github.shoothzj.demo.rabbitmq.DemoConstant.IP_ADDRESS;
import static com.github.shoothzj.demo.rabbitmq.DemoConstant.PORT;
import static com.github.shoothzj.demo.rabbitmq.DemoConstant.QUEUE_NAME;
import static com.github.shoothzj.demo.rabbitmq.DemoConstant.ROUTING_KEY;

/**
 * @author hezhangjian
 */
@Slf4j
public class RabbitProducer {

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("root");
        factory.setPassword("root123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
        String message = "Hello World!";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.close();
        connection.close();
    }


}
