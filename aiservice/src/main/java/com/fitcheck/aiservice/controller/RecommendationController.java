package com.fitcheck.aiservice.controller;

import com.fitcheck.aiservice.model.Recommendation;
import com.fitcheck.aiservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendations(@PathVariable String userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUserId(userId));

    }

    @GetMapping("/activities/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendations(@PathVariable String activityId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByActivityId(activityId));
    }
}
