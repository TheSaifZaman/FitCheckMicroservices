package com.fitcheck.activityservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public boolean validateUserId(String userId) {
        log.info("Calling user ID: {} for validation", userId);
        return Boolean.TRUE.equals(userServiceWebClient.get()
                .uri("api/v1/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}
