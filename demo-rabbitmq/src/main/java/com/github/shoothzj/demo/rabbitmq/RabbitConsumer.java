package com.github.shoothzj.demo.rabbitmq;

import com.github.shoothzj.demo.base.util.CommonUtil;
import com.github.shoothzj.demo.base.util.LogUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class RabbitConsumer {

    public static void main(String[] args) throws Exception {
        LogUtil.configureLog();
        Address[] addresses = {new Address(DemoConstant.IP_ADDRESS, DemoConstant.PORT)};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root123");
        Connection connection = factory.newConnection(addresses);
        Channel channel = connection.createChannel();
        channel.basicQos(64);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("recv message: [{}]", new String(body));
                CommonUtil.sleep(TimeUnit.SECONDS, 1);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(DemoConstant.QUEUE_NAME, consumer);
        CommonUtil.sleep(TimeUnit.SECONDS, 5);
        channel.close();
        connection.close();
    }

}
