package com.fitcheck.activityservice.controller;

import com.fitcheck.activityservice.dto.ActivityStoreRequest;
import com.fitcheck.activityservice.dto.ActivityResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fitcheck.activityservice.service.ActivityService;

@RestController
@RequestMapping("/api/v1/activities")
@AllArgsConstructor
public class ActivityController {
    private ActivityService activityService;

    @PostMapping("/")
    public ResponseEntity<ActivityResponse> store(@RequestBody ActivityStoreRequest request){
        return ResponseEntity.ok(activityService.store(request));
    }
}
