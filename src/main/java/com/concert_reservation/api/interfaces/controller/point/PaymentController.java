package com.concert_reservation.api.interfaces.controller.point;

import com.concert_reservation.api.interfaces.controller.point.dto.request.PaymentRequest;
import com.concert_reservation.api.interfaces.controller.point.dto.response.PaymentResponse;
import com.concert_reservation.api.application.point.PaymentFacade;
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

  private final PaymentFacade paymentFacade;
  @PatchMapping("/payment")
  @Operation(summary = "포인트 결제")
  public WebResponseData<PaymentResponse> payPoint(@Valid @RequestBody PaymentRequest paymentRequest) {
    PaymentResponse response = paymentFacade.payPoint(paymentRequest);
    return WebResponseData.ok(response);
  }

}
