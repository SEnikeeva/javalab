package ru.itis.rabbitmqexamples.consumer.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.rabbitmqexamples.util.Render;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NaturalConsumer {
    private final static String NATURAL_ROUTING_KEY = "natural.#";
    private final static String EXCHANGE = "docs_topic_exchange";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE, NATURAL_ROUTING_KEY);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {

                String data = new String(message.getBody());
                System.out.println("Start processing docs for " + data);

                try {
                    Render.render(data, "spec", "natural");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
