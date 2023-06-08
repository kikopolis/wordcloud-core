package com.kikopolis.wordcloudcore.service.rabbitmq;

import com.google.protobuf.MessageLite;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;

public class ProtoBufMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(final Object object, final MessageProperties messageProperties) {
        final MessageLite msg = (MessageLite) object;
        final var body = msg.toByteArray();
        return new Message(body, messageProperties);
    }

    @Override
    public Object fromMessage(final Message message) {
        // not implemented because not listening on this side
        throw new UnsupportedOperationException("Not implemented");
    }
}
