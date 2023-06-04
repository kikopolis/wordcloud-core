package com.kikopolis.wordcloudcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
public class RabbitMqService {
    private RabbitMqSender rabbitMqSender;
    private ProtoBufMessageService pbService;

    @Autowired
    public RabbitMqService(final RabbitMqSender rabbitMqSender,
                           final ProtoBufMessageService pbService) {
        this.rabbitMqSender = rabbitMqSender;
        this.pbService = pbService;
    }

    public UUID handle(final InputStream file,
                       final String ignoredWords,
                       final boolean ignoreDefaultWords) {
        final var uuid = randomUUID();
        final var mqMessage = pbService.createMqMessage(
                file, ignoredWords, ignoreDefaultWords, uuid);
        rabbitMqSender.send(mqMessage);
        return uuid;
    }
}
