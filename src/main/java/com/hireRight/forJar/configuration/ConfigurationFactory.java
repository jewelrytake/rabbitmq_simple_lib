package com.hireRight.forJar.configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import static com.hireRight.forJar.configuration.ChannelDto.*;

/**
 * Convenience factory class to facilitate opening a {@link Connection} to a RabbitMQ node.
 * Most connection and socket settings are configured using this factory.
 * Some settings that apply to connections can also be configured here
 * and will apply to all connections produced by this factor
 */
public class ConfigurationFactory {

    /** General class instance for {@link ConfigurationFactory} */
    private static final ConnectionFactory factory = new ConnectionFactory();

    /** General {@link Channel} instance for {@link ConfigurationFactory} */
    private static Channel channel = null;

    /** General {@link ChannelDto} instance for {@link ConfigurationFactory} */
    private static ChannelDto dto;

    /**
     * Create a custom queue without manual creation in rabbitMQ-panel
     * @param uri is the AMQP URI containing the data
     * @param queueName is the name of queue that will be created in RabbitMQ with default exchanger
     * @return the channel to use for data manipulation
     */
    public static Channel queueDeclare(String uri, String queueName) {

        try {
            factory.setConnectionTimeout(333333333);
            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            factory.setUri(uri);
            channel.queueDeclare(queueName, true, false, false, null);

            dto = ChannelDto.builder().uri(uri).queueName(queueName).build();
        } catch (IOException | NoSuchAlgorithmException | URISyntaxException | TimeoutException | KeyManagementException e) {
            e.printStackTrace();
        }
        return channel;
    }

    //need to know exchanger and create manually

    /**
     * Bind a queue to an exchange, with no extra arguments
     * @param uri is the AMQP URI containing the data
     * @param queueName the name of the queue
     * @param exchangerName the name of the exchange
     * @param routingKey the routing key to use for the binding
     * @return the channel to use for data manipulation
     */
    public static Channel queueBinding(String uri, String queueName, String exchangerName, String routingKey) {
        try {
            factory.setConnectionTimeout(333333333);
            Connection connection = factory.newConnection();
            channel = connection.createChannel();

            factory.setUri(uri);
            channel.queueBind(queueName, exchangerName, routingKey);

            dto = new ChannelDto(uri, queueName, exchangerName, routingKey);
        } catch (IOException | NoSuchAlgorithmException | URISyntaxException | TimeoutException | KeyManagementException e){
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * Create channel with default values from {@link ChannelDto}
     * @return the channel to use for data manipulation
     */
    public static Channel defaultQueueDeclare() {
        try {
            factory.setConnectionTimeout(999999);
            Connection connection = factory.newConnection();
            channel = connection.createChannel();
            factory.setUri(DEFAULT_URI);
            channel.queueDeclare(DEFAULT_QUEUE_NAME, true, false, false, null);
            dto = new ChannelDto(DEFAULT_URI, DEFAULT_QUEUE_NAME, DEFAULT_EXCHANGE_NAME, DEFAULT_ROUTING_KEY);
        }
        catch (IOException | NoSuchAlgorithmException | URISyntaxException | TimeoutException | KeyManagementException e) {
            e.printStackTrace();
        }
        return channel;
    }

    /**
     * Receive current values of {@link ChannelDto} after data changes
     * @return current state
     */
    public static ChannelDto getChannelInfo() {
        return dto;
    }
}
