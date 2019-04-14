package com.hireRight.forJar.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Convenience class providing default values for AMQP configuration
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {
    public static final String DEFAULT_URI = "amqp://guest:guest@localhost";
    public static final String DEFAULT_QUEUE_NAME = "defaultQueue";
    public static final String DEFAULT_EXCHANGE_NAME = "";
    public static final String DEFAULT_ROUTING_KEY = "";

    private String uri;
    private String queueName;
    private String exchangeName;
    private String routingKey;
}
