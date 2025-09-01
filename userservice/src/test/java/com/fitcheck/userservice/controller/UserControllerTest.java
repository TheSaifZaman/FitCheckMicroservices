package com.fitcheck.userservice.controller;

import com.fitcheck.userservice.dto.RegisterRequest;
import com.fitcheck.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void register() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("testFirstName");
        request.setLastName("testLastName");
        request.setEmail("test@mail.com");
        request.setPassword("password");
        userController.register(request);
        Mockito.verify(userService, Mockito.times(1)).register(request);
    }

    @Test
    void getUserById() {
        String userId = "12345";
        userController.getUserById(userId);
        Mockito.verify(userService, Mockito.times(1)).getUserById(userId);
    }
}