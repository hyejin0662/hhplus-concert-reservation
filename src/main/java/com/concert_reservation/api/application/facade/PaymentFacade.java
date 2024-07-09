package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.business.service.impl.PointServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentFacade {
    private final PointServiceImpl pointServiceImpl;

    public PointResponse processPayment(PaymentRequest paymentRequest) {
        return PointResponse.from(pointServiceImpl.processPayment(paymentRequest));
    }
}