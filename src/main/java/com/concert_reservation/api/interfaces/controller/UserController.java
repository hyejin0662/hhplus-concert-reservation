package com.concert_reservation.api.interfaces.controller;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.application.facade.UserFacade;
import com.concert_reservation.common.model.WebResponseData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;


    @PatchMapping("/points/charge")
    @Operation(summary = "포인트 충전")
    public WebResponseData<PointResponse> chargePoint(@Valid @RequestBody PointRequest pointRequest) {
        PointResponse response = userFacade.chargePoint(pointRequest);
        return WebResponseData.ok(response);
    }


    @GetMapping("/points/{pointId}")
    @Operation(summary = "포인트 조회")
    public WebResponseData<PointResponse> getPoint(@PathVariable Long pointId) {
        PointResponse response = userFacade.getPoint(pointId);
        return WebResponseData.ok(response);
    }

    @PostMapping
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    public WebResponseData<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userFacade.createUser(userRequest);
        return WebResponseData.ok(response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "사용자 조회", description = "사용자 ID로 사용자 정보를 조회합니다.")
    public WebResponseData<UserResponse> getUser(@PathVariable String userId) {
        UserResponse response = userFacade.getUser(userId);
        return WebResponseData.ok(response);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "사용자 업데이트", description = "사용자 ID로 사용자 정보를 업데이트합니다.")
    public WebResponseData<UserResponse> updateUser(@PathVariable String userId, @Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userFacade.updateUser(userId, userRequest);
        return WebResponseData.ok(response);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "사용자 삭제", description = "사용자 ID로 사용자를 삭제합니다.")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userFacade.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
