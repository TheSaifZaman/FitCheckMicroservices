package com.fitcheck.userservice.controller;

import com.fitcheck.userservice.dto.RegisterRequest;
import com.fitcheck.userservice.dto.UserResponse;
import com.fitcheck.userservice.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
        UserResponse response = userService.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.validateUserById(userId));
    }
}
