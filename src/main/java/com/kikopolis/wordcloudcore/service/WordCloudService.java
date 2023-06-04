package com.kikopolis.wordcloudcore.service;

import com.kikopolis.wordcloudcore.entity.WordCount;
import com.kikopolis.wordcloudcore.entity.WorkOrder;
import com.kikopolis.wordcloudcore.repository.WordCountRepository;
import com.kikopolis.wordcloudcore.repository.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class WordCloudService {
    private static final int FILE_MAX_SIZE = 100_000_000;
    private final WorkOrderRepository workOrderRepository;
    private final WordCountRepository wordCountRepository;
    private final RabbitMqService rabbitMqService;

    @Autowired
    public WordCloudService(final WorkOrderRepository workOrderRepository,
                            final WordCountRepository wordCountRepository,
                            final RabbitMqService rabbitMqService) {
        this.workOrderRepository = workOrderRepository;
        this.wordCountRepository = wordCountRepository;
        this.rabbitMqService = rabbitMqService;
    }

    public String handleUpload(final InputStream file,
                               final String ignoredWords,
                               final boolean ignoreDefaultWords) {
        verifyFileSize(file);
        final UUID uuid = rabbitMqService.handle(file, ignoredWords, ignoreDefaultWords);
        return uuid.toString();
    }

    public List<WordCount> getWordCountsForWorkOrder(final String uuid) {
        final var workOrder = workOrderRepository.findByUuid(UUID.fromString(uuid));
        final Long workOrderId = workOrder.getId();
        return wordCountRepository.findByWorkOrderId(workOrderId);
    }

    public WorkOrder getWorkOrder(String uuid) {
        final var workOrder = workOrderRepository.findByUuid(UUID.fromString(uuid));
        if (workOrder == null) {
            throw new EntityNotFoundException("Work order not found");
        }
        return workOrder;
    }

    private void verifyFileSize(final InputStream file) {
        try {
            final var fileSize = file.available();
            if (fileSize > FILE_MAX_SIZE) {
                throw new IllegalArgumentException("File size must be under 100MB");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file size");
        }
    }
}