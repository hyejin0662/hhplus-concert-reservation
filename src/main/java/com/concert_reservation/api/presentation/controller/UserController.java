package com.concert_reservation.api.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.application.facade.UserFacade;
import com.concert_reservation.api.business.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade userFacade;

    @GetMapping("/{userId}/balance")
    public ResponseEntity<Long> getUserBalance(@PathVariable String userId) {
        Long balance = userFacade.getUserBalance(userId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/{userId}/balance")
    public ResponseEntity<Long> chargeUserBalance(@PathVariable String userId, @RequestBody Long amount) {
        Long balance = userFacade.chargeUserBalance(userId, amount);
        return ResponseEntity.ok(balance);
    }
}