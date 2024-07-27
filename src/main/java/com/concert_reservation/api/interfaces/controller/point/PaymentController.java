package com.concert_reservation.api.interfaces.controller.point;

import com.concert_reservation.api.interfaces.controller.point.dto.PaymentDto;
import com.concert_reservation.api.interfaces.controller.point.dto.PaymentRequest;
import com.concert_reservation.api.application.facade.PaymentFacade;
import com.concert_reservation.common.model.WebResponseData;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

  private final PaymentService paymentService;
  @PatchMapping("/payment")
  @Operation(summary = "포인트 결제")
  public WebResponseData<PaymentDto.Response> payPoint(@Valid @RequestBody PaymentDto.Request request) {
    var command = PaymentCommand.PayPoint
        .builder()
        .userId(request.getUserId())
        .paymentMethod(request.getPaymentMethod())
        .amount(request.getAmount())
        .concertOptionId(request.getConcertOptionId())
        .build();
    var payPointInfo = paymentService.payPoint(command);
    PaymentDto.Response response = PaymentDto.Response.builder()
        .paymentId(payPointInfo.getPaymentId())
        .userId(payPointInfo.getUserId())
        .amount(payPointInfo.getAmount())
        .paymentMethod(payPointInfo.getPaymentMethod())
        .build();
    return WebResponseData.ok(response);
  }
}
