package com.hireRight.forJar.publisher;

import com.rabbitmq.client.Channel;

import java.io.IOException;

import static com.hireRight.forJar.configuration.ConfigurationFactory.defaultQueueDeclare;

/**
 * Simple example to publish messages to AMQP
 */
public class DefaultPublisher extends PreparedPublisher {

    public void beginPublish() throws IOException, InterruptedException {
        Channel channel = setChannel(defaultQueueDeclare());

        for (int i = 0; i < 1000; i++) {

            String message = "Message from user = " + i;
            channel.basicPublish("", getInfo().getQueueName(), null, message.getBytes());
            System.out.println("Message to queue: " + message);
            Thread.sleep(1000);
        }
    }

}
