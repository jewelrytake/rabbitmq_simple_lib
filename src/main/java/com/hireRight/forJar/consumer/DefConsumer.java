package com.hireRight.forJar.consumer;

import com.hireRight.forJar.configuration.ChannelDto;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.hireRight.forJar.configuration.ConfigurationFactory.defaultQueueDeclare;

/**
 * Simple example to consume messages from AMQP
 */
public class DefConsumer extends PreparedConsumer {

    public String beginConsume() throws IOException {
        String mes;
        final Channel channel = setChannel(defaultQueueDeclare());
        final ChannelDto info = getInfo();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Message consumed: " + message);
        };

        mes = channel.basicConsume(info.getQueueName(), true, deliverCallback, consumerTag -> {
        });
        return mes;
    }

}
