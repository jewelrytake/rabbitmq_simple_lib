package com.hireRight.forJar.consumer;

import com.hireRight.forJar.configuration.ChannelDto;
import com.hireRight.forJar.configuration.ConfigurationFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;

import java.io.IOException;

/**
 * General class providing a commons methods for the management of the consumers
 */
public abstract class PreparedConsumer {

    Channel setChannel(Channel channel) throws IOException {
        DefaultConsumer consumer = new DefaultConsumer(channel);
        ChannelDto info = ConfigurationFactory.getChannelInfo();
        channel.basicConsume(info.getQueueName(), false, consumer);
        return channel;
    }

    ChannelDto getInfo() {
        return ConfigurationFactory.getChannelInfo();
    }

    public abstract String beginConsume() throws IOException;
}
