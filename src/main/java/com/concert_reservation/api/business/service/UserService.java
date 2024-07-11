package com.concert_reservation.api.business.service;

import java.util.List;

import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.dto.command.UserCommand;
import com.concert_reservation.api.business.model.dto.info.UserInfo;

public interface UserService {
	Long getUserBalance(String userId);
	UserInfo chargeUserBalance(String userId, Long amount);


	UserInfo createUser(UserCommand userCommand);
	UserInfo getUser(String userId);
	UserInfo updateUser(String userId, UserCommand userCommand);
	void deleteUser(String userId);
}
