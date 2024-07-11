package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.service.impl.PointServiceImpl;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PointFacade {
    private final PointServiceImpl pointServiceImpl;

    // public PointResponse processPayment(PointRequest pointRequest) {
    //     return PointResponse.from(pointServiceImpl.processPayment(pointServiceImpl));
    // }

    public PointResponse createPoint(PointRequest pointRequest) {
        PointCommand pointCommand = new PointCommand(null, pointRequest.getUserId(), pointRequest.getBalance(), pointRequest.getAmount(), pointRequest.getPaymentTime(), pointRequest.getPaymentMethod());
        PointInfo pointInfo = pointServiceImpl.createPoint(pointCommand);
        return PointResponse.from(pointInfo);
    }

    public PointResponse getPoint(Long pointId) {
        PointInfo pointInfo = pointServiceImpl.getPoint(pointId);
        return PointResponse.from(pointInfo);
    }

    public PointResponse updatePoint(Long pointId, PointRequest pointRequest) {
        PointCommand pointCommand = new PointCommand(pointId, pointRequest.getUserId(), pointRequest.getBalance(), pointRequest.getAmount(), pointRequest.getPaymentTime(), pointRequest.getPaymentMethod());
        PointInfo pointInfo = pointServiceImpl.updatePoint(pointId, pointCommand);
        return PointResponse.from(pointInfo);
    }

    public void deletePoint(Long pointId) {
        pointServiceImpl.deletePoint(pointId);
    }
}