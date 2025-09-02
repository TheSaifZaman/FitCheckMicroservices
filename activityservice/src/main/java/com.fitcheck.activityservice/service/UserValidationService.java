package com.fitcheck.activityservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserValidationService {
    private final WebClient userServiceWebClient;

    public boolean validateUserId(String userId) {
        return Boolean.TRUE.equals(userServiceWebClient.get()
                .uri("api/v1/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }
}
