package com.concert_reservation.api.application.user;

import org.springframework.stereotype.Component;

import com.concert_reservation.api.interfaces.controller.point.dto.request.PointRequest;
import com.concert_reservation.api.interfaces.controller.point.dto.response.PointResponse;
import com.concert_reservation.api.application.point.PointCommand;
import com.concert_reservation.api.application.point.PointInfo;
import com.concert_reservation.api.domain.point.PointService;
import com.concert_reservation.api.domain.user.UserService;
import com.concert_reservation.api.interfaces.controller.user.dto.UserRequest;
import com.concert_reservation.api.interfaces.controller.user.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserService userService;
	private final PointService pointService;

	public PointResponse chargePoint(PointRequest pointRequest) {
		PointCommand pointCommand = new PointCommand(null, pointRequest.getUserId(), pointRequest.getAmount(), pointRequest.getPaymentTime(), pointRequest.getPaymentMethod());
		PointInfo pointInfo = pointService.chargePoint(pointCommand);
		return PointResponse.from(pointInfo);
	}

	public PointResponse getPoint(Long pointId) {
		PointInfo pointInfo = pointService.getPoint(pointId);
		return PointResponse.from(pointInfo);
	}


	public UserResponse createUser(UserRequest userRequest) {
		UserCommand userCommand = UserCommand.builder()
			.userId(userRequest.getUserId())
			.name(userRequest.getName())
			.email(userRequest.getEmail())
			.phoneNumber(userRequest.getPhoneNumber())
			.balance(userRequest.getBalance())
			.build();
		UserInfo userInfo = userService.createUser(userCommand);
		return UserResponse.from(userInfo);
	}

	public UserResponse getUser(String userId) {
		UserInfo userInfo = userService.getUser(userId);
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
		UserInfo userInfo = userService.updateUser(userId, userCommand);
		return UserResponse.from(userInfo);
	}

	public void deleteUser(String userId) {
		userService.deleteUser(userId);
	}


}