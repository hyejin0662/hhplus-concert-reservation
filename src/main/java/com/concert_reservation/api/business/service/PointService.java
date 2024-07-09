package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.response.PointResponse;

public interface PointService {
	PointResponse processPayment(Long bookingId);
}
