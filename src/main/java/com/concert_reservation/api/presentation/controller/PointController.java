package com.concert_reservation.api.presentation.controller;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.facade.PointFacade;
import com.concert_reservation.api.application.facade.UserFacade;
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






}
