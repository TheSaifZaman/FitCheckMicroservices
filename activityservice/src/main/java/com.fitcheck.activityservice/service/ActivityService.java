package com.fitcheck.activityservice.service;

import com.fitcheck.activityservice.controller.ActivityRepository;
import com.fitcheck.activityservice.dto.ActivityResponse;
import com.fitcheck.activityservice.dto.ActivityStoreRequest;
import com.fitcheck.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;
    private final KafkaTemplate<String, Activity> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public ActivityResponse store(ActivityStoreRequest request) {
        if (!userValidationService.validateUserId(request.getUserId())) {
            throw new IllegalArgumentException("Invalid userId: " + request.getUserId());
        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);
        try{
            kafkaTemplate.send(topicName, savedActivity.getUserId(), savedActivity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return getActivityResponse(savedActivity);
    }

    private static ActivityResponse getActivityResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setEndTime(activity.getEndTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}
