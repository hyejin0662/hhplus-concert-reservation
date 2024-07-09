package com.concert_reservation.api.business.service;

import com.concert_reservation.api.application.dto.response.UserResponse;

public interface UserService {
	Long getUserBalance(String userId);
	UserResponse chargeUserBalance(String userId, Long amount);
}
