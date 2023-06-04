package com.kikopolis.wordcloudcore.repository;

import com.kikopolis.wordcloudcore.entity.WordCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordCountRepository extends JpaRepository<WordCount, Long> {
    List<WordCount> findByWorkOrderId(Long id);
}
