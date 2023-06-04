package com.kikopolis.wordcloudcore.config;

import com.kikopolis.wordcloudcore.service.messageconverter.ProtoBufMessageConverter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${mq.queue}")
    private String mqQueue;
    @Value("${mq.exchange}")
    private String mqExchange;
    @Value("${mq.routingkey}")
    private String mqRoutingKey;
    @Value("${mq.host}")
    private String mqHost;
    @Value("${mq.port}")
    private int mqPort;
    @Value("${mq.username}")
    private String mqUsername;
    @Value("${mq.password}")
    private String mqPassword;

    @Bean
    Queue getQueue() {
        return new Queue(mqQueue);
    }

    @Bean
    DirectExchange getExchange() {
        return new DirectExchange(mqExchange);
    }

    @Bean
    Binding getBinding(final Queue queue, final DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(mqRoutingKey);
    }

    @Bean
    MessageConverter messageConverter() {
        return new ProtoBufMessageConverter();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        final var factory = new CachingConnectionFactory();
        factory.setHost(mqHost);
        factory.setPort(mqPort);
        factory.setUsername(mqUsername);
        factory.setPassword(mqPassword);
        return factory;
    }

    @Bean
    public AmqpTemplate amqpTemplate(final ConnectionFactory connectionFactory) {
        final var template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
