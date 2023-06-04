package com.kikopolis.wordcloudcore.service;

import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class RabbitMqSender {
    private static final Logger LOGGER = getLogger(RabbitMqSender.class);
    private final AmqpTemplate amqpTemplate;
    @Value("${mq.exchange}")
    private String mqExchange;
    @Value("${mq.routingkey}")
    private String mqRoutingKey;

    @Autowired
    public RabbitMqSender(final AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(final Object message) {
        amqpTemplate.convertAndSend(mqExchange, mqRoutingKey, message);
        LOGGER.info(" [x] Message sent to RabbitMQ");
    }
}
