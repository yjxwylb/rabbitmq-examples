package com.javashitang.rabbitmq.chapter_3_getMessage;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: lilimin
 * @Date: 2019/8/26 23:32
 */
public class GetMessageProducer {

    public final static String EXCHANGE_NAME = "getMessage_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("www.javashitang.com");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        for (int i = 0; i < 3; i++) {
            String message = "Hello RabbitMQ" + (i + 1);
            channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}