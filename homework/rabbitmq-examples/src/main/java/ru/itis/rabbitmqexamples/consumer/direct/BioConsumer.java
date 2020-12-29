package ru.itis.rabbitmqexamples.consumer.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.rabbitmqexamples.util.Render;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class BioConsumer {
    private final static String PHYSICS_QUEUE = "bio_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            channel.basicConsume(PHYSICS_QUEUE, false, (consumerTag, message) -> {
                String data = new String(message.getBody());
                System.out.println("Start processing docs for " + data);

                try {
                    Render.render(data, "course", "biology_course");
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
