package ru.itis.rabbitmqexamples.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.rabbitmqexamples.Dto.InputDataDto;
import ru.itis.rabbitmqexamples.service.interfaces.SendDataService;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class SendDataServiceImpl implements SendDataService {


    private final static String PHYSICS_QUEUE = "physics_queue";
    private final static String MATH_QUEUE = "math_queue";
    private final static String BIO_QUEUE = "bio_queue";

    private final static String PHYSICS_ROUTING_KEY = "exact.physics";
    private final static String MATH_ROUTING_KEY = "exact.math";
    private final static String BIO_ROUTING_KEY = "natural.bio";

    private final static String EXCHANGE_DIRECT = "docs_direct_exchange";
    private final static String EXCHANGE_FANOUT = "apply_docs";
    private final static String EXCHANGE_TOPIC = "docs_topic_exchange";


    private final ConnectionFactory connectionFactory;
    private Connection connection;

    @PostConstruct
    public void setNewConnection(){
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);        }
    }

    @Override
    public InputDataDto senData(InputDataDto user) {

        String userData;
        try {
            userData = new ObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }

        if (user.getOperation().equals("apply")) {
            try {
                Channel channel = connection.createChannel();
                // создаем exchange
                channel.exchangeDeclare(EXCHANGE_FANOUT, "fanout");
                channel.basicPublish(EXCHANGE_FANOUT, "",null, userData.getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        else {
            try {
                Channel channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_DIRECT, "direct");
                channel.queueBind(PHYSICS_QUEUE, EXCHANGE_DIRECT, PHYSICS_ROUTING_KEY);
                channel.queueBind(MATH_QUEUE, EXCHANGE_DIRECT, MATH_ROUTING_KEY);
                channel.queueBind(BIO_QUEUE, EXCHANGE_DIRECT, BIO_ROUTING_KEY);

                String currentRouting = user.getOperation();
                channel.basicPublish(EXCHANGE_DIRECT, currentRouting, null, userData.getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            try {
                Channel channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_TOPIC, "topic");
                String currentRouting = user.getOperation();
                channel.basicPublish(EXCHANGE_TOPIC, currentRouting, null, userData.getBytes());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return user;
    }
}
