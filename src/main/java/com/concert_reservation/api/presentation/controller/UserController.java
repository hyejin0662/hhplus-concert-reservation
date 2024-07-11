package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.application.facade.UserFacade;
import com.concert_reservation.common.model.WebResponseData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    public WebResponseData<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userFacade.createUser(userRequest);
        return WebResponseData.ok(response);
    }

    @GetMapping("/{userId}")
    public WebResponseData<UserResponse> getUser(@PathVariable String userId) {
        UserResponse response = userFacade.getUser(userId);
        return WebResponseData.ok(response);
    }

    @PutMapping("/{userId}")
    public WebResponseData<UserResponse> updateUser(@PathVariable String userId, @Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userFacade.updateUser(userId, userRequest);
        return WebResponseData.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userFacade.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
