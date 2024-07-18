package com.concert_reservation.api.application.facade;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.concert_reservation.api.application.dto.request.PaymentRequest;
import com.concert_reservation.api.application.dto.request.PointRequest;
import com.concert_reservation.api.application.dto.request.UserRequest;
import com.concert_reservation.api.application.dto.response.PaymentResponse;
import com.concert_reservation.api.application.dto.response.PointResponse;
import com.concert_reservation.api.application.dto.response.UserResponse;
import com.concert_reservation.api.business.model.dto.command.PointCommand;
import com.concert_reservation.api.business.model.dto.command.UserCommand;
import com.concert_reservation.api.business.model.dto.info.PointInfo;
import com.concert_reservation.api.business.model.dto.info.UserInfo;
import com.concert_reservation.api.business.service.PointService;
import com.concert_reservation.api.business.service.UserService;
import com.concert_reservation.common.model.WebResponseData;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserFacade {
	private final UserService userService;
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