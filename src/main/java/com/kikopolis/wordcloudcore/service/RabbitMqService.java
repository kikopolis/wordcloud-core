package com.kikopolis.wordcloudcore.service;

import com.kikopolis.wordcloudcore.api.RabbitMqApi;
import com.kikopolis.wordcloudcore.service.rabbitmq.ProtoBufMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
public class RabbitMqService {
    private RabbitMqApi rabbitMqApi;
    private ProtoBufMessageService pbService;

    @Autowired
    public RabbitMqService(final RabbitMqApi rabbitMqApi,
                           final ProtoBufMessageService pbService) {
        this.rabbitMqApi = rabbitMqApi;
        this.pbService = pbService;
    }

    public UUID handle(final InputStream file,
                       final String ignoredWords,
                       final boolean ignoreDefaultWords) {
        final var uuid = randomUUID();
        final var mqMessage = pbService.createMqMessage(
                file, ignoredWords, ignoreDefaultWords, uuid);
        rabbitMqApi.send(mqMessage);
        return uuid;
    }

    public UUID createAndSendFromFile() {
        final var uuid = createUUID();
        final var msgFromFile = "null";
        rabbitMqApi.send(msgFromFile);
        return uuid;
    }

    public UUID createAndSendFromText() {
        final var uuid = createUUID();
        final var msgFromText = "null";
        rabbitMqApi.send(msgFromText);
        return uuid;
    }

    private UUID createUUID() {
        return randomUUID();
    }
}
