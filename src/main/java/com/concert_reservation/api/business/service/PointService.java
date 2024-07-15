package com.concert_reservation.api.business.service;

import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;

public interface PointService {

	PointInfo chargePoint(PointCommand pointCommand);
	PointInfo getPoint(Long pointId);

	PointInfo deductPoint(PointCommand command);
}
