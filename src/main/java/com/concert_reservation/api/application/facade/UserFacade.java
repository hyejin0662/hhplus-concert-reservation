package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.dto.command.UserCommand;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserService userServiceImpl;


	public UserResponse createUser(UserRequest userRequest) {
		UserCommand userCommand = UserCommand.builder()
			.userId(userRequest.getUserId())
			.name(userRequest.getName())
			.email(userRequest.getEmail())
			.phoneNumber(userRequest.getPhoneNumber())
			.balance(userRequest.getBalance())
			.build();
		UserInfo userInfo = userServiceImpl.createUser(userCommand);
		return UserResponse.from(userInfo);
	}

	public UserResponse getUser(String userId) {
		UserInfo userInfo = userServiceImpl.getUser(userId);
		return UserResponse.from(userInfo);
	}

	public UserResponse updateUser(String userId, UserRequest userRequest) {
		UserCommand userCommand = UserCommand.builder()
			.userId(userId)
			.name(userRequest.getName())
			.email(userRequest.getEmail())
			.phoneNumber(userRequest.getPhoneNumber())
			.balance(userRequest.getBalance())
			.build();
		UserInfo userInfo = userServiceImpl.updateUser(userId, userCommand);
		return UserResponse.from(userInfo);
	}

	public void deleteUser(String userId) {
		userServiceImpl.deleteUser(userId);
	}


}