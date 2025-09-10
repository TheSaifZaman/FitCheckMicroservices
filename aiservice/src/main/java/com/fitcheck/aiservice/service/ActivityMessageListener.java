package com.fitcheck.aiservice.service;

import com.fitcheck.aiservice.events.ActivityEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void processActivityMessage(ActivityEvent activityEvent) {
        log.info("Received activity message: {}", activityEvent.getUserId());
    }
}
