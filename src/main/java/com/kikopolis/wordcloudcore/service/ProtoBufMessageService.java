package com.kikopolis.wordcloudcore.service;

import com.google.protobuf.ByteString;
import com.kikopolis.mq.MqMessageProto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ProtoBufMessageService {
    private static final Logger LOGGER = getLogger(ProtoBufMessageService.class);

    public MqMessageProto.MqMessage createMqMessage(
            final InputStream file,
            final String ignoredWords,
            final boolean ignoreDefaultWords,
            final UUID uuid) {
        final var builder = MqMessageProto.MqMessage.newBuilder();
        try {
            final byte[] bytes = file.readAllBytes();
            final var byteStr = ByteString.copyFrom(bytes);
            final var uuidStr = uuid.toString();
            return builder
                    .setFile(byteStr)
                    .setIgnoredWords(ignoredWords)
                    .setIgnoreDefaultWords(ignoreDefaultWords)
                    .setUuid(uuidStr)
                    .build();
        } catch (IOException e) {
            LOGGER.error("Error converting InputStream to ByteString", e);
        }
        return builder.build();
    }
}
