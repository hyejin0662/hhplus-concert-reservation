package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.application.facade.UserFacade;
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userFacade.createUser(userRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String userId) {
        UserResponse response = userFacade.getUser(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userFacade.updateUser(userId, userRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userFacade.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
