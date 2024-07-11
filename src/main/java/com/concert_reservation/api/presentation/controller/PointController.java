package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.facade.PointFacade;
import com.concert_reservation.common.model.WebResponseData;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
public class PointController {

    private final PointFacade pointFacade;


    @PatchMapping("/charge")
    @Operation(summary = "포인트 충전")
    public WebResponseData<PointResponse> chargePoint(@Valid @RequestBody PointRequest pointRequest) {
        PointResponse response = pointFacade.chargePoint(pointRequest);
        return WebResponseData.ok(response);
    }


    @PatchMapping("/deduct")
    @Operation(summary = "포인트 차감")
    public WebResponseData<PointResponse> deductPoint(@Valid @RequestBody PointRequest pointRequest) {
        PointResponse response = pointFacade.deductPoint(pointRequest);
        return WebResponseData.ok(response);
    }


    @GetMapping("/{pointId}")
    public WebResponseData<PointResponse> getPoint(@PathVariable Long pointId) {
        PointResponse response = pointFacade.getPoint(pointId);
        return WebResponseData.ok(response);
    }





}
