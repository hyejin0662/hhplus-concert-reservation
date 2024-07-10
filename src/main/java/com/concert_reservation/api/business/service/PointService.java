package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;

public interface PointService {
	PointResponse processPayment(Long bookingId);

	PointInfo createPoint(PointCommand pointCommand);
	PointInfo getPoint(Long pointId);
	PointInfo updatePoint(Long pointId, PointCommand pointCommand);
	void deletePoint(Long pointId);
}
