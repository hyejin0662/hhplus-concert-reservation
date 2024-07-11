package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.service.PointService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PointFacade {
    private final PointService pointService;



    public PointResponse chargePoint(PointRequest pointRequest) {
        PointCommand pointCommand = new PointCommand(null, pointRequest.getUserId(), pointRequest.getBalance(), pointRequest.getAmount(), pointRequest.getPaymentTime(), pointRequest.getPaymentMethod());
        PointInfo pointInfo = pointService.chargePoint(pointCommand);
        return PointResponse.from(pointInfo);
    }

    public PointResponse getPoint(Long pointId) {
        PointInfo pointInfo = pointService.getPoint(pointId);
        return PointResponse.from(pointInfo);
    }

    public PointResponse deductPoint(PointRequest pointRequest) {
        return PointResponse.from(pointService.deductPoint(pointRequest.toCommand()));
    }


}