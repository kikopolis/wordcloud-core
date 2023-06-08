package com.kikopolis.wordcloudcore.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class WordCountService {
    public String countFromFile(final InputStream file,
                                final String ignoredWords,
                                final boolean ignoreDefaultWords,
                                final boolean autoCorrectGrammar) {
        final UUID uuid = rabbitMqService.handle(file, ignoredWords, ignoreDefaultWords, autoCorrectGrammar);
        return uuid.toString();
    }

    public String countFromText(final String text,
                                final String ignoredWords,
                                final boolean ignoreDefaultWords,
                                final boolean autoCorrectGrammar) {
        final UUID uuid = rabbitMqService.handle(text, ignoredWords, ignoreDefaultWords, autoCorrectGrammar);
        return uuid.toString();
    }
}
