package com.concert_reservation.api.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.facade.PaymentFacade;
import com.concert_reservation.api.application.facade.PointFacade;
import com.concert_reservation.common.model.WebResponseData;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;


    @PatchMapping
    @Operation(summary = "포인트 결제")
    public WebResponseData<PaymentResponse> payPoint(@Valid @RequestBody PaymentRequest paymentRequest) {
        PaymentResponse response = paymentFacade.payPoint(paymentRequest);
        return WebResponseData.ok(response);
    }



}
