package com.hireRight.forJar.publisher;

import com.hireRight.forJar.configuration.ChannelDto;
import com.hireRight.forJar.configuration.ConfigurationFactory;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * General class providing a commons methods for the management of the publishers
 */
public abstract class PreparedPublisher {

    protected Channel setChannel(Channel channel) {
        return channel;
    }

    protected ChannelDto getInfo() {
        return ConfigurationFactory.getChannelInfo();
    }

    public abstract void beginPublish() throws IOException, InterruptedException;
}
